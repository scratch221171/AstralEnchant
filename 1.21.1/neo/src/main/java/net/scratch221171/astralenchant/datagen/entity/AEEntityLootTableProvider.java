package net.scratch221171.astralenchant.datagen.entity;

import java.util.function.BiConsumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEItems;

public class AEEntityLootTableProvider implements LootTableSubProvider {

    public static final ResourceKey<LootTable> WITHER_SKELETON_SKULL =
            ResourceKey.create(Registries.LOOT_TABLE, ModUtils.loc("entities/wither_skeleton_skull"));
    public static final ResourceKey<LootTable> ECHO_SHARD =
            ResourceKey.create(Registries.LOOT_TABLE, ModUtils.loc("entities/echo_shard"));

    public static final ResourceKey<LootTable> ROTTEN_BONE =
            ResourceKey.create(Registries.LOOT_TABLE, ModUtils.loc("entities/rotten_bone"));
    public static final ResourceKey<LootTable> ALLAY_WING =
            ResourceKey.create(Registries.LOOT_TABLE, ModUtils.loc("entities/allay_wing"));

    protected final HolderLookup.Provider provider;

    public AEEntityLootTableProvider(HolderLookup.Provider provider) {
        this.provider = provider;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {

        var enchantment = provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(AEEnchantments.MYSTIC_REMNANTS);
        output.accept(
                WITHER_SKELETON_SKULL,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(BinomialDistributionGenerator.binomial(2, 0.2F))
                                .add(LootItem.lootTableItem(Items.WITHER_SKELETON_SKULL))
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.0F, LevelBasedValue.perLevel(0.05F), enchantment))));
        output.accept(
                ECHO_SHARD,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(BinomialDistributionGenerator.binomial(2, 0.5F))
                                .add(LootItem.lootTableItem(Items.ECHO_SHARD))
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.0F, LevelBasedValue.perLevel(0.05F), enchantment))));
        output.accept(
                ROTTEN_BONE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(BinomialDistributionGenerator.binomial(3, 0.3F))
                                .add(LootItem.lootTableItem(AEItems.ROTTEN_BONE.get()))
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.0F, LevelBasedValue.perLevel(0.05F), enchantment))));
        output.accept(
                ALLAY_WING,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(BinomialDistributionGenerator.binomial(2, 0.5F))
                                .add(LootItem.lootTableItem(AEItems.ALLAY_WING.get()))
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.0F, LevelBasedValue.perLevel(0.05F), enchantment))));
    }
}
