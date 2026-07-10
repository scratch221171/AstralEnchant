package net.scratch221171.astralenchant.common.enchantment.handler;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.GrindstoneEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.event.ItemSetEnchantmentEvent;
import net.scratch221171.astralenchant.common.util.AEUtil;

@EventBusSubscriber(modid = Constants.MODID)
public class CurseOfEnchantmentHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    private static void disableChange(ItemSetEnchantmentEvent event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_ENCHANTMENT, event.getStack()) > 0) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    private static void clearXp(GrindstoneEvent.OnTakeItem event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_ENCHANTMENT, event.getTopItem()) > 0) {
            event.setXp(0);
        }
    }
}
