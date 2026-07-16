package net.scratch221171.astralenchant.common.registry;

import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.block.EnchantersWorkbenchBlock;

public class AEBlocks {
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(Constants.MODID);

    public static final DeferredBlock<Block> ARCANIUM_BLOCK = registerSimpleBlock(
            ID.ARCANIUM_BLOCK,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL));

    // 仕様: 作業台(Blocks.CRAFTING_TABLE)と全く同じプロパティ(硬度・爆破耐性・適正ツール等)を踏襲する。
    // 【要検証】BlockBehaviour.Properties.ofFullCopy(Block) が使用中のNeoForge/MC 1.21.1バージョンに
    // 実在するか確認してください。存在しない場合は Properties.of().strength(2.5F).sound(SoundType.WOOD) 等を
    // 手動で作業台の値に合わせて設定してください。
    public static final DeferredBlock<EnchantersWorkbenchBlock> ENCHANTERS_WORKBENCH = registerBlock(
            ID.ENCHANTERS_WORKBENCH,
            () -> new EnchantersWorkbenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));

    private static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties props) {
        var holder = REGISTER.registerSimpleBlock(name, props);
        AEItems.REGISTER.registerSimpleBlockItem(holder);
        return holder;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> supplier) {
        var holder = REGISTER.register(name, supplier);
        AEItems.REGISTER.registerSimpleBlockItem(holder);
        return holder;
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
