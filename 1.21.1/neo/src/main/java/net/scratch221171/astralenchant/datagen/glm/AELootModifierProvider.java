package net.scratch221171.astralenchant.datagen.glm;

import java.util.concurrent.CompletableFuture;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AELootTables;

public class AELootModifierProvider extends GlobalLootModifierProvider {
    public AELootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Constants.MODID);
    }

    @Override
    protected void start() {
        add(
                ID.ENTITIES_NETHER_WART,
                new AddTableLootModifier(new LootItemCondition[] {is(EntityType.WITCH)}, AELootTables.NETHER_WART));
        add(
                ID.ENTITIES_WITHER_SKELETON_SKULL,
                new AddTableLootModifier(
                        new LootItemCondition[] {is(EntityType.WITHER)}, AELootTables.WITHER_SKELETON_SKULL));
        add(
                ID.ENTITIES_ECHO_SHARD,
                new AddTableLootModifier(new LootItemCondition[] {is(EntityType.WARDEN)}, AELootTables.ECHO_SHARD));
        add(
                ID.ENTITIES_ROTTEN_BONE,
                new AddTableLootModifier(new LootItemCondition[] {is(EntityType.BOGGED)}, AELootTables.ROTTEN_BONE));
        add(
                ID.ENTITIES_ALLAY_WING,
                new AddTableLootModifier(new LootItemCondition[] {is(EntityType.ALLAY)}, AELootTables.ALLAY_WING));
        add(
                ID.ENTITIES_VEX_WING,
                new AddTableLootModifier(new LootItemCondition[] {is(EntityType.VEX)}, AELootTables.VEX_WING));
    }

    private static LootItemCondition is(EntityType<?> type) {
        return LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(type))
                .build();
    }

    private static LootItemCondition is(TagKey<EntityType<?>> tag) {
        return LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(tag))
                .build();
    }
}
