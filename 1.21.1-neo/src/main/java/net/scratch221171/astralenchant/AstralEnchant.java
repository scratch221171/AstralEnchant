package net.scratch221171.astralenchant;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.scratch221171.astralenchant.common.item.AEItems;
import net.scratch221171.astralenchant.config.ModConfigs;
import net.scratch221171.astralenchant.mdk.config.PlatformConfigRegistrar;
import net.scratch221171.astralenchant.mdk.config.VersionedConfigSpec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MODID)
public class AstralEnchant {
    public AstralEnchant(IEventBus modEventBus, ModContainer modContainer) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.21.1-neo-aaaaa"));
        PlatformConfigRegistrar.registerAll(modContainer, VersionedConfigSpec.bindAll(ModConfigs.ALL));

        modEventBus.addListener(this::commonSetup);
        AEItems.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        Constants.LOGGER.info("Hello, World!");
    }
}
