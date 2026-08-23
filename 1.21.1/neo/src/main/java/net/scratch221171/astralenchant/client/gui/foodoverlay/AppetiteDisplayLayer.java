package net.scratch221171.astralenchant.client.gui.foodoverlay;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.neoforged.fml.ModList;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.compat.appleskin.client.AppetiteDisplayState;
import net.scratch221171.astralenchant.config.ClientConfig;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class AppetiteDisplayLayer implements LayeredDraw.Layer {

    public static final AppetiteDisplayLayer INSTANCE = new AppetiteDisplayLayer();

    private static final ResourceLocation FOOD_FULL_SPRITE = ResourceLocation.withDefaultNamespace("hud/food_full");
    private static final ResourceLocation FOOD_EMPTY_SPRITE = ResourceLocation.withDefaultNamespace("hud/food_empty");

    private static final int ICON_SIZE = 9;
    private static final float MIN_BLOCK_SCALE = 0.5f;
    private static final int MAX_BLOCK_WIDTH = 42;

    public static final boolean APPLESKIN_LOADED = ModList.get().isLoaded("appleskin");
    public static final int HIDDEN_TEXT_COLOR = 0xFFFF55;
    public static final ResourceLocation APPLESKIN_ICONS =
            ResourceLocation.fromNamespaceAndPath("appleskin", "textures/icons.png");

    private AppetiteDisplayLayer() {}

    @Override
    public void render(@NonNull GuiGraphics guiGraphics, @NonNull DeltaTracker deltaTracker) {

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (!isEnabled(player)) return;
        if (!shouldRender(mc)) return;

        FoodData foodData = player.getFoodData();

        int foodLevel = foodData.getFoodLevel();
        int cap = (int) (20 * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER));

        String text = ClientConfig.FoodLevelDisplay.PERCENTAGE_STYLE.getAsBoolean()
                ? (Math.round(foodLevel * 100f / cap) + "%")
                : (formatCount(foodLevel)
                        + (ClientConfig.FoodLevelDisplay.SHOW_CAP.getAsBoolean() ? "/" + formatCount(cap) : ""));

        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();
        int right = screenWidth / 2 + 90;
        int top = screenHeight - 39;

        AppetiteDisplayState.anchorRightX = drawScaledBlock(guiGraphics, text, right, top, null, 0xFFFFFF);
        AppetiteDisplayState.anchorTopY = top;
    }

    public static boolean isEnabled(@Nullable Player player) {
        return player != null && switch (ClientConfig.FoodLevelDisplay.DISPLAY_CONDITION.get()) {
            case ALWAYS -> true;
            case NEVER -> false;
            case CONDITIONAL -> player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER) != 1;
        };
    }

    static void drawOutlinedText(GuiGraphics guiGraphics, String text, int x, int y, int color) {
        var font = Minecraft.getInstance().font;
        int black = 0x000000;
        guiGraphics.drawString(font, text, x - 1, y, black, false);
        guiGraphics.drawString(font, text, x + 1, y, black, false);
        guiGraphics.drawString(font, text, x, y - 1, black, false);
        guiGraphics.drawString(font, text, x, y + 1, black, false);
        guiGraphics.drawString(font, text, x, y, color, false);
    }

    static boolean shouldRender(Minecraft mc) {
        if (mc.player != null
                && mc.player.getVehicle() != null
                && !mc.player.getVehicle().showVehicleHealth()) return false;
        return mc.gameMode != null && mc.gameMode.canHurtPlayer();
    }

    public static String formatCount(int value) {
        if (value >= 1_000_000) {
            return String.format("%.1fM", value / 1_000_000f);
        }
        if (value >= 1_000) {
            return String.format("%.1fk", value / 1_000f);
        }
        return String.valueOf(value);
    }

    /**
     * アイコン+アウトライン付きテキストを1ブロック描画する。
     * ブロック幅がMAX_BLOCK_WIDTHを超える場合、右端(rightX, y)を基準にスケールダウンする。
     * hiddenIconTexture が null なら通常アイコン(FOOD_FULL_SPRITE)、
     * 非nullならAppleSkinアイコンシート(u=3*ICON_SIZE固定)を使用する。
     */
    public static int drawScaledBlock(
            GuiGraphics guiGraphics,
            String text,
            int rightX,
            int y,
            ResourceLocation hiddenIconTexture,
            int textColor) {
        Minecraft mc = Minecraft.getInstance();
        int textWidth = mc.font.width(text);
        int blockWidth = ICON_SIZE + 2 + textWidth;

        float scale =
                blockWidth > MAX_BLOCK_WIDTH ? Math.max(MIN_BLOCK_SCALE, MAX_BLOCK_WIDTH / (float) blockWidth) : 1.0f;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(rightX, y, 0);
        guiGraphics.pose().scale(scale, scale, 1.0f);

        int localIconX = -blockWidth;

        guiGraphics.blitSprite(FOOD_EMPTY_SPRITE, localIconX, 0, ICON_SIZE, ICON_SIZE);
        if (hiddenIconTexture != null) {
            guiGraphics.blit(hiddenIconTexture, localIconX, 0, 3 * ICON_SIZE, 0, ICON_SIZE, ICON_SIZE);
        } else {
            guiGraphics.blitSprite(FOOD_FULL_SPRITE, localIconX, 0, ICON_SIZE, ICON_SIZE);
        }

        int localTextX = localIconX + ICON_SIZE + 2;
        drawOutlinedText(guiGraphics, text, localTextX, 1, textColor);

        guiGraphics.pose().popPose();

        return rightX - Math.round(blockWidth * scale) - 1;
    }
}
