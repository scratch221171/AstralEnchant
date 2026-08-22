package net.scratch221171.astralenchant.client.gui.foodoverlay;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.scratch221171.astralenchant.ModUtils;

@EventBusSubscriber(value = Dist.CLIENT)
public final class AppetiteDisplayClientEvents {

    private static final ResourceLocation LAYER_ID = ModUtils.loc("appetite_display");

    @SubscribeEvent
    public static void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(LAYER_ID, AppetiteDisplayLayer.INSTANCE);
    }

    @SubscribeEvent
    public static void onRenderGuiLayerPre(RenderGuiLayerEvent.Pre event) {
        if (event.getName().equals(VanillaGuiLayers.FOOD_LEVEL)
                && AppetiteDisplayLayer.INSTANCE.isEnabled(Minecraft.getInstance().player)) {
            event.setCanceled(true);
        }
    }
}
