package net.scratch221171.astralenchant.compat.appleskin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.scratch221171.astralenchant.client.gui.foodoverlay.AppetiteDisplayLayer;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.compat.appleskin.AppetiteFoodPrediction;
import net.scratch221171.astralenchant.config.ClientConfig;
import net.scratch221171.astralenchant.mixin.compat.client.appleskin.HUDOverlayHandlerAccessor;
import squeek.appleskin.api.event.HUDOverlayEvent;

public final class AppetiteAppleSkinEvents {

    public static final int HIDDEN_TEXT_COLOR = 0xFFFF55;
    public static final ResourceLocation APPLE_SKIN_ICONS =
            ResourceLocation.fromNamespaceAndPath("appleskin", "textures/icons.png");

    public static void onSaturation(HUDOverlayEvent.Saturation event) {
        Player player = Minecraft.getInstance().player;
        if (!AppetiteDisplayLayer.isEnabled(player)) return;
        event.setCanceled(true);

        int cap = (int) (20 * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER));
        int saturationLevel = Math.round(event.saturationLevel);

        AppetiteDisplayLayer.DisplayText dt = AppetiteDisplayLayer.buildDisplayText(saturationLevel, cap);
        AppetiteDisplayLayer.BlockLayout layout = AppetiteDisplayLayer.drawScaledBlock(
                event.guiGraphics,
                dt.value(),
                dt.suffix(),
                AppetiteDisplayState.anchorRightX,
                AppetiteDisplayState.anchorTopY,
                APPLE_SKIN_ICONS,
                HIDDEN_TEXT_COLOR);

        AppetiteFoodPrediction.Result prediction = AppetiteFoodPrediction.queryHeldFood(player);
        if (prediction == null) return;

        int foodLevel = player.getFoodData().getFoodLevel();
        int newFoodValue = foodLevel + prediction.nutritionGain();
        float newSaturationValue = event.saturationLevel + prediction.saturationGain();
        float saturationGained =
                newSaturationValue > newFoodValue ? newFoodValue - event.saturationLevel : prediction.saturationGain();

        int predictedHiddenLevel = Math.min(cap, Math.round(event.saturationLevel + saturationGained));
        int hiddenDelta = predictedHiddenLevel - saturationLevel;

        drawFlashingSuffix(
                event.guiGraphics,
                (ClientConfig.FoodLevelDisplay.PERCENTAGE_STYLE.getAsBoolean()
                        ? Math.ceilDiv(hiddenDelta * 100, cap)
                        : hiddenDelta),
                layout.valueRightEdgeX(),
                AppetiteDisplayState.anchorTopY - 8,
                HIDDEN_TEXT_COLOR,
                HUDOverlayHandlerAccessor.getAlpha());
    }

    public static void onHungerRestored(HUDOverlayEvent.HungerRestored event) {
        Player player = Minecraft.getInstance().player;
        if (!AppetiteDisplayLayer.isEnabled(player)) return;
        event.setCanceled(true);

        int foodLevel = event.currentFoodLevel;
        int cap = (int) (20 * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER));

        AppetiteFoodPrediction.Result prediction = AppetiteFoodPrediction.queryHeldFood(player);
        if (prediction != null) {
            int foodDelta = Math.min(cap, foodLevel + prediction.nutritionGain()) - foodLevel;
            drawFlashingSuffix(
                    event.guiGraphics,
                    (ClientConfig.FoodLevelDisplay.PERCENTAGE_STYLE.getAsBoolean()
                            ? Math.ceilDiv(foodDelta * 100, cap)
                            : foodDelta),
                    AppetiteDisplayState.normalValueRightEdgeX,
                    AppetiteDisplayState.anchorTopY - 8,
                    0xFFFFFF,
                    HUDOverlayHandlerAccessor.getAlpha());
        }
    }

    static void drawFlashingSuffix(GuiGraphics guiGraphics, int delta, int rightX, int y, int color, float alpha) {
        if (delta <= 0) return;

        int alphaByte = Math.round(alpha * 255);
        if (alphaByte < 4) return;

        String text = "+" + delta;
        int textWidth = Minecraft.getInstance().font.width(text);
        int x = rightX - textWidth;

        int argb = (alphaByte << 24) | (color & 0xFFFFFF);
        AppetiteDisplayLayer.drawOutlinedText(guiGraphics, text, x, y, argb);
    }
}
