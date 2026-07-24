package net.scratch221171.astralenchant.datagen.lang;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ConfigID;

public class AEEnglishLangProvider extends AELangProvider implements ModonomiconLanguageProvider {

    public AEEnglishLangProvider(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("modmenu.descriptionTranslation." + Constants.MODID, "Adds enchantments you've never seen before.");
        // アイテム
        addItem(AEItems.ENCHANTMENT_SHARD, "Enchantment Shard");
        addItemWithDesc(
                AEItems.ARCANE_QUARTZ,
                "Arcane Quartz",
                "Its radiance has enthralled countless enchanters since ancient times.");
        addItem(AEItems.GROWN_ARCANE_QUARTZ, "Grown Arcane Quartz Dust");
        addItem(AEItems.RESONANT_ARCANE_QUARTZ, "Resonant Arcane Quartz");
        addItem(AEItems.ARCANE_QUARTZ_DUST, "Arcane Quartz Dust");
        addItemWithDesc(
                AEItems.ARCANE_QUARTZ_TINY_DUST,
                "Arcane Quartz Tiny Dust",
                "Obtained by crushing Arcane Quartz using a falling anvil.");
        addItemWithDesc(AEItems.LAVAPROOF_ARCANE_QUARTZ, "Lavaproof Arcane Quartz", "Whoa! It's all sticky!");
        addItemWithDesc(AEItems.LUMINITE, "Luminite", "Can be used as fuel for the Enchanter's Workbench.");
        addItem(AEItems.ARCANIUM_INGOT, "Arcanium Ingot");
        addItem(AEItems.BUDDING_ARCANIUM_INGOT, "Budding Arcanium Ingot");
        addItem(AEItems.ENCHANTMENT_VESSEL, "Enchantment Vessel");
        addItem(AEItems.RESONANT_VESSEL, "Resonant Vessel");

        addItemWithDesc(
                AEItems.ARCANIUM_HELMET,
                "Arcanium Helmet",
                "When the item breaks, its enchantments are dropped as enchanted books.");
        addItemWithDesc(
                AEItems.ARCANIUM_CHESTPLATE,
                "Arcanium Chestplate",
                "When the item breaks, its enchantments are dropped as enchanted books.");
        addItemWithDesc(
                AEItems.ARCANIUM_LEGGINGS,
                "Arcanium Leggings",
                "When the item breaks, its enchantments are dropped as enchanted books.");
        addItemWithDesc(
                AEItems.ARCANIUM_BOOTS,
                "Arcanium Boots",
                "When the item breaks, its enchantments are dropped as enchanted books.");

        // ブロック
        addBlock(AEBlocks.ARCANIUM_BLOCK, "Arcanium Block");
        addBlock(AEBlocks.ENCHANTERS_WORKBENCH, "Enchanter's Workbench");
        add("container." + Constants.MODID + ".enchanters_workbench", "Switch Enchantments");
        add("gui.astralenchant.enchanters_workbench.locked_curse", "This curse can't be toggled!");
        add(
                "gui.astralenchant.enchanters_workbench.locked_conflict",
                "Can't be enabled because it conflicts with another enchantment!");
        add("container.enchanters_workbench.cost", "Switching Cost: %1$s");

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
                AEEnchantments.ENDLESS_APPETITE,
                "Endless Appetite",
                "Increase the maximum FoodLevel and SaturationLevel.");
        addEnchantmentWithDesc(
                AEEnchantments.AFFINITY,
                "Affinity",
                "Ignores all restrictions and allows any enchantment to be applied.");
        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "Overload", "Increases the level of existing enchantments.");
        addEnchantmentWithDesc(
                AEEnchantments.EXPANSE,
                "Expanse",
                "Increases the number of curio slots the item currently occupies by the enchantment level.");
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
        addEnchantmentWithDesc(AEEnchantments.DISTORTION, "Distortion", "Deflects missed attacks to nearby enemies.");
        addEnchantmentWithDesc(
                AEEnchantments.ALMIGHTY,
                "Almighty",
                "Makes the tool suitable for all blocks. Enables mining of indestructible blocks.");

        // attribute
        addAttributeWithDesc(
                AEAttributes.COOLDOWN_DURATION,
                "Cooldown Duration",
                "Multiplier applied to item use cooldown duration");
        addAttributeWithDesc(
                AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER, "Food Level Cap", "Multiplier for the Food Level Cap");
        addAttributeWithDesc(
                AEAttributes.SATURATION_LEVEL_CAP_MULTIPLIER,
                "Saturation Level Cap",
                "Multiplier for the Saturation Level Cap");

        // データコンポーネント
        add("astralenchant.item.disabled_enchantments", "disabled enchantment(s): %s");

        // クリエタブ
        add("itemGroup.astralenchant.main", "Astral Enchant");
        add("itemGroup.astralenchant.enchantment", "Astral Enchant - Enchantment");

        // 進捗
        addAdvancementWithDesc(ID.ROOT, "Astral Enchant", "You're an enchanter starting today!");
        addAdvancementWithDesc(
                ID.ENCHANTMENT_VESSEL,
                "A Curious Tome",
                "Obtain an Enchantment Vessel. Let's craft some magnificent enchantments!");
        addAdvancementWithDesc(
                ID.BUDDING_ARCANIUM_INGOT,
                "Growing Crystals",
                "Harvest a Budding Amethyst block and embed it into an Arcanium Ingot.");
        addAdvancementWithDesc(
                ID.RESONANT_VESSEL,
                "Mystical Grimoire",
                "Obtain a Resonant Vessel. Let's craft even greater enchantments!");
        addAdvancementWithDesc(
                ID.ENCHANTERS_WORKBENCH,
                "Just What I Needed!",
                "Craft an Enchanter's Workbench. Swap your enchantments at will!");

        // 設定
        addConfig("enchantment_settings", "Enchantment Settings");
        addConfigWithDesc(
                ConfigID.EXTRA_TAG,
                "Applied Tags",
                "Manages the tags applied to attacks. If any invalid values are present, the default values will be used.");
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
                ConfigID.MAX_COST,
                "Maximum Cost",
                "Regardless of the two settings above, no more experience than this amount will be consumed.");
        addConfigWithDesc(
                ConfigID.RECOVER_HEALTH,
                "Health After Revival",
                "The player's health after being revived by the effect. If set to 0, the player will die again on the next tick.");
        addConfigWithDesc(
                ConfigID.INCLUDE_OVERLOAD_IN_TOTAL,
                "Include Overload in total",
                "Whether to include bonuses from overloading applied to other enchantments in the total level. Regardless of this setting, Overload levels are always applied to the level of Essence of Enchant itself.");
        addConfigWithDesc(
                ConfigID.MULTIPLIER,
                "Modifier Bonus per Level (%)",
                "Example: If set to 1, the modifier is increased by +20% at a total level of 20.");
        addConfigWithDesc(
                ConfigID.MAX_TOTAL_LEVEL,
                "Maximum Total Level",
                "Caps the total enchantment level used to prevent unlimited stat scaling from stacking excessive enchantments.");
        addConfigWithDesc(
                ConfigID.CURIOS_COMPAT,
                "Curios Item Compatibility",
                "Bonuses also apply to modifiers on Curios items.");
        addConfigWithDesc(
                ConfigID.SAVE_BLOCK_ENTITY,
                "Preserve Block Entity",
                "Converts blocks into items while preserving their block entity data, such as the contents of a chest.");
        addConfigWithDesc(
                ConfigID.SAVE_BLOCK_STATE,
                "Preserve Block State",
                "Converts blocks into items while preserving their block state, such as orientation.");
        addConfigWithDesc(
                ConfigID.STACKABLE,
                "Can be stacked",
                "You can increase the number of slots indefinitely by nesting them.");
        addConfigWithDesc(
                ConfigID.DISABLED_TAG,
                "Removed Tags",
                "Manages the tags removed from attacks. If any invalid values are present, the default values will be used.");
        addConfigWithDesc(
                ConfigID.HIDE_NAME,
                "Hide Item Name",
                "Disabling this may improve compatibility with certain tooltip decoration mods.");
        addConfigWithDesc(
                ConfigID.ANGLE_PER_LEVEL,
                "Angle Per Level",
                "At level n, the allowable difference between the orientation toward an entity and the viewing angle is expressed by the following formula: n * (angle) * π / 180");
        addConfigWithDesc(
                ConfigID.EFFECTIVE_FOR_ALL_BLOCKS,
                "Effective for All Blocks",
                "Whether the mining speed bonus is applied to blocks that do not have a preferred tool, such as glass.");
        addConfigWithDesc(
                ConfigID.CAN_BREAK_UNBREAKABLE,
                "Break Unbreakable Blocks",
                "At level 4, allows breaking normally unbreakable blocks such as Bedrock. Behavior is not guaranteed when used by non-player entities.");
        addConfigWithDesc(
                ConfigID.UNBREAKABLE_BLOCK_HARDNESS,
                "Unbreakable Block Hardness",
                "The hardness value applied when mining unbreakable blocks, such as bedrock.");
        addConfigWithDesc("enchantment_toggling", "Enchantment Toggling", "REQUIRE SERVER RESTART!");
    }

    @Override
    public void accept(String s, String s2) {
        this.add(s, s2);
    }
}
