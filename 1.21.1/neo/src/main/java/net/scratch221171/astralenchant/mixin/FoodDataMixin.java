package net.scratch221171.astralenchant.mixin;

import java.util.Optional;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.util.IFoodDataExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(FoodData.class)
public abstract class FoodDataMixin implements IFoodDataExtension {

    @Shadow
    protected abstract void add(int foodLevel, float saturationLevel);

    @Unique private Player astralenchant$player;

    @Override
    public void astralenchant$setPlayer(Player player) {
        this.astralenchant$player = player;
    }

    @Override
    public Optional<Player> astralenchant$getPlayer() {
        return Optional.ofNullable(this.astralenchant$player);
    }

    @Override
    public void astralenchant$update() {
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
