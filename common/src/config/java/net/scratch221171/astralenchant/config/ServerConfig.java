package net.scratch221171.astralenchant.config;

import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

public class ServerConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntries ENCHANTMENT_SETTINGS =
            BUILDER.comment("Enchantment Settings")
                    .category("enchantment_settings", EnchantmentSettings.ENTRIES);

    public static final class EnchantmentSettings {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntries LAST_STAND =
                BUILDER.comment("Last Stand")
                        .category("last_stand", LastStand.ENTRIES);

        public static final class LastStand {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry IGNORE_BYPASSES_INVULNERABILITY_TAG =
                    BUILDER.define(ConfigID.IGNORE_BYPASSES_INVULNERABILITY_TAG, true);

            public static final ConfigEntry.DoubleEntry BASE_COST =
                    BUILDER.defineInRange(ConfigID.BASE_COST, 10.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntry.DoubleEntry COST_RATE =
                    BUILDER.defineInRange(ConfigID.COST_RATE, 0.9, 0, 1);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ESSENCE_OF_ENCHANTMENT =
                BUILDER.comment("Essence of Enchantment")
                        .category("essence_of_enchantment", EssenceOfEnchantment.ENTRIES);

        public static final class EssenceOfEnchantment {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry INCLUDE_OVERLOAD_IN_TOTAL =
                    BUILDER.define(ConfigID.INCLUDE_OVERLOAD_IN_TOTAL, false);

            public static final ConfigEntry.DoubleEntry MULTIPLIER =
                    BUILDER.defineInRange(ConfigID.MULTIPLIER, 1.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ALMIGHTY =
                BUILDER.comment("Almighty")
                        .category("almighty", Almighty.ENTRIES);

        public static final class Almighty {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry EFFECTIVE_FOR_ALL_BLOCKS
                    = BUILDER.define(ConfigID.EFFECTIVE_FOR_ALL_BLOCKS, true);

            public static final ConfigEntry.DoubleEntry UNBREAKABLE_BLOCK_HARDNESS
                    = BUILDER.defineInRange(ConfigID.UNBREAKABLE_BLOCK_HARDNESS, 100.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
