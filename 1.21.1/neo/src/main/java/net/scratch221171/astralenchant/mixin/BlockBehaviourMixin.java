package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GameMasterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @ModifyVariable(method = "getDestroyProgress", at = @At("STORE"), ordinal = 0)
    private float astralenchant$modifyDestroySpeed(
            float hardness, BlockState state, Player player, BlockGetter getter, BlockPos pos) {
        if (state.getBlock() instanceof GameMasterBlock) return hardness;
        if (hardness >= 0) return hardness;

        int level = AEUtil.getEnchantmentLevel(AEEnchantments.ALMIGHTY, player.getMainHandItem());
        if (level < 4) return hardness; // lv1~3では岩盤は破壊不可

        return (float) ServerConfig.EnchantmentSettings.Almighty.UNBREAKABLE_BLOCK_HARDNESS.getAsDouble();
    }
}
