package net.scratch221171.astralenchant.datagen.util;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;

public abstract class AECategoryProvider extends CategoryProvider {
    protected static final String JA_JP = "ja_jp";

    public AECategoryProvider(ModonomiconProviderBase parent) {
        super(parent);
    }

    protected void add(String key, String enUs, String jaJp) {
        add(key, enUs);
        add(lang(JA_JP), key, jaJp);
    }

    protected String categoryNameJa() {
        return "";
    }

    protected String categoryDescriptionJa() {
        return "";
    }

    @Override
    protected final BookCategoryModel additionalSetup(BookCategoryModel category) {
        add(lang(JA_JP), context().categoryName(), categoryNameJa());
        var descJa = categoryDescriptionJa();
        if (!categoryDescription().isEmpty() && !descJa.isEmpty()) {
            add(lang(JA_JP), context().categoryDescription(), descJa);
        }
        return wrappedAdditionalSetup(category);
    }

    protected BookCategoryModel wrappedAdditionalSetup(BookCategoryModel category) {
        return category;
    }
}
