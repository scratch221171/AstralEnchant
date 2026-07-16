package net.scratch221171.astralenchant.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.client.mdk.config.KeyedConfigScreen;
import net.scratch221171.astralenchant.client.screen.EnchantersWorkbenchScreen;
import net.scratch221171.astralenchant.common.registry.AEMenuTypes;

@Mod(value = Constants.MODID, dist = Dist.CLIENT)
public class AstralEnchantClient {
    public AstralEnchantClient(ModContainer container, IEventBus modEventBus) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (mod, parent) -> new ConfigurationScreen(mod, parent, KeyedConfigScreen::new));

        modEventBus.addListener(this::registerScreens);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(AEMenuTypes.ENCHANTERS_WORKBENCH.get(), EnchantersWorkbenchScreen::new);
    }
}
