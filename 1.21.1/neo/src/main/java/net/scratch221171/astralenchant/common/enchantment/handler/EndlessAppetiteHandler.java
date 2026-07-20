package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.scratch221171.astralenchant.common.registry.AEAttributes;

public class EndlessAppetiteHandler {

    public static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        if (!event.has(EntityType.PLAYER, AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER)) {
            event.add(EntityType.PLAYER, AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER, 1.0);
        }
        if (!event.has(EntityType.PLAYER, AEAttributes.SATURATION_LEVEL_CAP_MULTIPLIER)) {
            event.add(EntityType.PLAYER, AEAttributes.SATURATION_LEVEL_CAP_MULTIPLIER, 1.0);
        }
    }
}
