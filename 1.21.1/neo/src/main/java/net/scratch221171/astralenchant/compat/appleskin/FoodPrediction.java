package net.scratch221171.astralenchant.compat.appleskin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import squeek.appleskin.helpers.FoodHelper;

public final class FoodPrediction {
    private FoodPrediction() {}

    public record Result(int nutritionGain, float saturationGain) {}

    @Nullable public static Result queryHeldFood(Player player) {
        FoodHelper.QueriedFoodResult queried = query(player.getMainHandItem(), player);
        if (queried == null) {
            queried = query(player.getOffhandItem(), player);
        }
        if (queried == null) return null;
        return new Result(queried.modifiedFoodProperties.nutrition(), queried.modifiedFoodProperties.saturation());
    }

    private static FoodHelper.@Nullable QueriedFoodResult query(ItemStack stack, Player player) {
        return FoodHelper.isFood(stack, player) ? FoodHelper.query(stack, player) : null;
    }
}
