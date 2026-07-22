package net.scratch221171.astralenchant.common.registry;

import static net.scratch221171.astralenchant.common.registry.AEArmorMaterials.ARCANIUM;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.item.AEItem;
import net.scratch221171.astralenchant.common.item.armor.AEArmorItem;
import org.jspecify.annotations.Nullable;

public class AEItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Constants.MODID);

    public static final DeferredItem<AEItem>
            ENCHANTMENT_SHARD =
                    registerAEItem(
                            ID.ENCHANTMENT_SHARD,
                            new AEItem.Properties()
                                    .rarity(Rarity.UNCOMMON)
                                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)),
            ARCANE_QUARTZ = registerAEItem(ID.ARCANE_QUARTZ, "item.astralenchant.arcane_quartz.desc", null),
            GROWN_ARCANE_QUARTZ = registerAEItem(ID.GROWN_ARCANE_QUARTZ),
            RESONANT_ARCANE_QUARTZ =
                    registerAEItem(ID.RESONANT_ARCANE_QUARTZ, new Item.Properties().rarity(Rarity.UNCOMMON)),
            ARCANE_QUARTZ_DUST = registerAEItem(ID.ARCANE_QUARTZ_DUST),
            ARCANE_QUARTZ_TINY_DUST =
                    registerAEItem(
                            ID.ARCANE_QUARTZ_TINY_DUST,
                            "item.astralenchant.arcane_quartz_tiny_dust.desc",
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            LAVAPROOF_ARCANE_QUARTZ =
                    registerAEItem(
                            ID.LAVAPROOF_ARCANE_QUARTZ,
                            new Item.Properties().fireResistant(),
                            "item.astralenchant.lavaproof_arcane_quartz.desc",
                            null),
            LUMINITE =
                    registerAEItem(
                            ID.LUMINITE,
                            new Item.Properties().fireResistant(),
                            "item.astralenchant.luminite.desc",
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_INGOT = registerAEItem(ID.ARCANIUM_INGOT),
            BUDDING_ARCANIUM_INGOT = registerAEItem(ID.BUDDING_ARCANIUM_INGOT),
            ENCHANTMENT_VESSEL =
                    registerAEItem(
                            ID.ENCHANTMENT_VESSEL,
                            new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)),
            RESONANT_VESSEL =
                    registerAEItem(
                            ID.RESONANT_VESSEL,
                            new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    public static final DeferredItem<AEArmorItem>
            ARCANIUM_HELMET =
                    registerAEArmorItem(
                            ID.ARCANIUM_HELMET,
                            ARCANIUM,
                            ArmorItem.Type.HELMET,
                            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15)),
                            "item.astralenchant.arcanium_helmet.desc",
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_CHESTPLATE =
                    registerAEArmorItem(
                            ID.ARCANIUM_CHESTPLATE,
                            ARCANIUM,
                            ArmorItem.Type.CHESTPLATE,
                            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(15)),
                            "item.astralenchant.arcanium_chestplate.desc",
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_LEGGINGS =
                    registerAEArmorItem(
                            ID.ARCANIUM_LEGGINGS,
                            ARCANIUM,
                            ArmorItem.Type.LEGGINGS,
                            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15)),
                            "item.astralenchant.arcanium_leggings.desc",
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_BOOTS =
                    registerAEArmorItem(
                            ID.ARCANIUM_BOOTS,
                            ARCANIUM,
                            ArmorItem.Type.BOOTS,
                            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15)),
                            "item.astralenchant.arcanium_boots.desc",
                            Style.EMPTY.withColor(ChatFormatting.GRAY));

    private static DeferredItem<AEItem> registerAEItem(String name) {
        return registerAEItem(name, new Item.Properties());
    }

    private static DeferredItem<AEItem> registerAEItem(String name, @Nullable String descKey, @Nullable Style style) {
        return registerAEItem(name, new Item.Properties(), descKey, style);
    }

    private static DeferredItem<AEItem> registerAEItem(String name, Item.Properties properties) {
        return REGISTER.registerItem(name, AEItem::new, properties);
    }

    private static DeferredItem<AEItem> registerAEItem(
            String name, Item.Properties properties, @Nullable String descKey, @Nullable Style style) {
        return REGISTER.registerItem(name, p -> new AEItem(p, descKey, style), properties);
    }

    private static DeferredItem<AEArmorItem> registerAEArmorItem(
            String name,
            Holder<ArmorMaterial> material,
            ArmorItem.Type type,
            Item.Properties properties,
            @Nullable String descriptionKey,
            @Nullable Style style) {
        return REGISTER.registerItem(name, p -> new AEArmorItem(material, type, p, descriptionKey, style), properties);
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
