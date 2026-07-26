package net.scratch221171.astralenchant.datagen.util;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
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
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.EnchantmentLookups;

public abstract class EnchantmentEntry extends BilingualEntryProvider {

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

    protected void addRecipePage(Enchantment.EnchantmentDefinition definition) {
        page("crafting", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc("enchanted_book_" + id()))
                .withText(pageText(
                        "Desc in English",
                        """
                        最大レベル：{0}
                        \\
                        金床コスト：{1}
                        \\
                        対応アイテム：{2}
                        \\
                        スロット：{3}
                        """,
                        definition.maxLevel(),
                        definition.anvilCost(),
                        definition
                                .supportedItems()
                                .unwrapKey()
                                .map(TagKey::location)
                                .map(ResourceLocation::toString)
                                .orElse("任意のアイテム"),
                        String.join(
                                ", ",
                                definition.slots().stream()
                                        .map(EquipmentSlotGroup::getSerializedName)
                                        .toList()))));
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        this.useExistingNameKey(CommonUtil.getLangKey(key()));
        return entry;
    }

    @Override
    protected String entryName() {
        return "";
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
