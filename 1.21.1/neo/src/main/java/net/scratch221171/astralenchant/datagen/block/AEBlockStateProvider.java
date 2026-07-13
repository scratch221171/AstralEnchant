package net.scratch221171.astralenchant.datagen.block;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.registry.AEBlocks;

public class AEBlockStateProvider extends BlockStateProvider {
    public AEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Constants.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        addFullBlock(AEBlocks.ARCANIUM_BLOCK);
    }

    private void addFullBlock(DeferredBlock<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
