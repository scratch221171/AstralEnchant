package net.scratch221171.astralenchant.datagen.lang;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
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
        add("modmenu.descriptionTranslation.astralenchant", "In search of enchantments yet to be discovered...");
        // アイテム
        addItem(AEItems.ENCHANTMENT_SHARD, "Enchantment Shard");
        addItemWithDesc(AEItems.ARCANE_QUARTZ, "Arcane Quartz", "Probably not quartz.");
        addItemWithDesc(AEItems.GROWN_ARCANE_QUARTZ, "Grown Arcane Quartz", "Not quartz, to be precise.");
        addItemWithDesc(AEItems.RESONANT_ARCANE_QUARTZ, "Resonant Arcane Quartz", "Definitely not quartz.");
        addItem(AEItems.ARCANE_QUARTZ_DUST, "Arcane Quartz Dust");
        addItem(AEItems.ARCANE_QUARTZ_TINY_DUST, "Arcane Quartz Tiny Dust");
        addItemWithDesc(AEItems.LAVAPROOF_ARCANE_QUARTZ, "Lavaproof Arcane Quartz", "Whoa! It's all sticky!");
        addItemWithDesc(AEItems.LUMINITE, "Luminite", "What a brilliant glow!");
        addItem(AEItems.ARCANIUM_INGOT, "Arcanium Ingot");
        addItem(AEItems.BUDDING_ARCANIUM_INGOT, "Budding Arcanium Ingot");
        addItem(AEItems.ENCHANTMENT_VESSEL, "Enchantment Vessel");
        addItem(AEItems.RESONANT_VESSEL, "Resonant Vessel");
        addItem(AEItems.ROTTEN_BONE, "Rotten Bone");
        addItemWithDesc(AEItems.ALLAY_WING, "Allay Wing", "How inhumane!");
        addItem(AEItems.VEX_WING, "Vex Wing");
        addItemWithDesc(AEItems.ENCHANTMENT_SEEDS, "Enchantment Seeds", "Resets the player's enchantment seed.");

        addItem(AEItems.ARCANIUM_HELMET, "Arcanium Helmet");
        addItem(AEItems.ARCANIUM_CHESTPLATE, "Arcanium Chestplate");
        addItem(AEItems.ARCANIUM_LEGGINGS, "Arcanium Leggings");
        addItem(AEItems.ARCANIUM_BOOTS, "Arcanium Boots");
        add(
                "armorMaterial." + Constants.MODID + ".arcanium.desc",
                "When the item breaks, its enchantments are dropped as enchanted books.");

        // ブロック
        addBlock(AEBlocks.ARCANIUM_BLOCK, "Arcanium Block");
        addBlock(AEBlocks.ENCHANTERS_WORKBENCH, "Enchanter's Workbench");
        add("container." + Constants.MODID + ".enchanters_workbench", "Switch Enchantments");
        add("gui." + Constants.MODID + ".enchanters_workbench.locked_curse", "Curse enchantments can't be toggled!");
        add("gui." + Constants.MODID + ".enchanters_workbench.locked_conflict", "Conflicts with another enchantment!");
        add("container.enchanters_workbench.cost", "Switching Cost: %1$s");

        // エンチャント
        addEnchantmentWithDesc(AEEnchantments.NULLIFICATION, "Nullification", "Makes attacks deal true damage.");
        addEnchantmentWithDesc(AEEnchantments.LAST_STAND, "Last Stand", "Consumes experience points to avoid death.");
        addEnchantmentWithDesc(
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                "Essence of Enchantment",
                "Increases stats based on the total level other than this one of your enchantments.");
        addEnchantmentWithDesc(AEEnchantments.RESILIENCE, "Resilience", "Reduces the cooldown of all items.");
        addEnchantmentWithDesc(
                AEEnchantments.FEATHER_TOUCH,
                "Feather Touch",
                "Allows you to harvest any block while preserving various data.");
        addEnchantmentWithDesc(
                AEEnchantments.ENDLESS_APPETITE,
                "Endless Appetite",
                "Increases the maximum FoodLevel and SaturationLevel.");
        addEnchantmentWithDesc(
                AEEnchantments.AFFINITY,
                "Affinity",
                "Ignores all restrictions and allows any enchantment to be applied.");
        addEnchantmentWithDesc(AEEnchantments.DEXTERITY, "Dexterity", "Prevents slowdown when using items.");
        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "Overload", "Increases the level of other enchantments.");
        addEnchantmentWithDesc(AEEnchantments.EXPANSE, "Expanse", "Increases the number of Curios slots.");
        addEnchantmentWithDesc(
                AEEnchantments.REACTIVE_ARMOR,
                "Reactive Armor",
                "Allows all damage to be mitigated by armor and enchantments.");
        addEnchantmentWithDesc(
                AEEnchantments.MYSTIC_REMNANTS, "Mystic Remnants", "Adds special drops to certain mobs.");
        addEnchantmentWithDesc(
                AEEnchantments.CURSE_OF_IGNORANCE, "Curse of Ignorance", "Replaces the tooltip with cursed text.");
        addEnchantmentWithDesc(
                AEEnchantments.CURSE_OF_ENCHANTMENT,
                "Curse of Enchantment",
                "Locks the item's enchantments, prevents them from being changed.");
        addEnchantmentWithDesc(AEEnchantments.DISTORTION, "Distortion", "Deflects missed attacks to nearby enemies.");
        addEnchantmentWithDesc(
                AEEnchantments.ALMIGHTY,
                "Almighty",
                "Increases the tool's mining level and makes it effective on all blocks.");
        addEnchantmentWithDesc(
                AEEnchantments.CURSE_OF_INTANGIBILITY,
                "Curse Of Intangibility",
                "Makes the player pass through blocks.");
        addEnchantmentWithDesc(
                AEEnchantments.OVER_MENDING, "Over Mending", "Converts experience orbs into extra durability.");

        // attribute
        addAttributeWithDesc(
                AEAttributes.COOLDOWN_DURATION,
                "Cooldown Duration",
                "Multiplier applied to item use cooldown duration");
        addAttributeWithDesc(
                AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER, "Food Level Cap", "Multiplier for the Food Level Cap");

        // データコンポーネント
        add("item.disabled_enchantments", "disabled enchantment(s): %s");
        add("item.extra_durability", "Extra Durability: ");

        // クリエタブ
        add("itemGroup." + Constants.MODID + ".main", "Astral Enchant");
        add("itemGroup." + Constants.MODID + ".enchantment", "Astral Enchant - Enchantment");

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
                "Applied Damage Tags",
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
                "Whether to include bonuses from overloading applied to other enchantments in the total level. Regardless of this setting, Overload levels are always applied to the level of Essence of Enchantment itself.");
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
                "Removed Damage Tags",
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
        addConfigWithDesc("enchantment_toggling", "Enchantment Toggling", "REQUIRES A SERVER RESTART!");
    }

    @Override
    public void accept(String s, String s2) {
        this.add(s, s2);
    }
}
