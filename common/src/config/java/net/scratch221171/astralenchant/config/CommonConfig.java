package net.scratch221171.astralenchant.config;

import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    /** ConfigCondition が configId -> 実体 を引くための一覧 */
    private static final Map<String, ConfigEntry.BooleanEntry> CONDITION_ENTRIES = new LinkedHashMap<>();

    public static Map<String, ConfigEntry.BooleanEntry> getConditionEntries() {
        return Collections.unmodifiableMap(CONDITION_ENTRIES);
    }

    public static final ConfigEntries ENCHANTMENT_TOGGLING =
            BUILDER.comment("Enchantment Toggling")
                    .category("enchantment_toggling", EnchantmentToggling.ENTRIES);

    public static final class EnchantmentToggling {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntry.BooleanEntry NULLIFICATION = defineToggle(ConfigID.NULLIFICATION);
        public static final ConfigEntry.BooleanEntry LAST_STAND = defineToggle(ConfigID.LAST_STAND);
        public static final ConfigEntry.BooleanEntry ITEM_PROTECTION = defineToggle(ConfigID.ITEM_PROTECTION);
        public static final ConfigEntry.BooleanEntry ESSENCE_OF_ENCHANTMENT = defineToggle(ConfigID.ESSENCE_OF_ENCHANTMENT);
        public static final ConfigEntry.BooleanEntry RESILIENCE = defineToggle(ConfigID.RESILIENCE);
        public static final ConfigEntry.BooleanEntry FEATHER_TOUCH = defineToggle(ConfigID.FEATHER_TOUCH);
        public static final ConfigEntry.BooleanEntry ADVENTURERS_LORE = defineToggle(ConfigID.ADVENTURERS_LORE);
        public static final ConfigEntry.BooleanEntry AFFINITY = defineToggle(ConfigID.AFFINITY);
        public static final ConfigEntry.BooleanEntry ENDLESS_APPETITE = defineToggle(ConfigID.ENDLESS_APPETITE);
        public static final ConfigEntry.BooleanEntry MOMENTUM = defineToggle(ConfigID.MOMENTUM);
        public static final ConfigEntry.BooleanEntry INSTANT_TELEPORT = defineToggle(ConfigID.INSTANT_TELEPORT);
        public static final ConfigEntry.BooleanEntry OVERLOAD = defineToggle(ConfigID.OVERLOAD);
        public static final ConfigEntry.BooleanEntry SLOT_EXPANSION = defineToggle(ConfigID.SLOT_EXPANSION);
        public static final ConfigEntry.BooleanEntry REACTIVE_ARMOR = defineToggle(ConfigID.REACTIVE_ARMOR);
        public static final ConfigEntry.BooleanEntry MYSTIC_REMNANTS = defineToggle(ConfigID.MYSTIC_REMNANTS);
        public static final ConfigEntry.BooleanEntry CURSE_OF_IGNORANCE = defineToggle(ConfigID.CURSE_OF_IGNORANCE);
        public static final ConfigEntry.BooleanEntry CURSE_OF_ENCHANTMENT = defineToggle(ConfigID.CURSE_OF_ENCHANTMENT);
        public static final ConfigEntry.BooleanEntry DISTORTION = defineToggle(ConfigID.DISTORTION);
        public static final ConfigEntry.BooleanEntry OVER_MENDING = defineToggle(ConfigID.OVER_MENDING);

        private static ConfigEntry.BooleanEntry defineToggle(String configId) {
            ConfigEntry.BooleanEntry entry = BUILDER.define(configId, true);
            CONDITION_ENTRIES.put(configId, entry);
            return entry;
        }

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
