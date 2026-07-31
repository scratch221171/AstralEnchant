package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import java.awt.*;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.BilingualEntryProvider;

public class EnchantmentCategoryRedirectEntry extends BilingualEntryProvider {
    public EnchantmentCategoryRedirectEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        entry.withCategoryToOpen(ModUtils.loc(EnchantmentCategory.CATEGORY_ID));
        return entry;
    }

    @Override
    protected void generatePages() {}

    @Override
    protected String entryName() {
        return "redirect to: Enchantments";
    }

    @Override
    protected String entryNameJa() {
        return "「エンチャント」へリダイレクト";
    }

    @Override
    protected String entryDescription() {
        return "";
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.ENCHANTING_TABLE);
    }

    @Override
    protected String entryId() {
        return "redirect_" + EnchantmentCategory.CATEGORY_ID;
    }
}
