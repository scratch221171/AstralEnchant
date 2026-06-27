package net.scratch221171.astralenchant.config;

import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

public class ServerConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntries ENCHANTMENTS =
            BUILDER.comment("Enchantment settings")
                    .category("enchantment_settings", EnchantmentSettings.ENTRIES);

    public static final class EnchantmentSettings {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntries LAST_STAND =
                BUILDER.comment("Last Stand")
                        .category("last_stand", LastStand.ENTRIES);

        public static final class LastStand {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry IGNORE_BYPASSES_INVULNERABILITY_TAG =
                    BUILDER.define("ignore_bypasses_invulnerability_tag", true);

            public static final ConfigEntry.DoubleEntry BASE_COST =
                    BUILDER.defineInRange("base_cost", 10.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntry.DoubleEntry COST_RATE =
                    BUILDER.defineInRange("cost_rate", 0.9, 0, 1);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ESSENCE_OF_ENCHANTMENT =
                BUILDER.comment("Essence of Enchantment")
                        .category("essence_of_enchant", EssenceOfEnchantment.ENTRIES);

        public static final class EssenceOfEnchantment {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry INCLUDE_OVERLOAD =
                    BUILDER.define("include_overload", false);

            public static final ConfigEntry.DoubleEntry MULTIPLIER =
                    BUILDER.defineInRange("multiplier", 1.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
