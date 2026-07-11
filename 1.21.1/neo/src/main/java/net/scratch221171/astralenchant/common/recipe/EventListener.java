package net.scratch221171.astralenchant.common.recipe;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.event.AnvilLandEvent;
import net.scratch221171.astralenchant.common.recipe.handler.AnvilCrushingRecipeHandler;

@EventBusSubscriber(modid = Constants.MODID)
public class EventListener {

    @SubscribeEvent
    private static void onAnvilLand(AnvilLandEvent e) {
        AnvilCrushingRecipeHandler.anvilCrushing(e);
    }
}
