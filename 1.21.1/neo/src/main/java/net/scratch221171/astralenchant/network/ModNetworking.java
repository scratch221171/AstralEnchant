package net.scratch221171.astralenchant.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.scratch221171.astralenchant.Constants;

@EventBusSubscriber(modid = Constants.MODID)
public final class ModNetworking {
    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(Constants.MODID).versioned("1");
        registrar.playToServer(
                ToggleEnchantmentPayload.TYPE, ToggleEnchantmentPayload.STREAM_CODEC, ToggleEnchantmentPayload::handle);
    }
}
