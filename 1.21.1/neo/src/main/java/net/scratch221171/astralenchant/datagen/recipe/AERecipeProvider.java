package net.scratch221171.astralenchant.datagen.recipe;

import appeng.recipes.handlers.InscriberRecipeBuilder;
import appeng.recipes.transform.TransformCircumstance;
import appeng.recipes.transform.TransformRecipeBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
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
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.OrCondition;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.tag.AETags;
import net.scratch221171.astralenchant.config.ConfigID;
import org.jspecify.annotations.NonNull;
import rearth.oritech.api.recipe.CentrifugeFluidRecipeBuilder;
import rearth.oritech.api.recipe.GrinderRecipeBuilder;
import rearth.oritech.api.recipe.PulverizerRecipeBuilder;

public class AERecipeProvider extends RecipeProvider {
    public AERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NonNull RecipeOutput output, HolderLookup.@NonNull Provider holderLookup) {
        // 精錬
        {
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
        }

        // 素材
        {
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
                    .save(output.withConditions(new NotCondition(new OrCondition(List.of(
                            new ModLoadedCondition("ae2"),
                            new ModLoadedCondition("create"),
                            new ModLoadedCondition("oritech"))))));

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

            var budding = new ItemStack(AEItems.BUDDING_ARCANIUM_INGOT.get(), 4);
            budding.set(AEDataComponents.PROCESSING_PROGRESS, 0f);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, budding)
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
                    .requires(AETags.Items.DUSTS_ARCANE_QUARTZ)
                    .requires(Items.SOUL_SAND)
                    .requires(Items.GHAST_TEAR)
                    .requires(Items.INK_SAC)
                    .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                    .save(output);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AEItems.RESONANT_VESSEL)
                    .requires(AEItems.ENCHANTMENT_VESSEL)
                    .requires(AEItems.GROWN_ARCANE_QUARTZ)
                    .requires(Items.SCULK_CATALYST)
                    .requires(Items.PHANTOM_MEMBRANE)
                    .requires(Items.GLOW_INK_SAC)
                    .unlockedBy(getHasName(AEItems.ENCHANTMENT_VESSEL), has(AEItems.ENCHANTMENT_VESSEL))
                    .save(output);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.ARCANIUM_HELMET)
                    .pattern("111")
                    .pattern("1 1")
                    .define('1', AETags.Items.INGOTS_ARCANIUM)
                    .unlockedBy(getHasName(AEItems.ARCANIUM_INGOT), has(AETags.Items.INGOTS_ARCANIUM))
                    .save(output);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.ARCANIUM_CHESTPLATE)
                    .pattern("1 1")
                    .pattern("111")
                    .pattern("111")
                    .define('1', AETags.Items.INGOTS_ARCANIUM)
                    .unlockedBy(getHasName(AEItems.ARCANIUM_INGOT), has(AETags.Items.INGOTS_ARCANIUM))
                    .save(output);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.ARCANIUM_LEGGINGS)
                    .pattern("111")
                    .pattern("1 1")
                    .pattern("1 1")
                    .define('1', AETags.Items.INGOTS_ARCANIUM)
                    .unlockedBy(getHasName(AEItems.ARCANIUM_INGOT), has(AETags.Items.INGOTS_ARCANIUM))
                    .save(output);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEItems.ARCANIUM_BOOTS)
                    .pattern("1 1")
                    .pattern("1 1")
                    .define('1', AETags.Items.INGOTS_ARCANIUM)
                    .unlockedBy(getHasName(AEItems.ARCANIUM_INGOT), has(AETags.Items.INGOTS_ARCANIUM))
                    .save(output);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AEBlocks.ENCHANTERS_WORKBENCH)
                    .pattern("121")
                    .pattern("343")
                    .pattern("555")
                    .define('1', AETags.Items.GEMS_LUMINITE)
                    .define('2', Items.PURPLE_CARPET)
                    .define('3', Tags.Items.OBSIDIANS)
                    .define('4', Items.ENCHANTING_TABLE)
                    .define('5', Tags.Items.BOOKSHELVES)
                    .unlockedBy(getHasName(AEItems.LUMINITE), has(AEItems.LUMINITE))
                    .save(output);
        }

        // エンチャント本
        {
            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.NULLIFICATION)),
                    holderLookup,
                    AEEnchantments.NULLIFICATION,
                    builder -> {
                        var sharpness = holderLookup.holderOrThrow(Enchantments.SHARPNESS);
                        var smite = holderLookup.holderOrThrow(Enchantments.SMITE);
                        var bane_of_arthropods = holderLookup.holderOrThrow(Enchantments.BANE_OF_ARTHROPODS);
                        var breach = holderLookup.holderOrThrow(Enchantments.BREACH);
                        var piercing = holderLookup.holderOrThrow(Enchantments.PIERCING);
                        var density = holderLookup.holderOrThrow(Enchantments.DENSITY);
                        var impaling = holderLookup.holderOrThrow(Enchantments.IMPALING);
                        builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                                .pattern("123")
                                .pattern("456")
                                .pattern("789")
                                .define('1', enchantedBook(sharpness, 5))
                                .define('2', enchantedBook(smite, 5))
                                .define('3', enchantedBook(bane_of_arthropods, 5))
                                .define('4', enchantedBook(breach, 4))
                                .define('5', Items.END_CRYSTAL)
                                .define('6', enchantedBook(piercing, 4))
                                .define('7', enchantedBook(density, 5))
                                .define('8', AEItems.RESONANT_VESSEL)
                                .define('9', enchantedBook(impaling, 5));
                    });

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.LAST_STAND)),
                    holderLookup,
                    AEEnchantments.LAST_STAND,
                    builder -> builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                            .pattern("123")
                            .pattern("242")
                            .pattern("565")
                            .define('1', potion(Potions.STRONG_REGENERATION))
                            .define('2', Items.EXPERIENCE_BOTTLE)
                            .define('3', potion(Potions.LONG_FIRE_RESISTANCE))
                            .define('4', Items.TOTEM_OF_UNDYING)
                            .define('5', Items.ENDER_EYE)
                            .define('6', AEItems.RESONANT_VESSEL));

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.ESSENCE_OF_ENCHANTMENT)),
                    holderLookup,
                    AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                    builder -> builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                            .pattern("111")
                            .pattern("121")
                            .pattern("343")
                            .define('1', Items.BOOKSHELF)
                            .define('2', Items.ENCHANTING_TABLE)
                            .define('3', Tags.Items.ENCHANTING_FUELS)
                            .define('4', AEItems.RESONANT_VESSEL));

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.RESILIENCE)),
                    holderLookup,
                    AEEnchantments.RESILIENCE,
                    builder -> builder.unlockedBy(
                                    getHasName(AEItems.ENCHANTMENT_VESSEL), has(AEItems.ENCHANTMENT_VESSEL))
                            .pattern("121")
                            .pattern("343")
                            .pattern("151")
                            .define('1', Items.WIND_CHARGE)
                            .define('2', Items.BREEZE_ROD)
                            .define('3', Tags.Items.ENDER_PEARLS)
                            .define('4', Items.CLOCK)
                            .define('5', AEItems.ENCHANTMENT_VESSEL));

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.FEATHER_TOUCH)),
                    holderLookup,
                    AEEnchantments.FEATHER_TOUCH,
                    builder -> {
                        var silk_touch = holderLookup.holderOrThrow(Enchantments.SILK_TOUCH);
                        builder.unlockedBy(getHasName(AEItems.ENCHANTMENT_VESSEL), has(AEItems.ENCHANTMENT_VESSEL))
                                .pattern("121")
                                .pattern("343")
                                .pattern("565")
                                .define('1', Tags.Items.FEATHERS)
                                .define('2', Items.AMETHYST_BLOCK)
                                .define('3', Items.DEEPSLATE)
                                .define('4', enchantedBook(silk_touch, 1))
                                .define('5', Items.ECHO_SHARD)
                                .define('6', AEItems.ENCHANTMENT_VESSEL);
                    });

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.AFFINITY)),
                    holderLookup,
                    AEEnchantments.AFFINITY,
                    builder -> builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                            .pattern("121")
                            .pattern("232")
                            .pattern("141")
                            .define('1', AETags.Items.GEMS_LUMINITE)
                            .define('2', Items.ANVIL)
                            .define('3', Items.HEART_OF_THE_SEA)
                            .define('4', AEItems.RESONANT_VESSEL));

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.OVERLOAD)),
                    holderLookup,
                    AEEnchantments.OVERLOAD,
                    builder -> {
                        var affinity = holderLookup.holderOrThrow(AEEnchantments.AFFINITY);
                        var eoe = holderLookup.holderOrThrow(AEEnchantments.ESSENCE_OF_ENCHANTMENT);
                        builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                                .pattern("121")
                                .pattern("343")
                                .pattern("121")
                                .define('1', Tags.Items.NETHER_STARS)
                                .define('2', enchantedBook(affinity, 1))
                                .define('3', enchantedBook(eoe, 1))
                                .define('4', AEItems.RESONANT_VESSEL);
                    });

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.REACTIVE_ARMOR)),
                    holderLookup,
                    AEEnchantments.REACTIVE_ARMOR,
                    builder -> {
                        var prot = holderLookup.holderOrThrow(Enchantments.PROTECTION);
                        var projectile_prot = holderLookup.holderOrThrow(Enchantments.PROJECTILE_PROTECTION);
                        var blast_prot = holderLookup.holderOrThrow(Enchantments.BLAST_PROTECTION);
                        var fire_prot = holderLookup.holderOrThrow(Enchantments.FIRE_PROTECTION);
                        builder.unlockedBy(getHasName(AEItems.ENCHANTMENT_VESSEL), has(AEItems.ENCHANTMENT_VESSEL))
                                .pattern("123")
                                .pattern("242")
                                .pattern("567")
                                .define('1', enchantedBook(prot, 4))
                                .define('2', potion(Potions.TURTLE_MASTER))
                                .define('3', enchantedBook(projectile_prot, 4))
                                .define('4', Items.SCULK_CATALYST)
                                .define('5', enchantedBook(blast_prot, 4))
                                .define('6', AEItems.ENCHANTMENT_VESSEL)
                                .define('7', enchantedBook(fire_prot, 4));
                    });

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.CURSE_OF_IGNORANCE)),
                    holderLookup,
                    AEEnchantments.CURSE_OF_IGNORANCE,
                    builder -> builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                            .pattern("121")
                            .pattern("232")
                            .pattern("141")
                            .define('1', Tags.Items.DYED_BLACK)
                            .define('2', Items.INK_SAC)
                            .define('3', Tags.Items.DYES_BLACK)
                            .define('4', AEItems.RESONANT_VESSEL));

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.CURSE_OF_ENCHANTMENT)),
                    holderLookup,
                    AEEnchantments.CURSE_OF_ENCHANTMENT,
                    builder -> {
                        var vanishing = holderLookup.holderOrThrow(Enchantments.VANISHING_CURSE);
                        var binding = holderLookup.holderOrThrow(Enchantments.BINDING_CURSE);
                        var ignorance = holderLookup.holderOrThrow(AEEnchantments.CURSE_OF_IGNORANCE);
                        builder.unlockedBy(getHasName(AEItems.RESONANT_VESSEL), has(AEItems.RESONANT_VESSEL))
                                .pattern("121")
                                .pattern("345")
                                .pattern("161")
                                .define('1', Tags.Items.CHAINS)
                                .define('2', enchantedBook(ignorance, 1))
                                .define('3', enchantedBook(vanishing, 1))
                                .define('4', Tags.Items.OBSIDIANS_CRYING)
                                .define('5', enchantedBook(binding, 1))
                                .define('6', AEItems.RESONANT_VESSEL);
                    });

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.DISTORTION)),
                    holderLookup,
                    AEEnchantments.DISTORTION,
                    builder -> {
                        var sweeping = holderLookup.holderOrThrow(Enchantments.SWEEPING_EDGE);
                        builder.unlockedBy(getHasName(AEItems.ENCHANTMENT_VESSEL), has(AEItems.ENCHANTMENT_VESSEL))
                                .pattern("121")
                                .pattern("232")
                                .pattern("141")
                                .define('1', Tags.Items.TOOLS_FISHING_ROD)
                                .define('2', enchantedBook(sweeping, 3))
                                .define('3', Tags.Items.ENDER_PEARLS)
                                .define('4', AEItems.ENCHANTMENT_VESSEL);
                    });

            enchantedBookRecipe(
                    output.withConditions(new ConfigCondition(ConfigID.ALMIGHTY)),
                    holderLookup,
                    AEEnchantments.ALMIGHTY,
                    builder -> {
                        var efficiency = holderLookup.holderOrThrow(Enchantments.EFFICIENCY);
                        builder.unlockedBy(getHasName(AEItems.ENCHANTMENT_VESSEL), has(AEItems.ENCHANTMENT_VESSEL))
                                .pattern("123")
                                .pattern("242")
                                .pattern("567")
                                .define('1', ItemTags.PICKAXES)
                                .define('2', enchantedBook(efficiency, 5))
                                .define('3', ItemTags.AXES)
                                .define('4', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                                .define('5', ItemTags.SHOVELS)
                                .define('6', AEItems.ENCHANTMENT_VESSEL)
                                .define('7', ItemTags.HOES);
                    });
        }

        // mod連携レシピ
        {
            InscriberRecipeBuilder.inscribe(AETags.Items.GEMS_ARCANE_QUARTZ, AEItems.ARCANE_QUARTZ_TINY_DUST, 1)
                    .save(
                            output.withConditions(new ModLoadedCondition("ae2")),
                            ModUtils.loc("arcane_quartz_inscribing"));
            InscriberRecipeBuilder.inscribe(AEItems.GROWN_ARCANE_QUARTZ, AEItems.ARCANE_QUARTZ_DUST, 1)
                    .save(
                            output.withConditions(new ModLoadedCondition("ae2")),
                            ModUtils.loc("grown_arcane_quartz_inscribing"));
            TransformRecipeBuilder.transform(
                    output.withConditions(new ModLoadedCondition("ae2")),
                    ModUtils.loc("lavaproof_arcane_quartz_transformation"),
                    AEItems.LUMINITE,
                    1,
                    TransformCircumstance.fluid(FluidTags.LAVA),
                    AEItems.LAVAPROOF_ARCANE_QUARTZ);
            ItemStackToItemStackRecipeBuilder.crushing(
                            ItemStackIngredient.of(SizedIngredient.of(AETags.Items.GEMS_ARCANE_QUARTZ, 1)),
                            new ItemStack(AEItems.ARCANE_QUARTZ_TINY_DUST.get()))
                    .build(
                            output.withConditions(new ModLoadedCondition("mekanism")),
                            ModUtils.loc("arcane_quartz_crushing"));
            ItemStackToItemStackRecipeBuilder.crushing(
                            ItemStackIngredient.of(SizedIngredient.of(AEItems.GROWN_ARCANE_QUARTZ, 1)),
                            new ItemStack(AEItems.ARCANE_QUARTZ_DUST.get()))
                    .build(
                            output.withConditions(new ModLoadedCondition("mekanism")),
                            ModUtils.loc("grown_arcane_quartz_crushing"));
            PulverizerRecipeBuilder.build()
                    .input(AETags.Items.GEMS_ARCANE_QUARTZ)
                    .result(AEItems.ARCANE_QUARTZ_TINY_DUST.get())
                    .export(output.withConditions(new ModLoadedCondition("oritech")), ID.ARCANE_QUARTZ);
            GrinderRecipeBuilder.build()
                    .input(AETags.Items.GEMS_ARCANE_QUARTZ)
                    .result(AEItems.ARCANE_QUARTZ_TINY_DUST.get())
                    .export(output.withConditions(new ModLoadedCondition("oritech")), ID.ARCANE_QUARTZ);
            PulverizerRecipeBuilder.build()
                    .input(AEItems.GROWN_ARCANE_QUARTZ)
                    .result(AEItems.ARCANE_QUARTZ_DUST.get())
                    .export(output.withConditions(new ModLoadedCondition("oritech")), ID.GROWN_ARCANE_QUARTZ);
            GrinderRecipeBuilder.build()
                    .input(AEItems.GROWN_ARCANE_QUARTZ)
                    .result(AEItems.ARCANE_QUARTZ_DUST.get())
                    .export(output.withConditions(new ModLoadedCondition("oritech")), ID.GROWN_ARCANE_QUARTZ);
            CentrifugeFluidRecipeBuilder.build()
                    .input(AEItems.LAVAPROOF_ARCANE_QUARTZ)
                    .fluidInput(Fluids.LAVA, 0.25f)
                    .result(AEItems.LUMINITE.get())
                    .export(output.withConditions(new ModLoadedCondition("oritech")), ID.LAVAPROOF_ARCANE_QUARTZ);
        }
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
            RecipeOutput output,
            HolderLookup.Provider provider,
            ResourceKey<Enchantment> key,
            Consumer<ShapedRecipeBuilder> consumer) {
        enchantedBookRecipe(output, provider, key, 1, consumer);
    }

    private void enchantedBookRecipe(
            RecipeOutput output,
            HolderLookup.Provider provider,
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
