package net.scratch221171.astralenchant.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.inventory.AnvilMenu;
import net.scratch221171.astralenchant.common.enchantment.handler.AffinityHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @WrapMethod(method = "createResult")
    private void astralenchant$wrapCreateResult(Operation<Void> original) {
        var stack = ((AnvilMenu) (Object) this).getSlot(0).getItem();
        AffinityHandler.Context.push(stack);
        try {
            original.call();
        } finally {
            AffinityHandler.Context.pop();
        }
    }
}
