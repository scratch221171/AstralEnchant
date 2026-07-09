package net.scratch221171.astralenchant.data.bootstrap;

import java.util.function.BiConsumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.config.ConfigID;

public class AEEnchantmentBootstrap {

    public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> consumer) {
        consumer.accept(AEEnchantments.NULLIFICATION, new ConfigCondition(ConfigID.NULLIFICATION));
        consumer.accept(AEEnchantments.LAST_STAND, new ConfigCondition(ConfigID.LAST_STAND));
        consumer.accept(AEEnchantments.ESSENCE_OF_ENCHANTMENT, new ConfigCondition(ConfigID.ESSENCE_OF_ENCHANTMENT));
        consumer.accept(AEEnchantments.RESILIENCE, new ConfigCondition(ConfigID.RESILIENCE));
        consumer.accept(AEEnchantments.FEATHER_TOUCH, new ConfigCondition(ConfigID.FEATHER_TOUCH));
        /*consumer.accept(AEEnchantments.ADVENTURERS_LORE, new ConfigCondition(ConfigID.ADVENTURERS_LORE));
        consumer.accept(AEEnchantments.COMPATIBILITY, new ConfigCondition(ConfigID.COMPATIBILITY));
        consumer.accept(AEEnchantments.ENDLESS_APPETITE, new ConfigCondition(ConfigID.ENDLESS_APPETITE));
        consumer.accept(AEEnchantments.MOMENTUM, new ConfigCondition(ConfigID.MOMENTUM));
        consumer.accept(AEEnchantments.INSTANT_TELEPORT, new ConfigCondition(ConfigID.INSTANT_TELEPORT));*/
        consumer.accept(AEEnchantments.OVERLOAD, new ConfigCondition(ConfigID.OVERLOAD));
        consumer.accept(AEEnchantments.REACTIVE_ARMOR, new ConfigCondition(ConfigID.REACTIVE_ARMOR));
        /*consumer.accept(AEEnchantments.MYSTIC_REMNANTS, new ConfigCondition(ConfigID.MYSTIC_REMNANTS));
        consumer.accept(AEEnchantments.CURSE_OF_IGNORANCE, new ConfigCondition(ConfigID.CURSE_OF_IGNORANCE));
        consumer.accept(AEEnchantments.CURSE_OF_ENCHANTMENT, new ConfigCondition(ConfigID.CURSE_OF_ENCHANTMENT));
        consumer.accept(AEEnchantments.DISTORTION, new ConfigCondition(ConfigID.DISTORTION));
        consumer.accept(AEEnchantments.OVER_MENDING, new ConfigCondition(ConfigID.OVER_MENDING));
        ;
        // Compat
        consumer.accept(
                AEEnchantments.SLOT_EXPANSION,
                new AndCondition(
                        List.of(new ModLoadedCondition("accessories"), new ConfigCondition(ConfigID.SLOT_EXPANSION))));
        consumer.accept(
                AEEnchantments.ITEM_PROTECTION,
                new AndCondition(
                        List.of(new ModLoadedCondition("l2hostility"), new ConfigCondition(ConfigID.ITEM_PROTECTION))));
        */
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderLookup.RegistryLookup<Item> itemLookup =
                context.registryLookup(Registries.ITEM).orElseThrow();

        HolderSet<Item> anyHolderSet = new AnyHolderSet<>(itemLookup);
        HolderSet<Item> armorTag = itemLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE);
        HolderSet<Item> headTag = itemLookup.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE);
        HolderSet<Item> chestTag = itemLookup.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
        HolderSet<Item> footTag = itemLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
        HolderSet<Item> weaponTag = itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
        HolderSet<Item> miningTag = itemLookup.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE);
        //        HolderSet<Item> bundleTag = itemLookup.getOrThrow(AstralEnchantmentTags.Items.BUNDLE);
        HolderSet<Item> durabilityTag = itemLookup.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE);

        register(
                context,
                AEEnchantments.NULLIFICATION,
                Enchantment.enchantment(Enchantment.definition(
                        weaponTag,
                        1,
                        1,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        32,
                        EquipmentSlotGroup.MAINHAND)));

        register(
                context,
                AEEnchantments.LAST_STAND,
                Enchantment.enchantment(Enchantment.definition(
                        armorTag,
                        1,
                        3,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        32,
                        EquipmentSlotGroup.ARMOR)));
        /*
                register(
                        context,
                        AEEnchantments.ITEM_PROTECTION,
                        Enchantment.enchantment(Enchantment.definition(
                                anyHolderSet,
                                1,
                                1,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                16,
                                EquipmentSlotGroup.ANY)));
        */
        register(
                context,
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                Enchantment.enchantment(Enchantment.definition(
                        anyHolderSet,
                        1,
                        5,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        32,
                        EquipmentSlotGroup.ANY)));

        register(
                context,
                AEEnchantments.RESILIENCE,
                Enchantment.enchantment(Enchantment.definition(
                                chestTag,
                                1,
                                3,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                16,
                                EquipmentSlotGroup.CHEST))
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ModUtils.loc("cr_bonus"),
                                        AEAttributes.COOLDOWN_DURATION,
                                        LevelBasedValue.perLevel(-0.15f),
                                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE)));

        register(
                context,
                AEEnchantments.FEATHER_TOUCH,
                Enchantment.enchantment(Enchantment.definition(
                        miningTag,
                        1,
                        1,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        16,
                        EquipmentSlotGroup.MAINHAND)));
        /*
                register(
                        context,
                        AEEnchantments.ADVENTURERS_LORE,
                        Enchantment.enchantment(Enchantment.definition(
                                footTag,
                                1,
                                3,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                8,
                                EquipmentSlotGroup.FEET)));

                register(
                        context,
                        AEEnchantments.COMPATIBILITY,
                        Enchantment.enchantment(Enchantment.definition(
                                bundleTag,
                                1,
                                1,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                32,
                                EquipmentSlotGroup.ANY)));

                register(
                        context,
                        AEEnchantments.ENDLESS_APPETITE,
                        Enchantment.enchantment(Enchantment.definition(
                                chestTag,
                                1,
                                1,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                32,
                                EquipmentSlotGroup.CHEST)));

                register(
                        context,
                        AEEnchantments.MOMENTUM,
                        Enchantment.enchantment(Enchantment.definition(
                                chestTag,
                                1,
                                1,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                16,
                                EquipmentSlotGroup.CHEST)));

                register(
                        context,
                        AEEnchantments.INSTANT_TELEPORT,
                        Enchantment.enchantment(Enchantment.definition(
                                headTag,
                                1,
                                4,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                16,
                                EquipmentSlotGroup.HEAD)));
        */
        register(
                context,
                AEEnchantments.OVERLOAD,
                Enchantment.enchantment(Enchantment.definition(
                        anyHolderSet,
                        1,
                        5,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        32,
                        EquipmentSlotGroup.ANY)));
        /*
                register(
                        context,
                        AEEnchantments.SLOT_EXPANSION,
                        Enchantment.enchantment(Enchantment.definition(
                                anyHolderSet,
                                1,
                                3,
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                Enchantment.constantCost(Integer.MAX_VALUE),
                                16,
                                EquipmentSlotGroup.ANY)));
        */
        register(
                context,
                AEEnchantments.REACTIVE_ARMOR,
                Enchantment.enchantment(Enchantment.definition(
                        chestTag,
                        1,
                        1,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        16,
                        EquipmentSlotGroup.CHEST)));
        /*
            register(
                    context,
                    AEEnchantments.MYSTIC_REMNANTS,
                    Enchantment.enchantment(Enchantment.definition(
                            weaponTag,
                            1,
                            3,
                            Enchantment.constantCost(Integer.MAX_VALUE),
                            Enchantment.constantCost(Integer.MAX_VALUE),
                            4,
                            EquipmentSlotGroup.MAINHAND)));

            register(
                    context,
                    AEEnchantments.CURSE_OF_IGNORANCE,
                    Enchantment.enchantment(Enchantment.definition(
                            anyHolderSet,
                            1,
                            1,
                            Enchantment.constantCost(25),
                            Enchantment.constantCost(50),
                            4,
                            EquipmentSlotGroup.ANY)));

            register(
                    context,
                    AEEnchantments.CURSE_OF_ENCHANTMENT,
                    Enchantment.enchantment(Enchantment.definition(
                            anyHolderSet,
                            1,
                            1,
                            Enchantment.constantCost(25),
                            Enchantment.constantCost(50),
                            4,
                            EquipmentSlotGroup.ANY)));

            register(
                    context,
                    AEEnchantments.DISTORTION,
                    Enchantment.enchantment(Enchantment.definition(
                            anyHolderSet,
                            1,
                            3,
                            Enchantment.constantCost(Integer.MAX_VALUE),
                            Enchantment.constantCost(Integer.MAX_VALUE),
                            16,
                            EquipmentSlotGroup.HAND)));

            register(
                    context,
                    AEEnchantments.OVER_MENDING,
                    Enchantment.enchantment(Enchantment.definition(
                                    durabilityTag,
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.ANY))
                            .withEffect(EnchantmentEffectComponents.TICK, new OverMendingEffect()));
        */
        register(
                context,
                AEEnchantments.ALMIGHTY,
                Enchantment.enchantment(Enchantment.definition(
                        miningTag,
                        1,
                        4,
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        Enchantment.constantCost(Integer.MAX_VALUE),
                        16,
                        EquipmentSlotGroup.MAINHAND)));
    }

    private static void register(
            BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
