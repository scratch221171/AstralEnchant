package net.scratch221171.astralenchant.common.recipe;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.event.AnvilLandEvent;
import net.scratch221171.astralenchant.common.recipe.handler.AnvilCrushingRecipeHandler;
import net.scratch221171.astralenchant.common.recipe.handler.TransformRecipeHandler;

@EventBusSubscriber(modid = Constants.MODID)
public class RecipeEvents {

    @SubscribeEvent
    private static void onAnvilLand(AnvilLandEvent e) {
        AnvilCrushingRecipeHandler.anvilCrushing(e);
    }

    @SubscribeEvent
    private static void onTick(EntityTickEvent.Pre e) {
        TransformRecipeHandler.handle(e);
    }
}
