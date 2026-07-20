package net.scratch221171.astralenchant.common.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;

public class AEAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, Constants.MODID);

    public static final Holder<Attribute>
            COOLDOWN_DURATION =
                    ATTRIBUTES.register(ID.COOLDOWN_DURATION, () -> new RangedAttribute(
                                    "attribute.astralenchant." + ID.COOLDOWN_DURATION, 1.0, 0.0, 1024.0)
                            .setSentiment(Attribute.Sentiment.NEGATIVE)
                            .setSyncable(true)),
            FOOD_LEVEL_CAP_MULTIPLIER =
                    ATTRIBUTES.register(ID.FOOD_LEVEL_CAP_MULTIPLIER, () -> new RangedAttribute(
                                    "attribute.astralenchant." + ID.FOOD_LEVEL_CAP_MULTIPLIER, 1.0, 0.0, 1024.0)
                            .setSyncable(true)),
            SATURATION_LEVEL_CAP_MULTIPLIER =
                    ATTRIBUTES.register(ID.SATURATION_LEVEL_CAP_MULTIPLIER, () -> new RangedAttribute(
                                    "attribute.astralenchant." + ID.SATURATION_LEVEL_CAP_MULTIPLIER, 1.0, 0.0, 1024.0)
                            .setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
