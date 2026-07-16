package net.scratch221171.astralenchant.network;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.inventory.EnchantersWorkbenchMenu;
import org.jspecify.annotations.NonNull;

public record ToggleEnchantmentPayload(int containerId, ResourceLocation enchantmentId) implements CustomPacketPayload {

    public static final Type<ToggleEnchantmentPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MODID, "toggle_enchantment"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleEnchantmentPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    ToggleEnchantmentPayload::containerId,
                    ResourceLocation.STREAM_CODEC,
                    ToggleEnchantmentPayload::enchantmentId,
                    ToggleEnchantmentPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleEnchantmentPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer serverPlayer)) return;
            if (serverPlayer.containerMenu.containerId != payload.containerId()) return;
            if (!(serverPlayer.containerMenu instanceof EnchantersWorkbenchMenu menu)) return;

            ResourceKey<Enchantment> key = ResourceKey.create(Registries.ENCHANTMENT, payload.enchantmentId());
            menu.handleToggleRequest(serverPlayer, key);
        });
    }
}
