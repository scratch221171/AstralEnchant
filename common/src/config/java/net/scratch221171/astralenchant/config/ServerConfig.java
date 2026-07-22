package net.scratch221171.astralenchant.config;

import java.util.List;
import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

public class ServerConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntries ENCHANTMENT_SETTINGS =
            BUILDER.comment("Enchantment Settings").category("enchantment_settings", EnchantmentSettings.ENTRIES);

    public static final class EnchantmentSettings {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntries NULLIFICATION =
                BUILDER.comment("Nullification").category(ConfigID.NULLIFICATION, Nullification.ENTRIES);

        public static final class Nullification {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            // ResourceLocationを使えないのでしょうがない
            public static final ConfigEntry.ListEntry<String> EXTRA_TAG = BUILDER.defineList(
                    ConfigID.EXTRA_TAG,
                    List.of(
                            "minecraft:bypasses_armor",
                            "minecraft:bypasses_cooldown",
                            "minecraft:bypasses_effects",
                            "minecraft:bypasses_enchantments",
                            "minecraft:bypasses_invulnerability",
                            "minecraft:bypasses_shield"),
                    () -> "minecraft:bypasses_armor",
                    e -> true);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries LAST_STAND =
                BUILDER.comment("Last Stand").category(ConfigID.LAST_STAND, LastStand.ENTRIES);

        public static final class LastStand {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry IGNORE_BYPASSES_INVULNERABILITY_TAG =
                    BUILDER.define(ConfigID.IGNORE_BYPASSES_INVULNERABILITY_TAG, true);

            public static final ConfigEntry.DoubleEntry BASE_COST =
                    BUILDER.defineInRange(ConfigID.BASE_COST, 10.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntry.DoubleEntry COST_RATE =
                    BUILDER.defineInRange(ConfigID.COST_RATE, 0.9, 0, 1);

            public static final ConfigEntry.DoubleEntry MAX_COST =
                    BUILDER.defineInRange(ConfigID.MAX_COST, Double.MAX_VALUE, 0.0, Double.MAX_VALUE);

            public static final ConfigEntry.DoubleEntry RECOVER_HEALTH =
                    BUILDER.defineInRange(ConfigID.RECOVER_HEALTH, 1.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ESSENCE_OF_ENCHANTMENT = BUILDER.comment("Essence of Enchantment")
                .category(ConfigID.ESSENCE_OF_ENCHANTMENT, EssenceOfEnchantment.ENTRIES);

        public static final class EssenceOfEnchantment {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry INCLUDE_OVERLOAD_IN_TOTAL =
                    BUILDER.define(ConfigID.INCLUDE_OVERLOAD_IN_TOTAL, false);

            public static final ConfigEntry.DoubleEntry MULTIPLIER =
                    BUILDER.defineInRange(ConfigID.MULTIPLIER, 1.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntry.IntEntry MAX_TOTAL_LEVEL =
                    BUILDER.defineInRange(ConfigID.MAX_TOTAL_LEVEL, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);

            public static final ConfigEntry.BooleanEntry CURIOS_COMPAT = BUILDER.define(ConfigID.CURIOS_COMPAT, true);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries FEATHER_TOUCH =
                BUILDER.comment("Feather touch").category(ConfigID.FEATHER_TOUCH, FeatherTouch.ENTRIES);

        public static final class FeatherTouch {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry SAVE_BLOCK_ENTITY =
                    BUILDER.define(ConfigID.SAVE_BLOCK_ENTITY, true);

            public static final ConfigEntry.BooleanEntry SAVE_BLOCK_STATE =
                    BUILDER.define(ConfigID.SAVE_BLOCK_STATE, true);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries EXPANSE =
                BUILDER.comment("Expanse").category(ConfigID.EXPANSE, Expanse.ENTRIES);

        public static final class Expanse {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry STACKABLE = BUILDER.define(ConfigID.STACKABLE, false);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final class ReactiveArmor {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            // ResourceLocationを使えないのでしょうがない
            public static final ConfigEntry.ListEntry<String> DISABLED_TAG = BUILDER.defineList(
                    ConfigID.DISABLED_TAG,
                    List.of("minecraft:bypasses_armor", "minecraft:bypasses_enchantments"),
                    () -> "minecraft:bypasses_armor",
                    e -> true);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries CURSE_OF_IGNORANCE =
                BUILDER.comment("Curse of ignorance").category(ConfigID.CURSE_OF_IGNORANCE, CurseOfIgnorance.ENTRIES);

        public static final class CurseOfIgnorance {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry HIDE_NAME = BUILDER.define(ConfigID.HIDE_NAME, true);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries DISTORTION =
                BUILDER.comment("Distortion").category(ConfigID.DISTORTION, Distortion.ENTRIES);

        public static final class Distortion {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.DoubleEntry ANGLE_PER_LEVEL =
                    BUILDER.defineInRange(ConfigID.ANGLE_PER_LEVEL, 7.5, 0.0, Double.MAX_VALUE);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ALMIGHTY =
                BUILDER.comment("Almighty").category(ConfigID.ALMIGHTY, Almighty.ENTRIES);

        public static final class Almighty {
            private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

            public static final ConfigEntry.BooleanEntry EFFECTIVE_FOR_ALL_BLOCKS =
                    BUILDER.define(ConfigID.EFFECTIVE_FOR_ALL_BLOCKS, true);

            public static final ConfigEntry.BooleanEntry CAN_BREAK_UNBREAKABLE =
                    BUILDER.define(ConfigID.CAN_BREAK_UNBREAKABLE, true);

            public static final ConfigEntry.DoubleEntry UNBREAKABLE_BLOCK_HARDNESS =
                    BUILDER.defineInRange(ConfigID.UNBREAKABLE_BLOCK_HARDNESS, 100.0, 0.0, Double.MAX_VALUE);

            public static final ConfigEntries ENTRIES = BUILDER.build();
        }

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
