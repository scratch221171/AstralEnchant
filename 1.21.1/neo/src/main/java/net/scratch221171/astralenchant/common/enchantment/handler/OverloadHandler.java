package net.scratch221171.astralenchant.common.enchantment.handler;

import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class OverloadHandler {

    public static void modifyLevels(GetEnchantmentLevelEvent event) {
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
