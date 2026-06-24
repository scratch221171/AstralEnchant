package net.scratch221171.astralenchant.data;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.data.model.AEItemModelProvider;

@EventBusSubscriber(modid = Constants.MODID)
public final class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        Constants.LOGGER.info("Loading DataGenerators");
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        event.createProvider(output -> new AEItemModelProvider(output, fileHelper));
    }
}
