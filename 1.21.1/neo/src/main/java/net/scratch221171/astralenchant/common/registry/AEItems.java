package net.scratch221171.astralenchant.common.registry;

import static net.scratch221171.astralenchant.common.registry.AEArmorMaterials.ARCANIUM;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
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

public class AEItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Constants.MODID);

    public static final DeferredItem<AEItem> ENCHANTMENT_SHARD = registerAEItem(
            ID.ENCHANTMENT_SHARD,
            new AEItem.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final DeferredItem<AEItem> ARCANE_QUARTZ =
            registerAEItem(ID.ARCANE_QUARTZ, "item.astralenchant.arcane_quartz.desc");

    public static final DeferredItem<AEItem> GROWN_ARCANE_QUARTZ =
            registerAEItem(ID.GROWN_ARCANE_QUARTZ, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static final DeferredItem<AEItem> ARCANE_QUARTZ_DUST = registerAEItem(ID.ARCANE_QUARTZ_DUST);

    public static final DeferredItem<AEItem> ARCANE_QUARTZ_TINY_DUST = registerAEItem(ID.ARCANE_QUARTZ_TINY_DUST);

    public static final DeferredItem<AEItem> LAVAPROOF_ARCANE_QUARTZ = registerAEItem(
            ID.LAVAPROOF_ARCANE_QUARTZ,
            new Item.Properties().fireResistant(),
            "item.astralenchant.lavaproof_arcane_quartz.desc");

    public static final DeferredItem<AEItem> LUMINITE =
            registerAEItem(ID.LUMINITE, new Item.Properties().fireResistant());

    public static final DeferredItem<AEItem> ARCANIUM_INGOT = registerAEItem(ID.ARCANIUM_INGOT);

    public static final DeferredItem<AEItem> BUDDING_ARCANIUM_INGOT = registerAEItem(ID.BUDDING_ARCANIUM_INGOT);

    public static final DeferredItem<AEItem> ENCHANTMENT_VESSEL =
            registerAEItem(ID.ENCHANTMENT_VESSEL, new Item.Properties().stacksTo(1));

    public static final DeferredItem<AEArmorItem> ARCANIUM_HELMET = registerAEArmorItem(
            ID.ARCANIUM_HELMET,
            ARCANIUM,
            ArmorItem.Type.HELMET,
            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15)),
            "item.astralenchant.arcanium_helmet.desc");

    public static final DeferredItem<AEArmorItem> ARCANIUM_CHESTPLATE = registerAEArmorItem(
            ID.ARCANIUM_CHESTPLATE,
            ARCANIUM,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(15)),
            "item.astralenchant.arcanium_chestplate.desc");

    public static final DeferredItem<AEArmorItem> ARCANIUM_LEGGINGS = registerAEArmorItem(
            ID.ARCANIUM_LEGGINGS,
            ARCANIUM,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15)),
            "item.astralenchant.arcanium_leggings.desc");

    public static final DeferredItem<AEArmorItem> ARCANIUM_BOOTS = registerAEArmorItem(
            ID.ARCANIUM_BOOTS,
            ARCANIUM,
            ArmorItem.Type.BOOTS,
            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15)),
            "item.astralenchant.arcanium_boots.desc");

    private static DeferredItem<AEItem> registerAEItem(String name) {
        return registerAEItem(name, new Item.Properties());
    }

    private static DeferredItem<AEItem> registerAEItem(String name, String descKey) {
        return registerAEItem(name, new Item.Properties(), descKey);
    }

    private static DeferredItem<AEItem> registerAEItem(String name, Item.Properties properties) {
        return REGISTER.registerItem(name, AEItem::new, properties);
    }

    private static DeferredItem<AEItem> registerAEItem(String name, Item.Properties properties, String descKey) {
        return REGISTER.registerItem(name, p -> new AEItem(p, descKey), properties);
    }

    private static DeferredItem<AEArmorItem> registerAEArmorItem(
            String name,
            Holder<ArmorMaterial> material,
            ArmorItem.Type type,
            Item.Properties properties,
            String descriptionKey) {
        return REGISTER.registerItem(name, p -> new AEArmorItem(material, type, p, descriptionKey), properties);
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
