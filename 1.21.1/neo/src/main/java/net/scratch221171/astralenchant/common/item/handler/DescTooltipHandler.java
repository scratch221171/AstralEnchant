package net.scratch221171.astralenchant.common.item.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.item.IAEDescribable;

public class DescTooltipHandler {

    public static void addDesc(ItemTooltipEvent event) {
        var stack = event.getItemStack();
        if (!(stack.getItem() instanceof IAEDescribable describable)) return;

        String key = describable.getDescKey(stack);
        if (key == null || key.isEmpty()) return;

        var style = describable.getDescStyle(stack);
        if (style == null)
            style = Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(true);

        var line = Component.translatable(key, describable.getDescArgs(stack)).setStyle(style);

        var tooltip = event.getToolTip();
        int index = describable.getDescInsertIndex(stack, tooltip);
        if (index < 0 || index > tooltip.size()) index = tooltip.size();
        tooltip.add(index, line);
    }
}
