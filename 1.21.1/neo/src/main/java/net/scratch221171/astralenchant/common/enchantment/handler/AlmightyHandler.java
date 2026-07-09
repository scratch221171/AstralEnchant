package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.BlockState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;

public class AlmightyHandler {

    // Almightyによる適正判定のエントリポイント
    // lv4は万能、lv1~3はティアのみ引き上げ
    public static boolean isAlmightyCorrectTool(ItemStack tool, BlockState state) {
        int level = AEUtil.getEnchantmentLevel(AEEnchantments.ALMIGHTY, tool);
        if (level <= 0) return false;
        if (level >= 4) return true;

        var virtualTier = virtualTierForLevel(level);
        if (virtualTier == null) return false;

        return !state.is(virtualTier.getIncorrectBlocksForDrops())
                && (ServerConfig.EnchantmentSettings.Almighty.EFFECTIVE_FOR_ALL_BLOCKS.getAsBoolean()
                        || state.is(BlockTags.MINEABLE_WITH_PICKAXE)
                        || state.is(BlockTags.MINEABLE_WITH_AXE)
                        || state.is(BlockTags.MINEABLE_WITH_SHOVEL)
                        || state.is(BlockTags.MINEABLE_WITH_HOE));
    }

    private static Tier virtualTierForLevel(int level) {
        return switch (level) {
            case 1 -> Tiers.IRON;
            case 2 -> Tiers.DIAMOND;
            case 3 -> Tiers.NETHERITE;
            default -> null;
        };
    }

    // 破壊速度はツール自体の素材依存
    public static float getVirtualDestroySpeed(Tier tier, BlockState state) {
        if (ServerConfig.EnchantmentSettings.Almighty.EFFECTIVE_FOR_ALL_BLOCKS.getAsBoolean()
                || state.is(BlockTags.MINEABLE_WITH_PICKAXE)
                || state.is(BlockTags.MINEABLE_WITH_AXE)
                || state.is(BlockTags.MINEABLE_WITH_SHOVEL)
                || state.is(BlockTags.MINEABLE_WITH_HOE)) {
            return tier.getSpeed();
        }
        return 1.0F;
    }
}
