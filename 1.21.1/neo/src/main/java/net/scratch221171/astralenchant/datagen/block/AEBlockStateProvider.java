package net.scratch221171.astralenchant.datagen.block;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.registry.AEBlocks;

public class AEBlockStateProvider extends BlockStateProvider {
    public AEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Constants.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        addFullBlock(AEBlocks.ARCANIUM_BLOCK);
        simpleBlockWithItem(
                AEBlocks.ENCHANTERS_WORKBENCH.get(),
                models().cubeBottomTop(
                                ID.ENCHANTERS_WORKBENCH,
                                modLoc("block/enchanters_workbench_side"),
                                mcLoc("block/oak_planks"),
                                modLoc("block/enchanters_workbench_top")));
    }

    private void addFullBlock(DeferredBlock<? extends Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
