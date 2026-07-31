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
import net.scratch221171.astralenchant.datagen.entity.AEEntityLootTableProvider;

public class AELootModifierProvider extends GlobalLootModifierProvider {
    public AELootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Constants.MODID);
    }

    @Override
    protected void start() {
        add(
                "wither_wither_skeleton_skull",
                new AddTableLootModifier(
                        new LootItemCondition[] {is(EntityType.WITHER)},
                        AEEntityLootTableProvider.WITHER_SKELETON_SKULL));
        add(
                "warden_echo_shard",
                new AddTableLootModifier(
                        new LootItemCondition[] {is(EntityType.WARDEN)}, AEEntityLootTableProvider.ECHO_SHARD));
        add(
                "bogged_rotten_bone",
                new AddTableLootModifier(
                        new LootItemCondition[] {is(EntityType.BOGGED)}, AEEntityLootTableProvider.ROTTEN_BONE));
        add(
                "allay_allay_wing",
                new AddTableLootModifier(
                        new LootItemCondition[] {is(EntityType.ALLAY)}, AEEntityLootTableProvider.ALLAY_WING));
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
