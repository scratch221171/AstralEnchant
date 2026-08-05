package net.scratch221171.astralenchant.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique private boolean astralenchant$curseControlsPhysics = false;

    @Inject(method = "tick", at = @At("HEAD"))
    public void forceNoPhysics(CallbackInfo ci) {
        var self = (LivingEntity) (Object) this;
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_INTANGIBILITY, self) > 0) {
            self.noPhysics = true;
            self.setOnGround(false);
            self.setSwimming(false);
            astralenchant$curseControlsPhysics = true;
        } else if (astralenchant$curseControlsPhysics) {
            self.noPhysics = false;
            astralenchant$curseControlsPhysics = false;
        }
    }
}
