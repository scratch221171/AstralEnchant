package net.scratch221171.astralenchant.common.registry;

import static net.scratch221171.astralenchant.common.registry.AEArmorMaterials.ARCANIUM;

import com.klikli_dev.modonomicon.item.ModonomiconItem;
import com.klikli_dev.modonomicon.registry.DataComponentRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.item.AEArmorItem;
import net.scratch221171.astralenchant.common.item.AEItem;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import org.jspecify.annotations.Nullable;

public class AEItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Constants.MODID);

    public static final DeferredItem<ModonomiconItem> ENCHANTERS_GUIDE = REGISTER.register(
            ID.ENCHANTERS_GUIDE,
            () -> new ModonomiconItem(new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponentRegistry.BOOK_ID.get(), ModUtils.loc(ID.ENCHANTERS_GUIDE))));

    public static final DeferredItem<AEItem>
            ENCHANTMENT_SHARD =
                    registerAEItem(
                            ID.ENCHANTMENT_SHARD,
                            new AEItem.Properties()
                                    .rarity(Rarity.UNCOMMON)
                                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)),
            ARCANE_QUARTZ = registerAEItem(ID.ARCANE_QUARTZ, Desc.defaultKey(), null),
            GROWN_ARCANE_QUARTZ = registerAEItem(ID.GROWN_ARCANE_QUARTZ, Desc.defaultKey(), null),
            RESONANT_ARCANE_QUARTZ =
                    registerAEItem(
                            ID.RESONANT_ARCANE_QUARTZ,
                            new Item.Properties().rarity(Rarity.UNCOMMON),
                            Desc.defaultKey(),
                            null),
            ARCANE_QUARTZ_DUST = registerAEItem(ID.ARCANE_QUARTZ_DUST),
            ARCANE_QUARTZ_TINY_DUST = registerAEItem(ID.ARCANE_QUARTZ_TINY_DUST),
            LAVAPROOF_ARCANE_QUARTZ =
                    registerAEItem(
                            ID.LAVAPROOF_ARCANE_QUARTZ, new Item.Properties().fireResistant(), Desc.defaultKey(), null),
            LUMINITE = registerAEItem(ID.LUMINITE, new Item.Properties().fireResistant(), Desc.defaultKey(), null),
            ARCANIUM_INGOT = registerAEItem(ID.ARCANIUM_INGOT),
            BUDDING_ARCANIUM_INGOT = registerAEItem(ID.BUDDING_ARCANIUM_INGOT),
            ENCHANTMENT_VESSEL =
                    registerAEItem(
                            ID.ENCHANTMENT_VESSEL,
                            new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)),
            RESONANT_VESSEL =
                    registerAEItem(
                            ID.RESONANT_VESSEL,
                            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)),
            ROTTEN_BONE = registerAEItem(ID.ROTTEN_BONE),
            ALLAY_WING = registerAEItem(ID.ALLAY_WING, Desc.defaultKey(), null);
    public static final DeferredItem<AEArmorItem>
            ARCANIUM_HELMET =
                    registerAEArmorItem(
                            ID.ARCANIUM_HELMET,
                            ARCANIUM,
                            ArmorItem.Type.HELMET,
                            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15)),
                            Desc.key("armorMaterial.astralenchant.arcanium.desc"),
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_CHESTPLATE =
                    registerAEArmorItem(
                            ID.ARCANIUM_CHESTPLATE,
                            ARCANIUM,
                            ArmorItem.Type.CHESTPLATE,
                            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(15)),
                            Desc.key("armorMaterial.astralenchant.arcanium.desc"),
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_LEGGINGS =
                    registerAEArmorItem(
                            ID.ARCANIUM_LEGGINGS,
                            ARCANIUM,
                            ArmorItem.Type.LEGGINGS,
                            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15)),
                            Desc.key("armorMaterial.astralenchant.arcanium.desc"),
                            Style.EMPTY.withColor(ChatFormatting.GRAY)),
            ARCANIUM_BOOTS =
                    registerAEArmorItem(
                            ID.ARCANIUM_BOOTS,
                            ARCANIUM,
                            ArmorItem.Type.BOOTS,
                            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15)),
                            Desc.key("armorMaterial.astralenchant.arcanium.desc"),
                            Style.EMPTY.withColor(ChatFormatting.GRAY));

    private static DeferredItem<AEItem> registerAEItem(String name) {
        return registerAEItem(name, new Item.Properties());
    }

    private static DeferredItem<AEItem> registerAEItem(String name, Desc key, @Nullable Style style) {
        return registerAEItem(name, new Item.Properties(), key, style);
    }

    private static DeferredItem<AEItem> registerAEItem(String name, Item.Properties properties) {
        return registerAEItem(name, properties, Desc.none(), null);
    }

    private static DeferredItem<AEItem> registerAEItem(
            String name, Item.Properties properties, Desc desc, @Nullable Style style) {
        return switch (desc.mode) {
            case NONE -> REGISTER.registerItem(name, AEItem::new, properties);
            case DEFAULT ->
                REGISTER.registerItem(
                        name, p -> new AEItem(p, CommonUtil.getAEItemLangKey(name) + ".desc", style), properties);
            case CUSTOM -> REGISTER.registerItem(name, p -> new AEItem(p, desc.key, style), properties);
        };
    }

    private static DeferredItem<AEArmorItem> registerAEArmorItem(
            String name,
            Holder<ArmorMaterial> material,
            ArmorItem.Type type,
            Item.Properties properties,
            Desc desc,
            @Nullable Style style) {
        return switch (desc.mode) {
            case NONE -> REGISTER.registerItem(name, p -> new AEArmorItem(material, type, p), properties);
            case DEFAULT ->
                REGISTER.registerItem(
                        name,
                        p -> new AEArmorItem(material, type, p, CommonUtil.getAEItemLangKey(name) + ".desc", style),
                        properties);
            case CUSTOM ->
                REGISTER.registerItem(name, p -> new AEArmorItem(material, type, p, desc.key, style), properties);
        };
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }

    // DescKeyを指定するためのもの
    private record Desc(Mode mode, @Nullable String key) {
        enum Mode {
            DEFAULT,
            NONE,
            CUSTOM
        }

        static Desc key(String key) {
            return new Desc(Mode.CUSTOM, key);
        }

        static Desc defaultKey() {
            return new Desc(Mode.DEFAULT, null);
        }

        static Desc none() {
            return new Desc(Mode.NONE, null);
        }
    }
}
