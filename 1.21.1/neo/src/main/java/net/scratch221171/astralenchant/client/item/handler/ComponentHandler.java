package net.scratch221171.astralenchant.client.item.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.client.item.renderer.ProgressBarDecorator;
import net.scratch221171.astralenchant.common.component.DisabledEnchantments;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.registry.AEItems;

public class ComponentHandler {

    public static void addComponentDesc(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        var component = stack.getOrDefault(AEDataComponents.DISABLED_ENCHANTMENTS, DisabledEnchantments.EMPTY);
        if (component == DisabledEnchantments.EMPTY) return;
        event.getToolTip()
                .addLast(Component.translatable(
                                "astralenchant.item.disabled_enchantments",
                                component.entries().size())
                        .withStyle(ChatFormatting.DARK_GRAY));
    }

    public static void registerDecorations(RegisterItemDecorationsEvent event) {
        event.register(AEItems.BUDDING_ARCANIUM_INGOT, new ProgressBarDecorator());
    }
}
