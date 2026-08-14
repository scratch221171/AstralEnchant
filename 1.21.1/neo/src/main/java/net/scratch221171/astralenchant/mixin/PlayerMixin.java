package net.scratch221171.astralenchant.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.scratch221171.astralenchant.common.util.IPlayerAccessor;
import net.scratch221171.astralenchant.common.util.IPlayerExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin implements IPlayerExtension {

    @Shadow
    public abstract ItemCooldowns getCooldowns();

    @Shadow
    protected int enchantmentSeed;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void astralenchant$init(CallbackInfo ci) {
        var self = (Player) (Object) this;
        ((IPlayerAccessor) this.getCooldowns()).astralenchant$setPlayer(self);
    }

    @Override
    public void astralenchant$setEnchantmentSeed(int seed) {
        this.enchantmentSeed = seed;
    }
}
