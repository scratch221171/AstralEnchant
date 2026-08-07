package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.AEEntryProvider;

public class FeatherTouchEntryRedirectEntry extends AEEntryProvider {
    public FeatherTouchEntryRedirectEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        entry.withEntryToOpen(ModUtils.loc(EnchantmentCategory.CATEGORY_ID + "/" + ID.FEATHER_TOUCH));
        return entry;
    }

    @Override
    protected void generatePages() {}

    @Override
    protected String entryName() {
        return "redirect to: Feather Touch";
    }

    @Override
    protected String entryNameJa() {
        return "「フェザータッチ」へリダイレクト";
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.ENCHANTED_BOOK);
    }

    @Override
    protected String entryId() {
        return "redirect_" + ID.FEATHER_TOUCH;
    }
}
