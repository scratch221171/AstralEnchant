package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.scratch221171.astralenchant.common.init.AEAttributes;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.common.util.IFoodDataExtension;
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

    @Inject(method = "onAttributeUpdated", at = @At("TAIL"))
    private void handleOverflowedFoodLevel(Holder<Attribute> attribute, CallbackInfo ci) {
        if (attribute.is(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER) && (Object) this instanceof Player player) {
            ((IFoodDataExtension) player.getFoodData()).astralenchant$update();
        }
    }
}
