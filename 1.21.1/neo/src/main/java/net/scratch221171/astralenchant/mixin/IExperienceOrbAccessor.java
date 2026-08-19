package net.scratch221171.astralenchant.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ExperienceOrb.class)
public interface IExperienceOrbAccessor {
    @Invoker("repairPlayerItems")
    int astralenchant$repairPlayerItems(ServerPlayer player, int value);
}
