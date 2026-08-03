package net.scratch221171.astralenchant.mixin.client;

import net.minecraft.client.player.LocalPlayer;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Redirect(
            method = {"aiStep", "canStartSprinting"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean astralenchant$disableUsingItemSlowdown(LocalPlayer instance) {
        return AEUtil.getEnchantmentLevel(AEEnchantments.DEXTERITY, instance) <= 0 && instance.isUsingItem();
    }
}
