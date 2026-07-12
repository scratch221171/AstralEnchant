package net.scratch221171.astralenchant.datagen.recipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.tag.AETags;
import org.jetbrains.annotations.NotNull;

public class AERecipeProvider extends RecipeProvider {
    public AERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output, HolderLookup.@NotNull Provider holderLookup) {
        // 精錬
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(Items.ENCHANTED_BOOK),
                        RecipeCategory.MISC,
                        AEItems.ENCHANTMENT_SHARD.toStack(1),
                        0.0f,
                        400)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ModUtils.loc("enchantment_shard_smelting"));

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(Items.ENCHANTED_BOOK),
                        RecipeCategory.MISC,
                        AEItems.ENCHANTMENT_SHARD.toStack(1),
                        0.0f,
                        200)
                .unlockedBy("has_enchanted_book", has(Items.ENCHANTED_BOOK))
                .save(output, ModUtils.loc("enchantment_shard_blasting"));

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(AEItems.BUDDING_ARCANIUM_INGOT),
                        RecipeCategory.MISC,
                        AEItems.GROWN_ARCANE_QUARTZ.toStack(1),
                        1,
                        200 * 16)
                .unlockedBy(getHasName(AEItems.BUDDING_ARCANIUM_INGOT), has(AEItems.BUDDING_ARCANIUM_INGOT))
                .save(output, ModUtils.loc("grown_arcane_quartz_smelting"));

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(AEItems.BUDDING_ARCANIUM_INGOT),
                        RecipeCategory.MISC,
                        AEItems.GROWN_ARCANE_QUARTZ.toStack(1),
                        1,
                        200 * 16 / 2)
                .unlockedBy(getHasName(AEItems.BUDDING_ARCANIUM_INGOT), has(AEItems.BUDDING_ARCANIUM_INGOT))
                .save(output, ModUtils.loc("grown_arcane_quartz_blasting"));

        // 素材
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANE_QUARTZ, 2)
                .requires(AEItems.ENCHANTMENT_SHARD)
                .requires(Items.QUARTZ)
                .requires(Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(AEItems.ENCHANTMENT_SHARD), has(AEItems.ENCHANTMENT_SHARD))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.LAVAPROOF_ARCANE_QUARTZ, 4)
                .requires(Items.MAGMA_CREAM)
                .requires(AETags.Items.GEMS_ARCANE_QUARTZ)
                .requires(AETags.Items.GEMS_ARCANE_QUARTZ)
                .requires(AETags.Items.GEMS_ARCANE_QUARTZ)
                .requires(AETags.Items.GEMS_ARCANE_QUARTZ)
                .unlockedBy(getHasName(AEItems.ARCANE_QUARTZ), has(AEItems.ARCANE_QUARTZ))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.LUMINITE, 4)
                .requires(Items.LAVA_BUCKET)
                .requires(AEItems.LAVAPROOF_ARCANE_QUARTZ)
                .requires(AEItems.LAVAPROOF_ARCANE_QUARTZ)
                .requires(AEItems.LAVAPROOF_ARCANE_QUARTZ)
                .requires(AEItems.LAVAPROOF_ARCANE_QUARTZ)
                .unlockedBy(getHasName(AEItems.LAVAPROOF_ARCANE_QUARTZ), has(AEItems.LAVAPROOF_ARCANE_QUARTZ))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANE_QUARTZ_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .unlockedBy(getHasName(AEItems.ARCANE_QUARTZ_TINY_DUST), has(AEItems.ARCANE_QUARTZ_TINY_DUST))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANE_QUARTZ_TINY_DUST, 9)
                .requires(AETags.Items.DUSTS_ARCANE_QUARTZ)
                .unlockedBy(getHasName(AEItems.ARCANE_QUARTZ_DUST), has(AEItems.ARCANE_QUARTZ_DUST))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANIUM_INGOT)
                .requires(Items.IRON_INGOT)
                .requires(Items.AMETHYST_SHARD)
                .requires(Items.GOLD_NUGGET)
                .requires(AEItems.ARCANE_QUARTZ_TINY_DUST)
                .unlockedBy(getHasName(AEItems.ARCANE_QUARTZ_TINY_DUST), has(AEItems.ARCANE_QUARTZ_TINY_DUST))
                .save(output, ModUtils.loc("arcanium_ingot_from_arcane_quartz"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEBlocks.ARCANIUM_BLOCK)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .define('1', AETags.Items.INGOTS_ARCANIUM)
                .define('2', AEItems.ARCANIUM_INGOT)
                .unlockedBy(getHasName(AEItems.ARCANIUM_INGOT), has(AETags.Items.INGOTS_ARCANIUM))
                .save(output, ModUtils.loc("arcanium_block_from_ingot"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ARCANIUM_INGOT, 9)
                .requires(AETags.Items.STORAGE_BLOCKS_ARCANIUM)
                .unlockedBy(getHasName(AEBlocks.ARCANIUM_BLOCK), has(AETags.Items.STORAGE_BLOCKS_ARCANIUM))
                .save(output, ModUtils.loc("arcanium_ingot_from_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, new ItemStack(AEItems.BUDDING_ARCANIUM_INGOT.get(), 4))
                .pattern("324")
                .pattern("212")
                .pattern("423")
                .define('1', Items.BUDDING_AMETHYST)
                .define('2', AETags.Items.INGOTS_ARCANIUM)
                .define('3', AETags.Items.GEMS_ARCANE_QUARTZ)
                .define('4', Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.BUDDING_AMETHYST), has(Items.BUDDING_AMETHYST))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.ENCHANTMENT_VESSEL)
                .requires(Items.BOOK)
                .requires(Items.SOUL_SAND)
                .requires(AEItems.ARCANE_QUARTZ_DUST)
                .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(output);
    }

    //    Ingredient    //

    private static Item getFromRegistry(String namespace, String path) {
        return BuiltInRegistries.ITEM
                .get(ResourceLocation.fromNamespaceAndPath(namespace, path))
                .asItem();
    }

    private Ingredient potion(Holder<Potion> potion) {
        return potion(potion, Items.POTION);
    }

    private Ingredient potion(Holder<Potion> potion, ItemLike item) {
        return DataComponentIngredient.of(false, DataComponents.POTION_CONTENTS, new PotionContents(potion), item);
    }

    private Ingredient enchantedBook(Holder<Enchantment> holder, int level) {
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        mutable.set(holder, level);
        return DataComponentIngredient.of(
                false, DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable(), Items.ENCHANTED_BOOK);
    }

    //    Builder    //

    private void enchantedBookRecipe(
            @NotNull RecipeOutput output,
            HolderLookup.@NotNull Provider provider,
            ResourceKey<Enchantment> key,
            Consumer<ShapedRecipeBuilder> consumer) {
        enchantedBookRecipe(output, provider, key, 1, consumer);
    }

    private void enchantedBookRecipe(
            @NotNull RecipeOutput output,
            HolderLookup.@NotNull Provider provider,
            ResourceKey<Enchantment> key,
            int level,
            Consumer<ShapedRecipeBuilder> consumer) {
        Holder<Enchantment> holder = provider.holderOrThrow(key);
        ItemStack result = new ItemStack(Items.ENCHANTED_BOOK);
        result.enchant(holder, level);
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result);
        consumer.accept(builder);
        builder.save(output, ModUtils.loc("enchanted_book_" + key.location().getPath()));
    }

    //    Criterion    //

    private static Criterion<InventoryChangeTrigger.TriggerInstance> has(Holder<Enchantment> holder) {
        return inventoryTrigger(ItemPredicate.Builder.item()
                .of(Items.ENCHANTED_BOOK)
                .withSubPredicate(
                        ItemSubPredicates.STORED_ENCHANTMENTS,
                        ItemEnchantmentsPredicate.storedEnchantments(List.of(
                                new EnchantmentPredicate(HolderSet.direct(holder), MinMaxBounds.Ints.atLeast(1))))));
    }
}
