package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.Constants;

public abstract class AELangProvider extends LanguageProvider {
    public AELangProvider(PackOutput output, String locale) {
        super(output, Constants.MODID, locale);
    }

    protected void addEnchantmentWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(key.location().toLanguageKey("enchantment"), name);
        add(key.location().toLanguageKey("enchantment", "desc"), desc);
        add(Constants.MODID + ".configuration." + key.location().getPath(), name);
    }

    protected void addConfig(String configID, String name) {
        add(Constants.MODID + ".configuration." + configID, name);
    }

    protected void addConfigWithDesc(String configID, String name, String desc) {
        add(Constants.MODID + ".configuration." + configID, name);
        add(Constants.MODID + ".configuration." + configID + ".tooltip", desc);
    }
}
