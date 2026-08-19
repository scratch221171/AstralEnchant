package net.scratch221171.astralenchant.client.enchantment.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.enchantment.handler.OverMendingHandler;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class OverMendingClientHandler {

    public static void insertExtraDurabilityLine(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        long time = System.currentTimeMillis();
        int color = Mth.color(0.4f, (float) (0.8f + 0.1f * Math.sin(time * 2 * Math.PI / 2048f)), 1);
        if (stack.has(AEDataComponents.EXTRA_DURABILITY)) {
            event.getToolTip()
                    .addLast(Component.translatable("item.extra_durability")
                            .withStyle(ChatFormatting.GRAY)
                            .append(Component.literal(
                                            String.valueOf(stack.getOrDefault(AEDataComponents.EXTRA_DURABILITY, 0)))
                                    .withColor(color))
                            .append(Component.literal(" / ").withStyle(ChatFormatting.GRAY))
                            .append(Component.literal(String.valueOf(OverMendingHandler.maxExtra(
                                            AEUtil.getEnchantmentLevel(AEEnchantments.OVER_MENDING, stack))))
                                    .withColor(color)));
        }
    }
}
