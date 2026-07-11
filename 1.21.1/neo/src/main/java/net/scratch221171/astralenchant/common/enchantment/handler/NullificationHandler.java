package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.scratch221171.astralenchant.common.data.TagGroupLoader;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;

public class NullificationHandler {

    public static void addDamageTag(EntityInvulnerabilityCheckEvent event) {
        var entity = event.getEntity();
        if (entity.level().isClientSide) return;
        var source = event.getSource();
        if (source.getEntity() instanceof LivingEntity attacker) {
            AEUtil.getEnchantmentHolder(AEEnchantments.NULLIFICATION, attacker).ifPresent(holder -> {
                var weapon = source.getWeaponItem();
                if (weapon != null && weapon.getEnchantmentLevel(holder) > 0) {
                    var accessor = (IDamageSourceExtension) source;
                    var targets = TagGroupLoader.getNullificationTags();
                    targets.forEach(accessor::astralenchant$addExtraTag);
                }
            });
        }
    }

    // パーティクル
    public static void spawnParticles(LivingIncomingDamageEvent event) {
        var entity = event.getEntity();
        AEUtil.getEnchantmentHolder(AEEnchantments.NULLIFICATION, entity).ifPresent(holder -> {
            var weapon = event.getSource().getWeaponItem();
            if (weapon != null
                    && weapon.getEnchantmentLevel(holder) > 0
                    && entity.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 10, 0f, 1f, 0f, 0.04f);
            }
        });
    }
}
