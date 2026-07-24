package net.scratch221171.astralenchant.datagen.guide;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import java.util.Map;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.FeaturesCategory;

public class EnchantersGuideBook extends SingleBookSubProvider {

    public EnchantersGuideBook(ModonomiconLanguageProvider enUs, ModonomiconLanguageProvider jaJp) {
        super(ID.ENCHANTERS_GUIDE, Constants.MODID, enUs, Map.of("ja_jp", jaJp));
    }

    @Override
    protected BookModel additionalSetup(BookModel book) {
        add(lang("ja_jp"), context().bookName(), "エンチャンターの手引き");
        add(lang("ja_jp"), context().bookTooltip(), "一年生課程必修の一冊！");

        return book.withGenerateBookItem(false)
                .withCustomBookItem(ModUtils.loc(ID.ENCHANTERS_GUIDE))
                .withBookTextOffsetX(5)
                .withBookTextOffsetY(0) // no top offset
                .withBookTextOffsetWidth(-5)
                .withAllowOpenBooksWithInvalidLinks(true);
    }

    @Override
    protected String bookName() {
        return "Enchanter's Guide";
    }

    @Override
    protected String bookTooltip() {
        return "A must-read for first-year students!";
    }

    @Override
    protected void registerDefaultMacros() {}

    @Override
    protected void generateCategories() {
        var featuresCategory = this.add(new FeaturesCategory(this).generate());
    }
}
