package net.scratch221171.astralenchant.config;

import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

public class ClientConfig {
    // TODO: 翻訳
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntries FOOD_LEVEL_DISPLAY =
            BUILDER.comment("Food Level Display").category("food_level_Display", FoodLevelDisplay.ENTRIES);

    public static final class FoodLevelDisplay {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntry.EnumEntry<TYPE> DISPLAY_CONDITION =
                BUILDER.defineEnum(ConfigID.DISPLAY_CONDITION, TYPE.CONDITIONAL);

        public static final ConfigEntry.BooleanEntry SHOW_CAP = BUILDER.define(ConfigID.SHOW_CAP, true),
                PERCENTAGE_STYLE = BUILDER.define(ConfigID.PERCENTAGE_STYLE, false);

        public static final ConfigEntries ENTRIES = BUILDER.build();

        public enum TYPE {
            ALWAYS,
            CONDITIONAL,
            NEVER
        }
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
