package net.scratch221171.astralenchant.compat.clumps;

import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.compat.clumps.handler.ClumpsOverMendingHandler;

public class ClumpsCompat {
    public static void registerClumpsEvents() {
        NeoForge.EVENT_BUS.addListener(ClumpsOverMendingHandler::onClumpsRepair);
    }
}
