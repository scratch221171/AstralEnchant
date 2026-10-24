package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.scratch221171.astralenchant.common.enchantment.handler.AlmightyHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @ModifyVariable(method = "getDestroyProgress", at = @At("STORE"), name = "f")
    private float astralenchant$modifyDestroySpeed(
            float hardness, BlockState state, Player player, BlockGetter getter, BlockPos pos) {
        return AlmightyHandler.modifyDestroySpeed(hardness, state, player);
    }
}
