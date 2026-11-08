package net.scratch221171.astralenchant.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.scratch221171.astralenchant.common.inventory.EnchantersWorkbenchMenu;
import org.jspecify.annotations.NonNull;

public class EnchantersWorkbenchBlock extends Block {
    public static final MapCodec<EnchantersWorkbenchBlock> CODEC = simpleCodec(EnchantersWorkbenchBlock::new);

    public EnchantersWorkbenchBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected MenuProvider getMenuProvider(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos) {
        return new SimpleMenuProvider(
                (id, inv, player) -> new EnchantersWorkbenchMenu(id, inv, ContainerLevelAccess.create(level, pos)),
                Component.translatable("container.astralenchant.enchanters_workbench"));
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(
            @NonNull BlockState state,
            Level level,
            @NonNull BlockPos pos,
            @NonNull Player player,
            @NonNull BlockHitResult hit) {
        if (!level.isClientSide) player.openMenu(state.getMenuProvider(level, pos));
        return InteractionResult.SUCCESS;
    }
}
