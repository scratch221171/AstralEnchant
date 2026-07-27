package net.scratch221171.astralenchant.client.enchantment.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.common.util.CommonUtil;

public class OverloadClientHandler {

    public static void rainbowColor(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (AEUtil.getEnchantmentLevel(AEEnchantments.OVERLOAD, stack) > 0
                || AEUtil.getEnchantmentLevel(
                                AEEnchantments.OVERLOAD,
                                stack.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY))
                        > 0) {
            AEUtil.modifyTooltip(
                    event.getToolTip(),
                    c -> AEUtil.isTranslationOf(c, CommonUtil.getLangKey(AEEnchantments.OVERLOAD)),
                    OverloadClientHandler::createRainbowGradient);
        }
    }

    private static Component createRainbowGradient(Component component) {
        String text = component.getString();
        var result = Component.empty();
        long time = System.currentTimeMillis();
        float baseHue = (time % 4000) / 4000f;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            float hue = (baseHue - (float) i / length) % 1f;
            int color = java.awt.Color.HSBtoRGB(hue, 1f, 1f) & 0xFFFFFF;
            result.append(Component.literal(String.valueOf(text.charAt(i))).withColor(color));
        }
        return result;
    }
}
