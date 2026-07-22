package net.scratch221171.astralenchant.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

public class CommonConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    /** ConfigCondition が configId -> 実体 を引くための一覧 */
    private static final Map<String, ConfigEntry.BooleanEntry> CONDITION_ENTRIES = new LinkedHashMap<>();

    public static Map<String, ConfigEntry.BooleanEntry> getConditionEntries() {
        return Collections.unmodifiableMap(CONDITION_ENTRIES);
    }

    public static final ConfigEntries ENCHANTMENT_TOGGLING =
            BUILDER.comment("Enchantment Toggling").category("enchantment_toggling", EnchantmentToggling.ENTRIES);

    public static final class EnchantmentToggling {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder().worldRestart();

        public static final ConfigEntry.BooleanEntry NULLIFICATION = defineToggle(ConfigID.NULLIFICATION),
                LAST_STAND = defineToggle(ConfigID.LAST_STAND),
                ESSENCE_OF_ENCHANTMENT = defineToggle(ConfigID.ESSENCE_OF_ENCHANTMENT),
                RESILIENCE = defineToggle(ConfigID.RESILIENCE),
                FEATHER_TOUCH = defineToggle(ConfigID.FEATHER_TOUCH),
                AFFINITY = defineToggle(ConfigID.AFFINITY),
                ENDLESS_APPETITE = defineToggle(ConfigID.ENDLESS_APPETITE),
                OVERLOAD = defineToggle(ConfigID.OVERLOAD),
                EXPANSE = defineToggle(ConfigID.EXPANSE),
                REACTIVE_ARMOR = defineToggle(ConfigID.REACTIVE_ARMOR),
                CURSE_OF_IGNORANCE = defineToggle(ConfigID.CURSE_OF_IGNORANCE),
                CURSE_OF_ENCHANTMENT = defineToggle(ConfigID.CURSE_OF_ENCHANTMENT),
                DISTORTION = defineToggle(ConfigID.DISTORTION),
                ALMIGHTY = defineToggle(ConfigID.ALMIGHTY);

        private static ConfigEntry.BooleanEntry defineToggle(String configId) {
            ConfigEntry.BooleanEntry entry = BUILDER.define(configId, true);
            CONDITION_ENTRIES.put(configId, entry);
            return entry;
        }

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
