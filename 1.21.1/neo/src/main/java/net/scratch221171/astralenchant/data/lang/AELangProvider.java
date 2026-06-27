package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.mdk.config.ConfigEntry;

public abstract class AELangProvider extends LanguageProvider {
    public AELangProvider(PackOutput output, String locale) {
        super(output, Constants.MODID, locale);
    }

    protected void addEnchantWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(key.location().toLanguageKey("enchantment"), name);
        add(key.location().toLanguageKey("enchantment", "desc"), desc);
        add(Constants.MODID + ".configuration." + key.location().getPath(), name);
    }

    protected <T> void addConfigWithDesc(ConfigEntry<T> configEntry, String name, String desc) {
        add(generateTranslationKeyPath(configEntry), name);
        add(generateTranslationKeyPath(configEntry) + ".tooltip", desc);
    }

    public static String generateTranslationKeyPath(ConfigEntry<?> entry) {
        return Constants.MODID + ".configuration." + entry.key();
    }
}
