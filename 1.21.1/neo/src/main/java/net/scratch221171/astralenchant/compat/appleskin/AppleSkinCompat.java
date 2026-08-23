package net.scratch221171.astralenchant.compat.appleskin;

import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.compat.appleskin.client.AppetiteAppleSkinEvents;

public class AppleSkinCompat {
    public static void registerAppleSkinClientEvents() {
        NeoForge.EVENT_BUS.addListener(AppetiteAppleSkinEvents::onSaturation);
        NeoForge.EVENT_BUS.addListener(AppetiteAppleSkinEvents::onHungerRestored);
    }
}
