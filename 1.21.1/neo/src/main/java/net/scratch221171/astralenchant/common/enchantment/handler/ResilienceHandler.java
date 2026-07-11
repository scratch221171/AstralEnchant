package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.scratch221171.astralenchant.common.registry.AEAttributes;

public class ResilienceHandler {

    public static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        if (!event.has(EntityType.PLAYER, AEAttributes.COOLDOWN_DURATION)) {
            event.add(EntityType.PLAYER, AEAttributes.COOLDOWN_DURATION, 1.0);
        }
    }
}
