package net.scratch221171.astralenchant.client.enchantment;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.client.enchantment.handler.CurseOfIgnoranceClientHandler;
import net.scratch221171.astralenchant.client.enchantment.handler.OverloadClientHandler;

@EventBusSubscriber(modid = Constants.MODID)
public class ClientEventListener {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void onItemTooltip(ItemTooltipEvent event) {
        OverloadClientHandler.shine(event);
        CurseOfIgnoranceClientHandler.makeUnreadable(event);
    }
}
