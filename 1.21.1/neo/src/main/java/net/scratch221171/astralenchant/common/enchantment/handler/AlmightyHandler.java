package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;

@EventBusSubscriber(modid = Constants.MODID)
public class AlmightyHandler {

    @SubscribeEvent
    private static void check(PlayerEvent.HarvestCheck event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.ALMIGHTY, event.getEntity()) <= 0) return;
        event.setCanHarvest(true);
    }

    public static float getVirtualDestroySpeed(Tier tier, BlockState state) {
        if (ServerConfig.EnchantmentSettings.Almighty.CORRECT_TOOL_FOR_ANY_BLOCK.getAsBoolean()
                || state.is(BlockTags.MINEABLE_WITH_PICKAXE)
                || state.is(BlockTags.MINEABLE_WITH_AXE)
                || state.is(BlockTags.MINEABLE_WITH_SHOVEL)
                || state.is(BlockTags.MINEABLE_WITH_HOE)) {
            return tier.getSpeed();
        }

        return 1.0F;
    }
}
