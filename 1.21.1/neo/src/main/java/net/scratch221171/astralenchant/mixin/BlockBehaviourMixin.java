package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @ModifyVariable(method = "getDestroyProgress", at = @At("STORE"), ordinal = 0)
    private float astralenchant$modifyDestroySpeed(
            float f, BlockState state, Player player, BlockGetter getter, BlockPos pos) {
        var level = AEUtil.getEnchantmentLevel(AEEnchantments.ALMIGHTY, player);
        return f <= 0 && level > 0 ? 200f / level : f;
    }
}
