package net.scratch221171.astralenchant.common.recipe.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.registry.AEItems;

public class BrewingRecipeHandler {

    public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        var builder = event.getBuilder();

        builder.addRecipe(processingIngredient(0f), potion(Potions.LONG_REGENERATION), processingStack(0.25f));
        builder.addRecipe(processingIngredient(0.25f), potion(Potions.STRONG_HEALING), processingStack(0.5f));
        builder.addRecipe(processingIngredient(0.5f), potion(Potions.LONG_FIRE_RESISTANCE), processingStack(0.75f));
        builder.addRecipe(
                processingIngredient(0.75f),
                potion(Potions.LONG_STRENGTH),
                new ItemStack(AEItems.GROWN_ARCANE_QUARTZ.get()));
    }

    private static Ingredient potion(Holder<Potion> potion) {
        return DataComponentIngredient.of(
                false, DataComponents.POTION_CONTENTS, new PotionContents(potion), Items.POTION);
    }

    private static Ingredient processingIngredient(float progress) {
        return DataComponentIngredient.of(
                false, AEDataComponents.PROCESSING_PROGRESS, progress, AEItems.BUDDING_ARCANIUM_INGOT);
    }

    private static ItemStack processingStack(float progress) {
        ItemStack stack = new ItemStack(AEItems.BUDDING_ARCANIUM_INGOT.get());
        stack.set(AEDataComponents.PROCESSING_PROGRESS, progress);
        return stack;
    }
}
