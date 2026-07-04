package net.scratch221171.astralenchant.common.registry;

import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;

public class AEConditions {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, Constants.MODID);

    public static final Supplier<MapCodec<ConfigCondition>> CONFIG_CONDITION =
            CONDITION_CODECS.register("config_condition", () -> ConfigCondition.CODEC);

    public static void register(IEventBus eventBus) {
        CONDITION_CODECS.register(eventBus);
    }
}
