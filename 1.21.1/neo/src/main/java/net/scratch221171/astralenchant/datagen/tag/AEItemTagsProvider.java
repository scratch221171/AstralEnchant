package net.scratch221171.astralenchant.datagen.tag;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.tag.AETags;
import org.jspecify.annotations.NonNull;

public class AEItemTagsProvider extends ItemTagsProvider {
    public AEItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, Constants.MODID, null);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        tag(AETags.Items.GEMS_ARCANE_QUARTZ).add(AEItems.ARCANE_QUARTZ.get());
        tag(Tags.Items.GEMS).addTag(AETags.Items.GEMS_ARCANE_QUARTZ);

        tag(AETags.Items.DUSTS_ARCANE_QUARTZ).add(AEItems.ARCANE_QUARTZ_DUST.get());
        tag(Tags.Items.DUSTS).addTag(AETags.Items.DUSTS_ARCANE_QUARTZ);

        tag(AETags.Items.INGOTS_ARCANIUM).add(AEItems.ARCANIUM_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(AETags.Items.INGOTS_ARCANIUM);

        copy(AETags.Blocks.STORAGE_BLOCKS_ARCANIUM, AETags.Items.STORAGE_BLOCKS_ARCANIUM);
        tag(Tags.Items.STORAGE_BLOCKS).addTag(AETags.Items.STORAGE_BLOCKS_ARCANIUM);

        tag(ItemTags.HEAD_ARMOR).add(AEItems.ARCANIUM_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(AEItems.ARCANIUM_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(AEItems.ARCANIUM_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(AEItems.ARCANIUM_BOOTS.get());
    }
}
