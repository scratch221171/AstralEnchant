package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;

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
            var blacklist = getOverloadBlacklist();
            for (var entry : enchantments.keySet()) {
                if (!entry.is(AEEnchantments.OVERLOAD) && blacklist.stream().noneMatch(entry::is)) {
                    int current = enchantments.getLevel(entry);
                    enchantments.upgrade(entry, current + bonus);
                }
            }
        });
    }

    private static List<ResourceLocation> getOverloadBlacklist() {
        List<ResourceLocation> enchantments = new ArrayList<>();
        boolean error = false;
        for (String entry : ServerConfig.EnchantmentSettings.Overload.BLACKLIST.get()) {
            try {
                Optional.of(entry).map(ResourceLocation::tryParse).map(enchantments::add);
            } catch (Exception e) {
                Constants.LOGGER.error("Error while loading tags for Overload!", e);
                Constants.LOGGER.error("using default value instead...");
                error = true;
            }
        }
        if (error) {
            for (String entry : ServerConfig.EnchantmentSettings.Overload.BLACKLIST.defaultValue()) {
                Optional.of(entry).map(ResourceLocation::tryParse).map(enchantments::add);
            }
        }
        return enchantments;
    }
}
