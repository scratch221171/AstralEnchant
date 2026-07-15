package net.scratch221171.astralenchant.common.item;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.item.handler.DescTooltipHandler;

@EventBusSubscriber(modid = Constants.MODID)
public class ItemEvents {

    @SubscribeEvent
    private static void onItemTooltip(ItemTooltipEvent e) {
        DescTooltipHandler.addDesc(e);
    }
}
