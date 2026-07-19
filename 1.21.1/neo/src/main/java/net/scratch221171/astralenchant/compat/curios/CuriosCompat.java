package net.scratch221171.astralenchant.compat.curios;

import net.neoforged.neoforge.common.NeoForge;
import net.scratch221171.astralenchant.compat.curios.handler.EssenceOfEnchantmentCuriosHandler;

public class CuriosCompat {
    public static void registerCuriosEvents() {
        NeoForge.EVENT_BUS.addListener(EssenceOfEnchantmentCuriosHandler::addCurioAttribute);
    }
}
