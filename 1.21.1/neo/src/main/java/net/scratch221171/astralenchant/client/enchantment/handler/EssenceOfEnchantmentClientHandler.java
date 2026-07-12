package net.scratch221171.astralenchant.client.enchantment.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class EssenceOfEnchantmentClientHandler {

    public static void shine(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (AEUtil.getEnchantmentLevel(AEEnchantments.ESSENCE_OF_ENCHANTMENT, stack) > 0
                || AEUtil.getEnchantmentLevel(
                                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                                stack.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY))
                        > 0) {
            AEUtil.modifyTooltip(
                    event.getToolTip(),
                    c -> AEUtil.isTranslationOf(c, AEUtil.getLangKey(AEEnchantments.ESSENCE_OF_ENCHANTMENT)),
                    EssenceOfEnchantmentClientHandler::createShine);
        }
    }

    private static Component createShine(Component component) {
        String text = component.getString();
        var result = Component.empty();
        long time = System.currentTimeMillis();
        float t = (time % 2000L) / 2000f;
        float pulse = 0.5f - 0.5f * (float) Math.cos(t * Math.PI * 2);
        pulse = (float) Math.pow(pulse, 6); // 数字を大きくすると光る時間が短くなる

        float saturation = 0.3f * pulse;
        int color = java.awt.Color.HSBtoRGB(0.85f, 0.5f - saturation, 1f) & 0xFFFFFF;
        result.append(Component.literal(text).withColor(color));

        return result;
    }
}
