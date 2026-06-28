package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ServerConfig;

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
        addEnchantWithDesc(
                AEEnchantments.NULLIFICATION,
                "Nullification",
                "Various damage reductions are negated, and attacks deal their full damage.");
        addEnchantWithDesc(
                AEEnchantments.LAST_STAND,
                "Last Stand",
                "Consumes experience points to avoid death.");
        addEnchantWithDesc(
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                "Essence of Enchantment",
                "All item stats increase based on the total level of all enchantments other than this one.");
        addEnchantWithDesc(
                AEEnchantments.OVERLOAD,
                "Overload",
                "Increase the level of existing enchantments by the Overload value.");
        addEnchantWithDesc(
                AEEnchantments.REACTIVE_ARMOR,
                "Reactive Armor",
                "Negates effects that bypass armor and enchantments for certain types of damage.");

        // 設定
        add(Constants.MODID + ".configuration.enchantment_settings", "Enchantment Settings");
        addConfigWithDesc(ServerConfig.EnchantmentSettings.LastStand.IGNORE_BYPASSES_INVULNERABILITY_TAG, "Ignore Invulnerability-Piercing Damage", "If disabled, Last Stand will not activate against damage with the BYPASSES_INVULNERABILITY tag (such as /kill or the void).");
        addConfigWithDesc(ServerConfig.EnchantmentSettings.LastStand.BASE_COST, "Base Level Cost", "At level n, the consumed experience levels are calculated as: (Current Experience Levels) * (Cost Rate) / √n + (Base Cost) / n");
        addConfigWithDesc(ServerConfig.EnchantmentSettings.LastStand.COST_RATE, "Level Cost Rate", "At level n, the consumed experience levels are calculated as: (Current Experience Levels) * (Cost Rate) / √n + (Base Cost) / n");
    }
}
