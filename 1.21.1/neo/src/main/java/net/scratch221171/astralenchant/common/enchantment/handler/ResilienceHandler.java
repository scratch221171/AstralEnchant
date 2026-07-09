package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.registry.AEAttributes;

@EventBusSubscriber(modid = Constants.MODID)
public class ResilienceHandler {

    @SubscribeEvent
    private static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        if (!event.has(EntityType.PLAYER, AEAttributes.COOLDOWN_DURATION)) {
            event.add(EntityType.PLAYER, AEAttributes.COOLDOWN_DURATION, 1.0);
        }
    }
}
