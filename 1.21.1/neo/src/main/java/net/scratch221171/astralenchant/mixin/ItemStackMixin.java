package net.scratch221171.astralenchant.mixin;

import java.util.function.BiConsumer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.enchantment.handler.AlmightyHandler;
import net.scratch221171.astralenchant.common.enchantment.handler.EssenceOfEnchantmentHandler;
import net.scratch221171.astralenchant.common.event.ItemSetEnchantmentEvent;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    // 実際のステータス計算に関わる方
    @ModifyVariable(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",
            at = @At("HEAD"),
            argsOnly = true)
    private BiConsumer<Holder<Attribute>, AttributeModifier> wrap(
            BiConsumer<Holder<Attribute>, AttributeModifier> original, EquipmentSlot slot) {
        return (attr, mod) -> {
            EssenceOfEnchantmentHandler.handle(
                    (ItemStack) (Object) this, attr, original, mod.id(), slot.getSerializedName());
            original.accept(attr, mod);
        };
    }

    // Tooltipなど、見た目だけ
    @ModifyVariable(
            method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Ljava/util/function/BiConsumer;)V",
            at = @At("HEAD"),
            argsOnly = true)
    private BiConsumer<Holder<Attribute>, AttributeModifier> wrap(
            BiConsumer<Holder<Attribute>, AttributeModifier> original, EquipmentSlotGroup slotGroup) {
        return (attr, mod) -> {
            EssenceOfEnchantmentHandler.handle(
                    (ItemStack) (Object) this, attr, original, mod.id(), slotGroup.getSerializedName());
            original.accept(attr, mod);
        };
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    private <T> void astralenchant$onEnchanted(
            DataComponentType<? super T> type, T value, CallbackInfoReturnable<T> cir) {
        if (!(type == DataComponents.ENCHANTMENTS && value instanceof ItemEnchantments enchantments)) return;

        ItemStack stack = (ItemStack) (Object) this;

        var event = new ItemSetEnchantmentEvent(stack, enchantments);
        if (NeoForge.EVENT_BUS.post(event).isCanceled()) {
            cir.setReturnValue((T) stack.get(DataComponents.ENCHANTMENTS));
        }
    }

    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void astralenchant$forceCorrectTool(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (AlmightyHandler.isAlmightyCorrectTool(self, state)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void astralenchant$virtualSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        var self = (ItemStack) (Object) this;
        if (AEUtil.getEnchantmentLevel(AEEnchantments.ALMIGHTY, self) <= 0) return;

        if (!(self.getItem() instanceof TieredItem tiered)) return;

        float virtual = AlmightyHandler.getVirtualDestroySpeed(tiered.getTier(), state);
        cir.setReturnValue(Math.max(cir.getReturnValue(), virtual));
    }
}
