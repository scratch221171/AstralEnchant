package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.common.item.AEItems;

public class AEEnglishLangProvider extends AELangProvider {

    public AEEnglishLangProvider(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations() {
        // アイテム
        addItem(AEItems.ENCHANTMENT_SHARD, "Enchantment Shard");
        addItem(AEItems.ARCANE_QUARTZ, "Arcane Quartz");
        addItem(AEItems.ARCANIUM_INGOT, "Arcanium Ingot");
        addItem(AEItems.BUDDING_ARCANIUM_INGOT, "Budding Arcanium Ingot");
    }
}
