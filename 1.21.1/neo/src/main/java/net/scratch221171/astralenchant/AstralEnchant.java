package net.scratch221171.astralenchant;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.scratch221171.astralenchant.common.registry.AEConditions;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.tag.TagGroupLoader;
import net.scratch221171.astralenchant.config.ModConfigs;
import net.scratch221171.astralenchant.mdk.config.PlatformConfigRegistrar;
import net.scratch221171.astralenchant.mdk.config.VersionedConfigSpec;

@Mod(Constants.MODID)
public class AstralEnchant {
    public AstralEnchant(IEventBus modEventBus, ModContainer modContainer) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.21.1-neo"));
        PlatformConfigRegistrar.registerAll(modContainer, VersionedConfigSpec.bindAll(ModConfigs.ALL));

        modEventBus.addListener(this::commonSetup);
        AEItems.register(modEventBus);
        AEDataComponents.register(modEventBus);
        AEConditions.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::onAddReloadListeners);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(TagGroupLoader.INSTANCE);
    }
}
