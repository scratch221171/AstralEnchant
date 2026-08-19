package net.scratch221171.astralenchant.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.scratch221171.astralenchant.common.enchantment.handler.OverMendingHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ExperienceOrb.class)
public class ExperienceOrbMixin {

    @WrapOperation(
            method = "playerTouch(Lnet/minecraft/world/entity/player/Player;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperiencePoints(I)V"))
    private void astralenchant$applyOverMending(Player entity, int value, Operation<Void> original) {
        if (entity instanceof ServerPlayer serverPlayer) {
            value = OverMendingHandler.repairPlayerItems(serverPlayer, value);
        }
        if (value > 0) {
            original.call(entity, value);
        }
    }
}
