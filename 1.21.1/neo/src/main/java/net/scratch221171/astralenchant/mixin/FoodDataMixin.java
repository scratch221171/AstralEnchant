package net.scratch221171.astralenchant.mixin;

import java.util.Optional;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.util.IPlayerAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class FoodDataMixin implements IPlayerAccessor {

    @Shadow
    protected abstract void add(int foodLevel, float saturationLevel);

    @Shadow
    private int foodLevel;

    @Shadow
    private float saturationLevel;

    @Unique private Player astralenchant$player;

    @Override
    public void astralenchant$setPlayer(Player player) {
        this.astralenchant$player = player;
    }

    @Override
    public Optional<Player> astralenchant$getPlayer() {
        return Optional.ofNullable(this.astralenchant$player);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void astralenchant$capturePlayer(Player player, CallbackInfo ci) {
        astralenchant$setPlayer(player);
        // 上限を超えた分をclamp(倍率が下がった時のため)
        add(0, 0);
    }

    @Redirect(method = "add(IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"))
    private int astralenchant$redirectFoodClamp(int value, int min, int max) {
        return astralenchant$getPlayer()
                .map(player -> (int)
                        Mth.clamp(value, min, max * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER)))
                .orElseGet(() -> Mth.clamp(value, min, max));
    }

    @ModifyConstant(method = "needsFood", constant = @Constant(intValue = 20))
    private int modifyFoodLimit(int original) {
        return astralenchant$getPlayer()
                .map(player -> (int) (original * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER)))
                .orElse(original);
    }
}
