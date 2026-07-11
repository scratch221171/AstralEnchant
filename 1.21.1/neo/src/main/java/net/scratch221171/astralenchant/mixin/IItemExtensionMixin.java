package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IItemExtension.class)
public interface IItemExtensionMixin {
    @Inject(method = "supportsEnchantment", at = @At("RETURN"), cancellable = true)
    private void astralenchant$supportsAny(
            ItemStack stack, Holder<Enchantment> enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) return;
        if (AEUtil.getEnchantmentLevel(AEEnchantments.AFFINITY, stack) > 0) {
            cir.setReturnValue(true);
        }
    }
}
