package net.scratch221171.astralenchant.datagen.util;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import java.util.List;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.common.util.EnchantmentLookups;

public abstract class EnchantmentEntry extends AEEntryProvider {

    protected final EnchantmentLookups lookups = new EnchantmentLookups() {
        public HolderLookup.RegistryLookup<Item> items() {
            return registries().lookupOrThrow(Registries.ITEM);
        }

        public HolderLookup.RegistryLookup<Attribute> attributes() {
            return registries().lookupOrThrow(Registries.ATTRIBUTE);
        }

        // nullなら全て対応していることにする
        public HolderSet<Item> anyItems() {
            return HolderSet.direct();
        }
    };

    public EnchantmentEntry(CategoryProvider parent) {
        super(parent);
    }

    protected abstract String id();

    protected abstract ResourceKey<Enchantment> key();

    protected String translatedName() {
        return "<t>" + CommonUtil.getLangKey(key()) + "<t>";
    }

    protected String translatedName(ResourceKey<Enchantment> key) {
        return "<t>" + CommonUtil.getLangKey(key) + "<t>";
    }

    protected void addDetails(EnchantmentLookups lookups) {
        var enchantment = AEEnchantments.getEnchantment(key(), lookups);
        var definition = enchantment.definition();
        List<EnchantmentAttributeEffect> effects =
                enchantment.effects().getOrDefault(EnchantmentEffectComponents.ATTRIBUTES, List.of());
        if (effects.isEmpty()) {
            page("detail", () -> BookCraftingRecipePageModel.create()
                    .withRecipeId1(ModUtils.loc("enchanted_book_" + id()))
                    .withText(pageText(
                            "Desc in English",
                            """
                            [最大レベル]{0}   \s
                            [金床コスト]{1}   \s
                            [対応アイテム]{2}   \s
                            [スロット]{3}
                            """,
                            definition.maxLevel(),
                            definition.anvilCost(),
                            definition
                                    .supportedItems()
                                    .unwrapKey()
                                    .map(TagKey::location)
                                    .map(ResourceLocation::getPath)
                                    .orElse("任意のアイテム"),
                            String.join(
                                    ", ",
                                    definition.slots().stream()
                                            .map(EquipmentSlotGroup::getSerializedName)
                                            .toList()))));
        } else {
            page("detail", () -> BookCraftingRecipePageModel.create()
                    .withRecipeId1(ModUtils.loc("enchanted_book_" + id()))
                    .withText(pageText(
                            "Desc in English",
                            """
                            [最大レベル]{0}   \s
                            [金床コスト]{1}   \s
                            [対応アイテム]{2}   \s
                            [スロット]{3}   \s
                            [属性効果]{4}
                            """,
                            definition.maxLevel(),
                            definition.anvilCost(),
                            definition
                                    .supportedItems()
                                    .unwrapKey()
                                    .map(TagKey::location)
                                    .map(ResourceLocation::getPath)
                                    .orElse("任意のアイテム"),
                            String.join(
                                    ", ",
                                    definition.slots().stream()
                                            .map(EquipmentSlotGroup::getSerializedName)
                                            .toList()),
                            EnchantmentEffectTextUtil.formatAttributeEffects(effects))));
        }
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        useExistingNameKey(CommonUtil.getLangKey(key()));
        useExistingDescKey(CommonUtil.getDescLangKey(key()));
        return entry;
    }

    @Override
    protected String entryName() {
        return id().replace('_', ' ');
    }

    @Override
    protected String entryDescription() {
        return "";
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.ENCHANTED_BOOK);
    }

    @Override
    protected String entryId() {
        return id();
    }
}
