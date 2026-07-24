package net.scratch221171.astralenchant.common.enchantment.handler;

import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.GrindstoneEvent;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.event.ItemSetEnchantmentEvent;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class CurseOfEnchantmentHandler {

    public static void cancelChange(ItemSetEnchantmentEvent event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_ENCHANTMENT, event.getStack()) > 0) {
            event.setCanceled(true);
        }
    }

    public static void clearXp(GrindstoneEvent.OnTakeItem event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_ENCHANTMENT, event.getTopItem()) > 0) {
            event.setXp(0);
        }
    }

    public static void forceCancel(AnvilUpdateEvent event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_ENCHANTMENT, event.getLeft()) > 0) {
            event.setCanceled(true);
        }
    }
}
