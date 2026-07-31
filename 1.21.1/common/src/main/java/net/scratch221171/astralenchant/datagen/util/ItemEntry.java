package net.scratch221171.astralenchant.datagen.util;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import net.minecraft.world.item.Item;
import net.scratch221171.astralenchant.common.util.CommonUtil;

public abstract class ItemEntry extends BilingualEntryProvider {
    public ItemEntry(CategoryProvider parent) {
        super(parent);
    }

    protected abstract String id();

    protected Item item() {
        return CommonUtil.getAEItem(id());
    }

    protected String translatedName() {
        return "<t>" + CommonUtil.getAEItemLangKey(id()) + "<t>";
    }

    protected String translatedName(String id) {
        return "<t>" + CommonUtil.getAEItemLangKey(id) + "<t>";
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        useExistingNameKey(CommonUtil.getAEItemLangKey(id()));
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
        return BookIconModel.create(item());
    }

    @Override
    protected String entryId() {
        return id();
    }
}
