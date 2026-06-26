package net.scratch221171.astralenchant.data;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.data.bootstrap.AEEnchantmentBootstrap;
import net.scratch221171.astralenchant.data.lang.AEEnglishLangProvider;
import net.scratch221171.astralenchant.data.lang.AEJapaneseLangProvider;
import net.scratch221171.astralenchant.data.model.AEItemModelProvider;
import net.scratch221171.astralenchant.data.tag.AEDamageTypeTagsProvider;

@EventBusSubscriber(modid = Constants.MODID)
public final class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        Constants.LOGGER.info("Loading DataGenerators");
        var lookupProvider = event.getLookupProvider();
        var fileHelper = event.getExistingFileHelper();

        event.createDatapackRegistryObjects(
                new RegistrySetBuilder().add(Registries.ENCHANTMENT, AEEnchantmentBootstrap::bootstrap),
                AEEnchantmentBootstrap::applyConditions);

        event.createProvider(AEEnglishLangProvider::new);
        event.createProvider(AEJapaneseLangProvider::new);

        event.createProvider(output -> new AEItemModelProvider(output, fileHelper));
    }
}
