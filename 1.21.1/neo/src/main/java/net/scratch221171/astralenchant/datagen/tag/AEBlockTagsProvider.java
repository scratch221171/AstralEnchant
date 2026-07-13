package net.scratch221171.astralenchant.datagen.tag;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.tag.AETags;
import org.jspecify.annotations.NonNull;

public class AEBlockTagsProvider extends BlockTagsProvider {
    public AEBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Constants.MODID, null);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider lookupProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(AEBlocks.ARCANIUM_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(AEBlocks.ARCANIUM_BLOCK.get());

        tag(AETags.Blocks.STORAGE_BLOCKS_ARCANIUM).add(AEBlocks.ARCANIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(AETags.Blocks.STORAGE_BLOCKS_ARCANIUM);
    }
}
