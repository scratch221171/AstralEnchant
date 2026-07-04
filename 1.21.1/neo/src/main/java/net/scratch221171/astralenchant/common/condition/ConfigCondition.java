package net.scratch221171.astralenchant.common.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.HashMap;
import java.util.Map;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.config.ServerConfig;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Constants.MODID)
public record ConfigCondition(String key) implements ICondition {
    public static final MapCodec<ConfigCondition> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(Codec.STRING.fieldOf("key").forGetter(ConfigCondition::key))
                    .apply(inst, ConfigCondition::new));

    private static final Map<String, Boolean> cache = new HashMap<>();

    @Override
    public boolean test(@NotNull IContext context) {
        return cache.getOrDefault(key, true);
    }

    //    public boolean test() {
    //        try {
    //            Constants.LOGGER.info(key);
    //            Constants.LOGGER.info(String.valueOf(ServerConfig.isEnabled(key)));
    //            return ServerConfig.isEnabled(key);
    //        } catch (Exception e) {
    //            Constants.LOGGER.error(e.getLocalizedMessage());
    //            return true;
    //        }
    //    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

    @SubscribeEvent
    private static void onLoad(ModConfigEvent event) {
        if (event.getConfig().getModId().equals(Constants.MODID)) {
            ServerConfig.getList().forEach((key, value) -> {
                cache.put(key, value.getAsBoolean());
            });
        }
    }
}
