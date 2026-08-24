package net.scratch221171.astralenchant.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;

public class AEAttributes {
    public static final ResourceKey<Attribute> COOLDOWN_DURATION =
            ResourceKey.create(Registries.ATTRIBUTE, ModUtils.loc(ID.COOLDOWN_DURATION));

    public static final ResourceKey<Attribute> FOOD_LEVEL_CAP_MULTIPLIER =
            ResourceKey.create(Registries.ATTRIBUTE, ModUtils.loc(ID.FOOD_LEVEL_CAP_MULTIPLIER));
}
