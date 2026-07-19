package net.scratch221171.astralenchant.compat.curios.handler;

import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.enchantment.handler.EssenceOfEnchantmentHandler;
import net.scratch221171.astralenchant.config.ServerConfig;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;

public class CuriosEssenceOfEnchantmentHandler {

    public static void addCurioAttribute(CurioAttributeModifierEvent event) {
        if (!ServerConfig.EnchantmentSettings.EssenceOfEnchantment.CURIOS_COMPAT.getAsBoolean()) return;
        ItemStack stack = event.getItemStack();
        int totalLevel = EssenceOfEnchantmentHandler.getTotalEnchantmentLevel(stack);
        String slotId = event.getSlotContext().identifier(); // ※要確認、下記注記参照

        for (var entry : event.getOriginalModifiers().entries()) {
            EssenceOfEnchantmentHandler.addModifier(
                    stack,
                    totalLevel,
                    entry.getKey(),
                    event::addModifier,
                    entry.getValue().id(),
                    slotId);
        }
    }
}
