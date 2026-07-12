package net.scratch221171.astralenchant.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.Collection;
import net.minecraft.core.Holder;
import net.minecraft.server.commands.EnchantCommand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.common.enchantment.handler.AffinityHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantCommand.class)
public abstract class EnchantCommandMixin {

    @WrapOperation(
            method = "enchant",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;isEnchantmentCompatible(Ljava/util/Collection;Lnet/minecraft/core/Holder;)Z"))
    private static boolean astralenchant$wrapCompatibilityCheck(
            Collection<Holder<Enchantment>> existing,
            Holder<Enchantment> candidate,
            Operation<Boolean> original,
            @Local ItemStack stack) {
        AffinityHandler.Context.push(stack);
        try {
            return original.call(existing, candidate);
        } finally {
            AffinityHandler.Context.pop();
        }
    }
}
