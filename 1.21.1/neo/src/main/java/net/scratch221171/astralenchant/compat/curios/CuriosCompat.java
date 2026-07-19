package net.scratch221171.astralenchant.compat.curios;

import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.compat.curios.handler.CuriosEssenceOfEnchantmentHandler;
import net.scratch221171.astralenchant.compat.curios.handler.CuriosSlotExpansionHandler;

public class CuriosCompat {
    public static void registerCuriosEvents() {
        NeoForge.EVENT_BUS.addListener(CuriosEssenceOfEnchantmentHandler::addCurioAttribute);
        NeoForge.EVENT_BUS.addListener(CuriosSlotExpansionHandler::expandSlot);
    }
}
