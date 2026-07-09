package net.scratch221171.astralenchant.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.common.util.IItemCooldownsExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Shadow
    public abstract ItemCooldowns getCooldowns();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        ((IItemCooldownsExtension) this.getCooldowns()).astralenchant$setPlayer((Player) (Object) this);
    }
}
