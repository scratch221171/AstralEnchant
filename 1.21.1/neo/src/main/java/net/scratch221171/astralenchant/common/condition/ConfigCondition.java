package net.scratch221171.astralenchant.common.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.scratch221171.astralenchant.config.CommonConfig;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

/**
 * jsonのneoforge:conditionsから参照する、CommonConfigのbool設定でロード可否を切り替えるcondition。
 * 例: {
 *       "type": "astralenchant:config_condition",
 *       "config_id": "nullification"
 *     }
 */
public record ConfigCondition(String configId) implements ICondition {

    public static final MapCodec<ConfigCondition> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(Codec.STRING.fieldOf("config_id").forGetter(ConfigCondition::configId))
                    .apply(inst, ConfigCondition::new));

    @Override
    public boolean test(@NotNull IContext context) {
        return Optional.ofNullable(CommonConfig.getConditionEntries().get(configId))
                .map(ConfigEntry.BooleanEntry::getAsBoolean)
                .orElseThrow(
                        () -> new IllegalArgumentException("Unregistered config id for ConfigCondition: " + configId));
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

    @Override
    public @NonNull String toString() {
        return "astralenchant_config(\"" + configId + "\")";
    }
}
