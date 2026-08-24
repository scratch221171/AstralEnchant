package net.scratch221171.astralenchant.client.gui.food;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(value = Dist.CLIENT)
public final class NumericFoodDisplayClientEvents {

    @SubscribeEvent
    public static void onRenderGuiLayerPre(RenderGuiLayerEvent.Pre event) {
        if (!event.getName().equals(VanillaGuiLayers.FOOD_LEVEL)) return;
        if (!NumericFoodDisplayRenderer.shouldRender(Minecraft.getInstance())) return;

        event.setCanceled(true);
        NumericFoodDisplayRenderer.render(event.getGuiGraphics());
    }
}
