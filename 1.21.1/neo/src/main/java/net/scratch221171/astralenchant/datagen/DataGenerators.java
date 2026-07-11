package net.scratch221171.astralenchant.datagen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.Constants;
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

        event.createDatapackRegistryObjects(
                new RegistrySetBuilder().add(Registries.ENCHANTMENT, AEEnchantmentBootstrap::bootstrap),
                AEEnchantmentBootstrap::applyConditions);

        event.createProvider(output -> new AERecipeProvider(output, lookup));

        event.createBlockAndItemTags(AEBlockTagsProvider::new, AEItemTagsProvider::new);
        event.createProvider(AEEnchantmentTagsProvider::new);

        event.createProvider(output -> new AEItemModelProvider(output, fileHelper));

        event.createProvider(AEEnglishLangProvider::new);
        event.createProvider(AEJapaneseLangProvider::new);
    }
}
