/*
 * Based on code from JustDireThings
 * https://github.com/Direwolf20-MC/JustDireThings
 *
 * Licensed under the MIT License.
 *
 * Original copyright (c) 2023 Direwolf20-MC
 */

package net.scratch221171.astralenchant.client.item.renderer;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import org.jetbrains.annotations.NotNull;

public class ProgressBarDecorator implements IItemDecorator {
    @Override
    public boolean render(
            @NotNull GuiGraphics guiGraphics, @NotNull Font font, @NotNull ItemStack stack, int xOffset, int yOffset) {
        float progress = stack.getOrDefault(AEDataComponents.PROCESSING_PROGRESS, -1f);
        if (progress >= 0) {
            // Calculate positions based on whether the power bar is visible
            int barY = yOffset + 13; // Adjust Y position based on power bar visibility
            int barWidth = (int) Math.ceil(progress * 13F);

            long time = System.currentTimeMillis();
            int barColor = Mth.hsvToRgb(Math.max(0.0F, progress) / 3.0F, 1.0F, 1.0F);
            // Render fluid bar
            renderBar(guiGraphics, xOffset + 2, barY, barWidth, barColor);

            return true;
        }
        return false;
    }

    private void renderBar(GuiGraphics guiGraphics, int x, int y, int width, int color) {
        // Render the background of the bar (black)
        guiGraphics.fill(RenderType.guiOverlay(), x, y, x + 13, y + 2, 0xFF303030);

        // Render the fluid bar with the calculated width and color
        guiGraphics.fill(RenderType.guiOverlay(), x, y, x + width, y + 1, color | 0xFF000000);
    }
}
