package net.scratch221171.astralenchant.common.registry;

import com.mojang.serialization.Codec;
import java.util.function.UnaryOperator;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.component.DisabledEnchantments;

public class AEDataComponents {
    public static final DeferredRegister<DataComponentType<?>> REGISTER =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Constants.MODID);

    //    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> OVERLOAD =
    //            register("overload", builder -> builder.persistent(Codec.INT));

    //    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> OVER_MENDING =
    //            register("over_mending", builder -> builder.persistent(Codec.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> PROCESSING_PROGRESS =
            register("processing_progress", builder -> builder.persistent(Codec.floatRange(0.0f, 1.0f)));

    // エンチャンターの作業台で無効化されたエンチャント(ID+レベル)を保持する
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<DisabledEnchantments>>
            DISABLED_ENCHANTMENTS =
                    register("disabled_enchantments", builder -> builder.persistent(DisabledEnchantments.CODEC));

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(
            String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return REGISTER.register(
                name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
