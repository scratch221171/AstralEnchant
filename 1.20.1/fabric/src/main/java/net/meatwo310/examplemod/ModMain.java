package net.scratch221171.astralenchant;

import net.fabricmc.api.ModInitializer;
import net.scratch221171.astralenchant.config.ModConfigs;
import net.scratch221171.astralenchant.mdk.config.PlatformConfigRegistrar;
import net.scratch221171.astralenchant.mdk.config.VersionedConfigSpec;

public class ModMain implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.20.1-fabric"));
        PlatformConfigRegistrar.registerAll(Constants.MODID, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
