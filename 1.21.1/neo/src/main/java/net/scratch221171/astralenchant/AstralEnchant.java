package net.scratch221171.astralenchant;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.scratch221171.astralenchant.common.data.TagGroupLoader;
import net.scratch221171.astralenchant.common.registry.*;
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
        AEBlocks.register(modEventBus);
        AEDataComponents.register(modEventBus);
        AECreativeTabs.register(modEventBus);
        AEAttributes.register(modEventBus);
        AEConditions.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::onAddReloadListeners);

        NeoForgeMod.enableMergedAttributeTooltips();
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(TagGroupLoader.INSTANCE);
    }
}
