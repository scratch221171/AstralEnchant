package net.scratch221171.astralenchant.compat.appleskin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.scratch221171.astralenchant.client.gui.foodoverlay.AppetiteDisplayLayer;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.compat.appleskin.AppetiteFoodPrediction;
import net.scratch221171.astralenchant.config.ClientConfig;
import net.scratch221171.astralenchant.mixin.compat.client.appleskin.HUDOverlayHandlerAccessor;
import squeek.appleskin.api.event.HUDOverlayEvent;
import squeek.appleskin.client.HUDOverlayHandler;

public final class AppetiteAppleSkinEvents {

    public static void onSaturation(HUDOverlayEvent.Saturation event) {
        Player player = Minecraft.getInstance().player;
        if (!AppetiteDisplayLayer.isEnabled(player)) return;
        event.setCanceled(true);

        int cap = (int) (20 * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER));
        int saturationLevel = Math.round(event.saturationLevel);

        String hiddenText = ClientConfig.FoodLevelDisplay.PERCENTAGE_STYLE.getAsBoolean()
                ? (Math.round(saturationLevel * 100f / cap) + "%")
                : (AppetiteDisplayLayer.formatCount(saturationLevel)
                   + (ClientConfig.FoodLevelDisplay.SHOW_CAP.getAsBoolean() ? "/" + AppetiteDisplayLayer.formatCount(cap) : ""));

        AppetiteDisplayLayer.drawScaledBlock(
                event.guiGraphics, hiddenText,
                AppetiteDisplayState.anchorRightX, AppetiteDisplayState.anchorTopY,
                AppetiteDisplayLayer.APPLESKIN_ICONS, AppetiteDisplayLayer.HIDDEN_TEXT_COLOR);

        AppetiteFoodPrediction.Result prediction = AppetiteFoodPrediction.queryHeldFood(player);
        if (prediction == null) return;

        int foodLevel = player.getFoodData().getFoodLevel();
        int newFoodValue = foodLevel + prediction.nutritionGain();
        float newSaturationValue = event.saturationLevel + prediction.saturationGain();
        // AppleSkin本家(SaturationOverlay#render)と同じclamp式をそのまま踏襲
        float saturationGained = newSaturationValue > newFoodValue
                ? newFoodValue - event.saturationLevel
                : prediction.saturationGain();

        // 前回指摘の通り、隠し満腹度はfoodLevelに依存せずsaturationのみに基づく値
        int predictedHiddenLevel = Math.min(cap, Math.round(event.saturationLevel + saturationGained));
        int hiddenDelta = predictedHiddenLevel - saturationLevel;

        drawFlashingSuffix(event.guiGraphics, hiddenDelta,
                AppetiteDisplayState.anchorRightX - 40, AppetiteDisplayState.anchorTopY, // TODO: 仮の相対座標のまま未修正
                AppetiteDisplayLayer.HIDDEN_TEXT_COLOR, HUDOverlayHandlerAccessor.getAlpha());
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
            drawFlashingSuffix(event.guiGraphics, foodDelta,
                    AppetiteDisplayState.anchorRightX + 4, AppetiteDisplayState.anchorTopY, 0xFFFFFF, HUDOverlayHandlerAccessor.getAlpha());
        }
    }

    static void drawFlashingSuffix(GuiGraphics guiGraphics, int delta, int x, int y, int color, float alpha) {
        if (delta <= 0) return;

        int alphaByte = Math.round(alpha * 255);
        /// vanilla Fontの「アルファ0系は強制不透明化」仕様([Font#adjustColor(int)])の回避
        if (alphaByte < 4) return;

        Minecraft mc = Minecraft.getInstance();
        int argb = (Math.round(alpha * 255) << 24) | (color & 0xFFFFFF);
        guiGraphics.drawString(mc.font, "+" + delta, x, y + 1, argb, false);
    }
}
