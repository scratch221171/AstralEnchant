package net.scratch221171.astralenchant.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class AETags {
    private static final String COMMON = "c";

    private static ResourceLocation commonId(String path) {
        return ResourceLocation.fromNamespaceAndPath(COMMON, path);
    }

    // Block
    public static final class Blocks {
        public static final TagKey<Block> STORAGE_BLOCKS_ARCANIUM = createBlock(commonId("storage_blocks/arcanium"));
    }

    // Item
    public static final class Items {
        public static final TagKey<Item> GEMS_ARCANE_QUARTZ = createItem(commonId("gems/arcane_quartz"));
        public static final TagKey<Item> GEMS_LUMINITE = createItem(commonId("gems/luminite"));
        public static final TagKey<Item> DUSTS_ARCANE_QUARTZ = createItem(commonId("dusts/arcane_quartz"));
        public static final TagKey<Item> TINY_DUSTS = createItem(commonId("tiny_dusts"));
        public static final TagKey<Item> TINY_DUSTS_ARCANE_QUARTZ = createItem(commonId("tiny_dusts/arcane_quartz"));
        public static final TagKey<Item> INGOTS_ARCANIUM = createItem(commonId("ingots/arcanium"));
        public static final TagKey<Item> STORAGE_BLOCKS_ARCANIUM = createItem(commonId("storage_blocks/arcanium"));
    }

    private static TagKey<Block> createBlock(final ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }

    private static TagKey<Item> createItem(final ResourceLocation name) {
        return TagKey.create(Registries.ITEM, name);
    }
}
