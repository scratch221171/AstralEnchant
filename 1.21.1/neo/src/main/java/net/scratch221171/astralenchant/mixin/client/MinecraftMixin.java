package net.scratch221171.astralenchant.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    @Nullable public HitResult hitResult;

    @Shadow
    public LocalPlayer player;

    @Shadow
    public ClientLevel level;

    @Unique private HitResult astralenchant$savedHitResult;

    @Unique private boolean astralenchant$redirected;

    /**
     * {@link AEEnchantments#DISTORTION} が付いている場合、
     * 「エンティティに当たっていない」攻撃を視線に一番近いmobへ差し替える。
     * 差し替えは startAttack() の実行中だけに限定し、RETURN時に必ず元へ戻す。
     */
    @Inject(method = "startAttack()Z", at = @At("HEAD"))
    private void astralenchant$redirectMissAttack(CallbackInfoReturnable<Boolean> cir) {

        astralenchant$redirected = false;

        if (this.hitResult == null) return;
        if (!astralenchant$isRedirectable(this.hitResult)) return;

        int enchLevel = AEUtil.getEnchantmentLevel(AEEnchantments.DISTORTION, player);
        if (enchLevel <= 0) return;

        double radAnglePerLevel =
                ServerConfig.EnchantmentSettings.Distortion.ANGLE_PER_LEVEL.getAsDouble() * Math.PI / 180;
        Entity target = astralenchant$findTarget(enchLevel * radAnglePerLevel);
        if (target == null) return;

        astralenchant$savedHitResult = this.hitResult;
        astralenchant$redirected = true;
        this.hitResult = new EntityHitResult(target);
    }

    /**
     * vanilla本体の処理(gameMode.attack等)が終わった直後に、
     * 描画やほかのMod向けにhitResultを元通りに戻す。
     */
    @Inject(method = "startAttack()Z", at = @At("RETURN"))
    private void astralenchant$restoreHitResult(CallbackInfoReturnable<Boolean> cir) {
        if (astralenchant$redirected) {
            this.hitResult = astralenchant$savedHitResult;
            astralenchant$savedHitResult = null;
            astralenchant$redirected = false;
        }
    }

    /**
     * リダイレクト対象にしてよいhitResultか判定する。
     * - MISS: 対象なし → リダイレクト可
     * - BLOCK: 当たり判定(衝突形状)を持たない装飾ブロック(草など)のみリダイレクト可。
     *          採掘対象になりうる実体のあるブロックはリダイレクトしない(誤爆防止)。
     * - ENTITY: すでに当たっているので対象外
     */
    @Unique private boolean astralenchant$isRedirectable(HitResult result) {
        return switch (result.getType()) {
            case MISS -> true;
            case BLOCK -> {
                BlockHitResult blockHit = (BlockHitResult) result;
                BlockState state = level.getBlockState(blockHit.getBlockPos());
                yield state.getCollisionShape(level, blockHit.getBlockPos()).isEmpty();
            }
            default -> false;
        };
    }

    @Unique private Entity astralenchant$findTarget(double apexAngle) {

        AttributeInstance ins = player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
        if (ins == null) return null;
        double range = ins.getValue();

        AABB searchBox = player.getBoundingBox().inflate(range);

        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getLookAngle().normalize();

        double bestCos = Math.cos(Math.min(apexAngle, Math.PI));
        Entity best = null;

        for (Entity e : level.getEntities(
                player,
                searchBox,
                entity -> entity instanceof LivingEntity le
                        && le.isAlive()
                        && le.isPickable()
                        && le.isAttackable()
                        && player.canAttack(le))) {

            Vec3 closestPoint = astralenchant$closestPointOnBoxToRay(eye, look, e.getBoundingBox());

            // 実距離チェック(AABB探索の対角線誤差対策)
            if (eye.distanceToSqr(closestPoint) > range * range) continue;

            // 壁越しに殴れないよう遮蔽物チェック
            BlockHitResult occlusion = level.clip(
                    new ClipContext(eye, closestPoint, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
            if (occlusion.getType() == HitResult.Type.BLOCK) continue;

            Vec3 dir = closestPoint.subtract(eye).normalize();
            double cos = dir.dot(look);

            if (cos >= bestCos) {
                bestCos = cos;
                best = e;
            }
        }

        return best;
    }

    @Unique private Vec3 astralenchant$closestPointOnBoxToRay(Vec3 rayOrigin, Vec3 rayDir, AABB box) {

        Vec3 toCenter = box.getCenter().subtract(rayOrigin);
        double t = Math.max(0, toCenter.dot(rayDir));

        Vec3 pointOnRay = rayOrigin.add(rayDir.scale(t));

        double x = Math.clamp(pointOnRay.x, box.minX, box.maxX);
        double y = Math.clamp(pointOnRay.y, box.minY, box.maxY);
        double z = Math.clamp(pointOnRay.z, box.minZ, box.maxZ);

        return new Vec3(x, y, z);
    }
}
