package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
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
        addEnchantmentWithDesc(AEEnchantments.RESILIENCE, "Resilience", "Reduces the cooldown of all items.");
        addEnchantmentWithDesc(
                AEEnchantments.FEATHER_TOUCH,
                "Feather Touch",
                "Collects even normally unobtainable blocks as an item. Preserves block entity data, and while sneaking, preserves block states as well.");
        addEnchantmentWithDesc(
                AEEnchantments.AFFINITY,
                "Affinity",
                "Ignores all restrictions and allows any enchantment to be applied.");
        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "Overload", "Increases the level of existing enchantments.");
        addEnchantmentWithDesc(
                AEEnchantments.REACTIVE_ARMOR,
                "Reactive Armor",
                "Negates effects that bypass armor and enchantments for certain types of damage.");
        addEnchantmentWithDesc(
                AEEnchantments.CURSE_OF_IGNORANCE, "Curse of Ignorance", "Replaces the tooltip with obscured text.");
        addEnchantmentWithDesc(
                AEEnchantments.CURSE_OF_ENCHANTMENT,
                "Curse of Enchantment",
                "Locks the item's enchantments, prevents them from being changed.");
        addEnchantmentWithDesc(
                AEEnchantments.ALMIGHTY,
                "Almighty",
                "Makes the tool suitable for all blocks. Enables mining of indestructible blocks.");

        // attribute
        addAttributeWithDesc(
                AEAttributes.COOLDOWN_DURATION,
                "Cooldown Duration",
                "Multiplier applied to item use cooldown duration");

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
                ConfigID.INCLUDE_OVERLOAD_IN_TOTAL,
                "Include Overload in total",
                "Whether to include bonuses from overloading applied to other enchantments in the total level. Regardless of this setting, Overload levels are always applied to the level of Essence of Enchant itself.");
        addConfigWithDesc(
                ConfigID.MULTIPLIER,
                "Modifier Bonus per Level (%)",
                "Example: If set to 1, the modifier is increased by +20% at a total level of 20.");
        addConfigWithDesc(
                ConfigID.UNBREAKABLE_BLOCK_HARDNESS,
                "Unbreakable Block Hardness",
                "The hardness value applied when mining unbreakable blocks, such as bedrock.");
        addConfigWithDesc(
                ConfigID.EFFECTIVE_FOR_ALL_BLOCKS,
                "Effective for All Blocks",
                "Whether the mining speed bonus is applied to blocks that do not have a preferred tool, such as glass.");

        addConfigWithDesc("enchantment_toggling", "Enchantment Toggling", "REQUIRE SERVER RESTART!");
    }
}
