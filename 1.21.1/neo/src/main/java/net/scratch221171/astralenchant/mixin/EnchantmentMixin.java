package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.enchantment.handler.AffinityHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "areCompatible", at = @At("HEAD"), cancellable = true)
    private static void astralenchant$bypass(
            Holder<Enchantment> first, Holder<Enchantment> second, CallbackInfoReturnable<Boolean> cir) {
        if (AffinityHandler.Context.isActive()) {
            cir.setReturnValue(true);
        }
    }
}
