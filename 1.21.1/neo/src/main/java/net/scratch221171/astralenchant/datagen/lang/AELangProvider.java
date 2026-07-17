package net.scratch221171.astralenchant.datagen.lang;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.util.AEUtil;

public abstract class AELangProvider extends LanguageProvider {
    public AELangProvider(PackOutput output, String locale) {
        super(output, Constants.MODID, locale);
    }

    protected void addItemWithDesc(DeferredItem<?> holder, String name, String desc) {
        addItem(holder, name);
        add(holder.get().getDescriptionId() + ".desc", desc);
    }

    protected void addEnchantmentWithDesc(ResourceKey<Enchantment> key, String name, String desc) {
        add(AEUtil.getLangKey(key), name);
        add(AEUtil.getDescLangKey(key), desc);
        add(Constants.MODID + ".configuration." + key.location().getPath(), name);
    }

    protected void addAttributeWithDesc(Holder<Attribute> holder, String name, String desc) {
        add(holder.unwrapKey().orElseThrow().location().toLanguageKey("attribute"), name);
        add(holder.unwrapKey().orElseThrow().location().toLanguageKey("attribute", "desc"), desc);
    }

    protected void addAdvancementWithDesc(String id, String name, String desc) {
        add("advancements." + Constants.MODID + "." + id + ".title", name);
        add("advancements." + Constants.MODID + "." + id + ".description", desc);
    }

    protected void addConfig(String configID, String name) {
        add(Constants.MODID + ".configuration." + configID, name);
    }

    protected void addConfigWithDesc(String configID, String name, String desc) {
        add(Constants.MODID + ".configuration." + configID, name);
        add(Constants.MODID + ".configuration." + configID + ".tooltip", desc);
    }
}
