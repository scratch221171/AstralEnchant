package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

@EventBusSubscriber(modid = Constants.MODID)
public class OverloadHandler {

    @SubscribeEvent
    private static void modifyLevels(GetEnchantmentLevelEvent event) {
        //        int overload = event.getStack().getOrDefault(AEDataComponents.OVERLOAD, 0);
        int overload = AEUtil.getEnchantmentLevel(
                AEEnchantments.OVERLOAD, event.getEnchantments().toImmutable());
        if (overload <= 0) return;

        var enchantments = event.getEnchantments();
        for (var entry : enchantments.keySet()) {
            if (!entry.is(AEEnchantments.OVERLOAD)) {
                int current = enchantments.getLevel(entry);
                enchantments.upgrade(entry, current + overload);
            }
        }
    }

    @SubscribeEvent
    private static void modifyTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        int level = AEUtil.getEnchantmentLevel(AEEnchantments.OVERLOAD, stack);
        if (level > 0) {
            AEUtil.modifyTooltip(
                    event.getToolTip(),
                    c -> c instanceof TranslatableContents t && t.getKey().equals("enchantment.astralenchant.overload"),
                    c -> createRainbowGradient(c.getString()));
        }
    }

    private static Component createRainbowGradient(String text) {
        MutableComponent result = Component.empty();
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
