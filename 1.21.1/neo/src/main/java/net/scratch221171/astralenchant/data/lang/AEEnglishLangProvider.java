package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ConfigID;

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

        // エンチャント
        addEnchantmentWithDesc(
                AEEnchantments.NULLIFICATION,
                "Nullification",
                "Negates various damage reductions, and makes attacks deal their full damage.");
        addEnchantmentWithDesc(AEEnchantments.LAST_STAND, "Last Stand", "Consumes experience points to avoid death.");
        addEnchantmentWithDesc(
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                "Essence of Enchantment",
                "Increases all item stats based on the total level of all enchantments other than this one.");
        addEnchantmentWithDesc(
                AEEnchantments.FEATHER_TOUCH,
                "Feather Touch",
                "Collects even normally unobtainable blocks as an item. Preserves block entity data, and while sneaking, preserves block states as well.");
        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "Overload", "Increases the level of existing enchantments.");
        addEnchantmentWithDesc(
                AEEnchantments.REACTIVE_ARMOR,
                "Reactive Armor",
                "Negates effects that bypass armor and enchantments for certain types of damage.");

        // 設定
        addConfig("enchantment_settings", "Enchantment Settings");
        addConfigWithDesc(
                ConfigID.IGNORE_BYPASSES_INVULNERABILITY_TAG,
                "Ignore Invulnerability-Piercing Damage",
                "If disabled, Last Stand will not activate against damage with the BYPASSES_INVULNERABILITY tag (such as /kill or the void).");
        addConfigWithDesc(
                ConfigID.BASE_COST,
                "Base Level Cost",
                "At level n, the consumed experience levels are calculated as: (Current Experience Levels) * (Cost Rate) / √n + (Base Cost) / n");
        addConfigWithDesc(
                ConfigID.COST_RATE,
                "Level Cost Rate",
                "At level n, the consumed experience levels are calculated as: (Current Experience Levels) * (Cost Rate) / √n + (Base Cost) / n");

        addConfigWithDesc(
                "enchantment_toggling",
                "Enchantment Toggling",
                "Note: You must restart the world to apply the changes!");
    }
}
