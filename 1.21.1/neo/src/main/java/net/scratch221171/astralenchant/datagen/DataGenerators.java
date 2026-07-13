package net.scratch221171.astralenchant.datagen;

import java.util.List;
import java.util.Set;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.datagen.block.AEBlockLootTableProvider;
import net.scratch221171.astralenchant.datagen.block.AEBlockStateProvider;
import net.scratch221171.astralenchant.datagen.bootstrap.AEEnchantmentBootstrap;
import net.scratch221171.astralenchant.datagen.lang.AEEnglishLangProvider;
import net.scratch221171.astralenchant.datagen.lang.AEJapaneseLangProvider;
import net.scratch221171.astralenchant.datagen.model.AEItemModelProvider;
import net.scratch221171.astralenchant.datagen.recipe.AERecipeProvider;
import net.scratch221171.astralenchant.datagen.tag.AEBlockTagsProvider;
import net.scratch221171.astralenchant.datagen.tag.AEEnchantmentTagsProvider;
import net.scratch221171.astralenchant.datagen.tag.AEItemTagsProvider;

@EventBusSubscriber(modid = Constants.MODID)
public final class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        Constants.LOGGER.info("Loading DataGenerators");
        var lookup = event.getLookupProvider();
        var fileHelper = event.getExistingFileHelper();

        // Server
        event.createDatapackRegistryObjects(
                new RegistrySetBuilder().add(Registries.ENCHANTMENT, AEEnchantmentBootstrap::bootstrap),
                AEEnchantmentBootstrap::applyConditions);

        event.createProvider((output, future) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        AEBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
                future));

        event.createProvider(AERecipeProvider::new);

        event.createProvider(AEEnchantmentTagsProvider::new);
        event.createBlockAndItemTags(AEBlockTagsProvider::new, AEItemTagsProvider::new);
        // Client
        event.createProvider(output -> new AEBlockStateProvider(output, fileHelper));
        event.createProvider(output -> new AEItemModelProvider(output, fileHelper));

        event.createProvider(AEEnglishLangProvider::new);
        event.createProvider(AEJapaneseLangProvider::new);
    }
}
