package net.scratch221171.astralenchant.common.enchantment.handler;

import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class OverloadHandler {

    private static final ThreadLocal<Boolean> IS_CALCULATING = ThreadLocal.withInitial(() -> false);

    public static void modifyLevels(GetEnchantmentLevelEvent event) {
        if (IS_CALCULATING.get()) return;

        AEUtil.getEnchantmentHolder(AEEnchantments.OVERLOAD).ifPresent(overload -> {
            int bonus;
            // ApotheosisのEndersurgeGem等に対応するため、overload自身の取得においてもGetEnchantmentLevelEventを発火
            // StackOverFlow防止用のIS_CALCULATING
            IS_CALCULATING.set(true);
            try {
                // 他のリスナーの変更も含めた最新の OVERLOAD レベルを安全に取得
                bonus = event.getStack().getEnchantmentLevel(overload);
            } finally {
                IS_CALCULATING.set(false);
            }

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
