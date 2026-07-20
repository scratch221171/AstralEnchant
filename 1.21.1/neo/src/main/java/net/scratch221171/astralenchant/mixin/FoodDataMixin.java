package net.scratch221171.astralenchant.mixin;

import java.util.Optional;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.util.IPlayerAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO: 倍率が下がった時の余剰分の処理
// TODO: apple skin連携
@Mixin(FoodData.class)
public class FoodDataMixin implements IPlayerAccessor {

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
        this.astralenchant$setPlayer(player);
    }

    @Redirect(method = "add(IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"))
    private int astralenchant$redirectFoodClamp(int value, int min, int max) {
        return astralenchant$getPlayer()
                .map(player -> (int)
                        Mth.clamp(value, min, max * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER)))
                .orElseGet(() -> Mth.clamp(value, min, max));
    }

    @Redirect(method = "add(IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F"))
    private float astralenchant$redirectSaturationClamp(float value, float min, float max) {
        return astralenchant$getPlayer()
                .map(player -> (float) Mth.clamp(
                        value, min, max * player.getAttributeValue(AEAttributes.SATURATION_LEVEL_CAP_MULTIPLIER)))
                .orElseGet(() -> Mth.clamp(value, min, max));
    }

    @ModifyConstant(method = "needsFood", constant = @Constant(intValue = 20))
    private int modifyFoodLimit(int original) {
        return astralenchant$getPlayer()
                .map(player -> (int) (original * player.getAttributeValue(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER)))
                .orElse(original);
    }
}
