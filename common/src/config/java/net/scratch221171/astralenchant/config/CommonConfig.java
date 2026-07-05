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

        public static final ConfigEntry.BooleanEntry NULLIFICATION = defineToggle("nullification");
        public static final ConfigEntry.BooleanEntry LAST_STAND = defineToggle("last_stand");
        public static final ConfigEntry.BooleanEntry ESSENCE_OF_ENCHANTMENT = defineToggle("essence_of_enchantment");
        public static final ConfigEntry.BooleanEntry REACTIVE_ARMOR = defineToggle("reactive_armor");
        public static final ConfigEntry.BooleanEntry OVERLOAD = defineToggle("overload");


//        public static final ConfigEntry.BooleanEntry NULLIFICATION = defineToggle(Constants.ID.NULLIFICATION);
//        public static final ConfigEntry.BooleanEntry LAST_STAND = defineToggle(Constants.ID.LAST_STAND);
//        public static final ConfigEntry.BooleanEntry ITEM_PROTECTION = defineToggle(Constants.ID.ITEM_PROTECTION);
//        public static final ConfigEntry.BooleanEntry ESSENCE_OF_ENCHANTMENT = defineToggle(Constants.ID.ESSENCE_OF_ENCHANTMENT);
//        public static final ConfigEntry.BooleanEntry COOLDOWN_REDUCTION = defineToggle(Constants.ID.COOLDOWN_REDUCTION);
//        public static final ConfigEntry.BooleanEntry FEATHER_TOUCH = defineToggle(Constants.ID.FEATHER_TOUCH);
//        public static final ConfigEntry.BooleanEntry ADVENTURERS_LORE = defineToggle(Constants.ID.ADVENTURERS_LORE);
//        public static final ConfigEntry.BooleanEntry COMPATIBILITY = defineToggle(Constants.ID.COMPATIBILITY);
//        public static final ConfigEntry.BooleanEntry ENDLESS_APPETITE = defineToggle(Constants.ID.ENDLESS_APPETITE);
//        public static final ConfigEntry.BooleanEntry MOMENTUM = defineToggle(Constants.ID.MOMENTUM);
//        public static final ConfigEntry.BooleanEntry INSTANT_TELEPORT = defineToggle(Constants.ID.INSTANT_TELEPORT);
//        public static final ConfigEntry.BooleanEntry OVERLOAD = defineToggle(Constants.ID.OVERLOAD);
//        public static final ConfigEntry.BooleanEntry SLOT_EXPANSION = defineToggle(Constants.ID.SLOT_EXPANSION);
//        public static final ConfigEntry.BooleanEntry REACTIVE_ARMOR = defineToggle(Constants.ID.REACTIVE_ARMOR);
//        public static final ConfigEntry.BooleanEntry MYSTIC_REMNANTS = defineToggle(Constants.ID.MYSTIC_REMNANTS);
//        public static final ConfigEntry.BooleanEntry CURSE_OF_IGNORANCE = defineToggle(Constants.ID.CURSE_OF_IGNORANCE);
//        public static final ConfigEntry.BooleanEntry CURSE_OF_ENCHANTMENT = defineToggle(Constants.ID.CURSE_OF_ENCHANTMENT);
//        public static final ConfigEntry.BooleanEntry DISTORTION = defineToggle(Constants.ID.DISTORTION);
//        public static final ConfigEntry.BooleanEntry OVER_MENDING = defineToggle(Constants.ID.OVER_MENDING);

        private static ConfigEntry.BooleanEntry defineToggle(String configId) {
            ConfigEntry.BooleanEntry entry = BUILDER.define(configId, true);
            CONDITION_ENTRIES.put(configId, entry);
            return entry;
        }

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
