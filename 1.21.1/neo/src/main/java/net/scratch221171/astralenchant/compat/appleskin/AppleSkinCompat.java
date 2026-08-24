package net.scratch221171.astralenchant.compat.appleskin;

import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.compat.appleskin.handler.client.NumericFoodDisplayClientHandler;

public class AppleSkinCompat {
    public static void registerAppleSkinClientEvents() {
        NeoForge.EVENT_BUS.addListener(NumericFoodDisplayClientHandler::renderNumericSaturation);
        NeoForge.EVENT_BUS.addListener(NumericFoodDisplayClientHandler::renderNumericRestoredHunger);
    }
}
