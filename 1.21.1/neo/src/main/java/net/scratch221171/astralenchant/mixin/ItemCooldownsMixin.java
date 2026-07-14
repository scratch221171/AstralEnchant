package net.scratch221171.astralenchant.mixin;

import java.util.Optional;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.util.IPlayerAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements IPlayerAccessor {

    @Unique private Player astralenchant$player;

    @Override
    public void astralenchant$setPlayer(Player player) {
        this.astralenchant$player = player;
    }

    @ModifyVariable(method = "addCooldown", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int astralenchant$modifyTicks(int ticks) {
        if (astralenchant$player.level().isClientSide) return ticks;
        double multiplier = Optional.ofNullable(astralenchant$player.getAttribute(AEAttributes.COOLDOWN_DURATION))
                .map(AttributeInstance::getValue)
                .orElse(0d);
        if (multiplier <= 0) return 0;

        return (int) (ticks * multiplier);
    }
}
