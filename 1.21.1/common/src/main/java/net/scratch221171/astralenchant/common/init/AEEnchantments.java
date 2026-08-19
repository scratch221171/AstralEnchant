package net.scratch221171.astralenchant.common.init;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.EnchantmentLookups;

public class AEEnchantments {

    private static final HashMap<ResourceKey<Enchantment>, Function<EnchantmentLookups, Enchantment.Builder>> LIST =
            new HashMap<>();

    public static Set<ResourceKey<Enchantment>> getAll() {
        return LIST.keySet();
    }

    public static Enchantment.Builder getBuilder(ResourceKey<Enchantment> enchantment, EnchantmentLookups lookups) {
        return LIST.get(enchantment).apply(lookups);
    }

    public static Enchantment getEnchantment(ResourceKey<Enchantment> key, EnchantmentLookups lookups) {
        return getBuilder(key, lookups).build(key.location());
    }

    public static final ResourceKey<Enchantment>
            NULLIFICATION =
                    create(
                            ID.NULLIFICATION,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.MAINHAND))),
            LAST_STAND =
                    create(
                            ID.LAST_STAND,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                                    1,
                                    3,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.ARMOR))),
            ESSENCE_OF_ENCHANTMENT =
                    create(
                            ID.ESSENCE_OF_ENCHANTMENT,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.anyItems(),
                                    1,
                                    5,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.ANY))),
            RESILIENCE =
                    create(ID.RESILIENCE, lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                                    1,
                                    3,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.CHEST))
                            .withEffect(
                                    EnchantmentEffectComponents.ATTRIBUTES,
                                    new EnchantmentAttributeEffect(
                                            ModUtils.loc(ID.RESILIENCE),
                                            lookups.attributes().getOrThrow(AEAttributes.COOLDOWN_DURATION),
                                            LevelBasedValue.perLevel(-0.1f),
                                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE))),
            FEATHER_TOUCH =
                    create(
                            ID.FEATHER_TOUCH,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.MAINHAND))),
            AFFINITY =
                    create(
                            ID.AFFINITY,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.anyItems(),
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.ANY))),
            ENDLESS_APPETITE =
                    create(ID.ENDLESS_APPETITE, lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                                    1,
                                    3,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.CHEST))
                            .withEffect(
                                    EnchantmentEffectComponents.ATTRIBUTES,
                                    new EnchantmentAttributeEffect(
                                            ModUtils.loc(ID.ENDLESS_APPETITE),
                                            lookups.attributes().getOrThrow(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER),
                                            new LevelBasedValue.LevelsSquared(0f),
                                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                            .withEffect(
                                    EnchantmentEffectComponents.ATTRIBUTES,
                                    new EnchantmentAttributeEffect(
                                            ModUtils.loc(ID.ENDLESS_APPETITE),
                                            lookups.attributes()
                                                    .getOrThrow(AEAttributes.SATURATION_LEVEL_CAP_MULTIPLIER),
                                            new LevelBasedValue.LevelsSquared(0f),
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL))),
            DEXTERITY =
                    create(
                            ID.DEXTERITY,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.LEGS))),
            OVERLOAD =
                    create(
                            ID.OVERLOAD,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.anyItems(),
                                    1,
                                    5,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.ANY))),
            EXPANSE =
                    create(
                            ID.EXPANSE,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.anyItems(),
                                    1,
                                    2,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.ANY))),
            REACTIVE_ARMOR =
                    create(
                            ID.REACTIVE_ARMOR,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.CHEST))),
            MYSTIC_REMNANTS =
                    create(
                            ID.MYSTIC_REMNANTS,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                    1,
                                    3,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.MAINHAND))),
            CURSE_OF_IGNORANCE =
                    create(
                            ID.CURSE_OF_IGNORANCE,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.anyItems(),
                                    1,
                                    1,
                                    Enchantment.constantCost(25),
                                    Enchantment.constantCost(50),
                                    8,
                                    EquipmentSlotGroup.ANY))),
            CURSE_OF_ENCHANTMENT =
                    create(
                            ID.CURSE_OF_ENCHANTMENT,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.anyItems(),
                                    1,
                                    1,
                                    Enchantment.constantCost(25),
                                    Enchantment.constantCost(50),
                                    8,
                                    EquipmentSlotGroup.ANY))),
            DISTORTION =
                    create(
                            ID.DISTORTION,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                    1,
                                    3,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.HAND))),
            // TODO: Guide
            OVER_MENDING =
                    create(
                            ID.OVER_MENDING,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                    1,
                                    5,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.MAINHAND))),
            ALMIGHTY =
                    create(
                            ID.ALMIGHTY,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                                    1,
                                    4,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    16,
                                    EquipmentSlotGroup.MAINHAND))),
            CURSE_OF_INTANGIBILITY =
                    create(
                            ID.CURSE_OF_INTANGIBILITY,
                            lookups -> Enchantment.enchantment(Enchantment.definition(
                                    lookups.items().getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                    1,
                                    1,
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    Enchantment.constantCost(Integer.MAX_VALUE),
                                    32,
                                    EquipmentSlotGroup.FEET)));

    private static ResourceKey<Enchantment> create(
            String name, Function<EnchantmentLookups, Enchantment.Builder> func) {
        var key = ResourceKey.create(Registries.ENCHANTMENT, ModUtils.loc(name));
        LIST.put(key, func);
        return key;
    }
}
