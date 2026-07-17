package net.scratch221171.astralenchant.client.item;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.client.item.handler.ComponentHandler;
import net.scratch221171.astralenchant.client.item.handler.DescTooltipHandler;

@EventBusSubscriber(modid = Constants.MODID)
public class ItemEvents {

    @SubscribeEvent
    private static void onItemTooltip(ItemTooltipEvent e) {
        DescTooltipHandler.addDesc(e);
        ComponentHandler.addComponentDesc(e);
    }

    @SubscribeEvent
    private static void onRegisterItemDecorations(RegisterItemDecorationsEvent e) {
        ComponentHandler.registerDecorations(e);
    }
}
