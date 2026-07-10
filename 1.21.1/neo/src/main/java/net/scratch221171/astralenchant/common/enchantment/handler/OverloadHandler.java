package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.EventPriority;
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

}
