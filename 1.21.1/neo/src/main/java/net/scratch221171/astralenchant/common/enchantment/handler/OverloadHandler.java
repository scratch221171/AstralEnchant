package net.scratch221171.astralenchant.common.enchantment.handler;

import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class OverloadHandler {

    public static void modifyLevels(GetEnchantmentLevelEvent event) {
        AEUtil.getEnchantmentHolder(AEEnchantments.OVERLOAD).ifPresent(overload -> {
            int bonus = event.getStack().getTagEnchantments().getLevel(overload);

            var enchantments = event.getEnchantments();
            for (var entry : enchantments.keySet()) {
                if (!entry.is(AEEnchantments.OVERLOAD)) {
                    int current = enchantments.getLevel(entry);
                    enchantments.upgrade(entry, current + bonus);
                }
            }
        });
    }
}
