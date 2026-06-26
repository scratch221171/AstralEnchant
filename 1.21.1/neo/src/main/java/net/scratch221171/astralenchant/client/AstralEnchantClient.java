package net.scratch221171.astralenchant.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.client.mdk.config.KeyedConfigScreen;

@Mod(value = Constants.MODID, dist = Dist.CLIENT)
public class AstralEnchantClient {
    public AstralEnchantClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (mod, parent) ->
                new ConfigurationScreen(mod, parent, KeyedConfigScreen::new)
        );
    }
}
