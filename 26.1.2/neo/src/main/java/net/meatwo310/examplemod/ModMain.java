package net.scratch221171.astralenchant;

import net.scratch221171.astralenchant.config.ModConfigs;
import net.scratch221171.astralenchant.mdk.config.PlatformConfigRegistrar;
import net.scratch221171.astralenchant.mdk.config.VersionedConfigSpec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain(IEventBus modEventBus, ModContainer modContainer) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.id("26.1.2-neo"));
        PlatformConfigRegistrar.registerAll(modContainer, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
