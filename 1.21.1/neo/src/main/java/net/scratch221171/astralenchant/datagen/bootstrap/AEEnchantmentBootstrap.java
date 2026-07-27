package net.scratch221171.astralenchant.datagen.bootstrap;

import java.util.List;
import java.util.function.BiConsumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.EnchantmentLookups;
import net.scratch221171.astralenchant.config.ConfigID;

public class AEEnchantmentBootstrap {

    public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> consumer) {
        consumer.accept(AEEnchantments.NULLIFICATION, new ConfigCondition(ConfigID.NULLIFICATION));
        consumer.accept(AEEnchantments.LAST_STAND, new ConfigCondition(ConfigID.LAST_STAND));
        consumer.accept(AEEnchantments.ESSENCE_OF_ENCHANTMENT, new ConfigCondition(ConfigID.ESSENCE_OF_ENCHANTMENT));
        consumer.accept(AEEnchantments.RESILIENCE, new ConfigCondition(ConfigID.RESILIENCE));
        consumer.accept(AEEnchantments.FEATHER_TOUCH, new ConfigCondition(ConfigID.FEATHER_TOUCH));
        consumer.accept(AEEnchantments.AFFINITY, new ConfigCondition(ConfigID.AFFINITY));
        consumer.accept(AEEnchantments.ENDLESS_APPETITE, new ConfigCondition(ConfigID.ENDLESS_APPETITE));
        consumer.accept(AEEnchantments.OVERLOAD, new ConfigCondition(ConfigID.OVERLOAD));
        consumer.accept(AEEnchantments.REACTIVE_ARMOR, new ConfigCondition(ConfigID.REACTIVE_ARMOR));
        consumer.accept(AEEnchantments.CURSE_OF_IGNORANCE, new ConfigCondition(ConfigID.CURSE_OF_IGNORANCE));
        consumer.accept(AEEnchantments.CURSE_OF_ENCHANTMENT, new ConfigCondition(ConfigID.CURSE_OF_ENCHANTMENT));
        consumer.accept(AEEnchantments.DISTORTION, new ConfigCondition(ConfigID.DISTORTION));
        consumer.accept(AEEnchantments.ALMIGHTY, new ConfigCondition(ConfigID.ALMIGHTY));

        // Compat
        consumer.accept(
                AEEnchantments.EXPANSE,
                new AndCondition(List.of(new ModLoadedCondition("curios"), new ConfigCondition(ConfigID.EXPANSE))));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var lookups = new EnchantmentLookups() {
            public HolderLookup.RegistryLookup<Item> items() {
                return context.registryLookup(Registries.ITEM).orElseThrow();
            }

            public HolderLookup.RegistryLookup<Attribute> attributes() {
                return context.registryLookup(Registries.ATTRIBUTE).orElseThrow();
            }

            public HolderSet<Item> anyItems() {
                return new AnyHolderSet<>(items());
            }
        };

        AEEnchantments.getAll().forEach(key -> context.register(key, AEEnchantments.getEnchantment(key, lookups)));
    }

    private static void register(
            BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
