package net.scratch221171.astralenchant.common.registry;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;

public class AEItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Constants.MODID);

    public static final DeferredItem<Item> ENCHANTMENT_SHARD = REGISTER.registerSimpleItem(
            "enchantment_shard",
            new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final DeferredItem<Item> ARCANE_QUARTZ = REGISTER.registerSimpleItem("arcane_quartz");

    public static final DeferredItem<Item> GROWN_ARCANE_QUARTZ = REGISTER.registerSimpleItem("grown_arcane_quartz");

    public static final DeferredItem<Item> ARCANE_QUARTZ_DUST = REGISTER.registerSimpleItem("arcane_quartz_dust");

    public static final DeferredItem<Item> ARCANE_QUARTZ_TINY_DUST =
            REGISTER.registerSimpleItem("arcane_quartz_tiny_dust");

    public static final DeferredItem<Item> LAVAPROOF_ARCANE_QUARTZ =
            REGISTER.registerSimpleItem("lavaproof_arcane_quartz", new Item.Properties().fireResistant());

    public static final DeferredItem<Item> LUMINITE =
            REGISTER.registerSimpleItem("luminite", new Item.Properties().fireResistant());

    public static final DeferredItem<Item> ARCANIUM_INGOT = REGISTER.registerSimpleItem("arcanium_ingot");

    public static final DeferredItem<Item> BUDDING_ARCANIUM_INGOT =
            REGISTER.registerSimpleItem("budding_arcanium_ingot");

    public static final DeferredItem<Item> ENCHANTMENT_VESSEL = REGISTER.registerSimpleItem("enchantment_vessel");

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
