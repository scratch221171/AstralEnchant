package net.scratch221171.astralenchant.datagen.util;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;

public abstract class BilingualCategoryProvider extends CategoryProvider {
    protected static final String JA_JP = "ja_jp";

    public BilingualCategoryProvider(ModonomiconProviderBase parent) {
        super(parent);
    }

    protected void add(String key, String enUs, String jaJp) {
        this.add(key, enUs);
        this.add(this.lang(JA_JP), key, jaJp);
    }

    protected String categoryNameJa() {
        return "";
    }

    protected String categoryDescriptionJa() {
        return "";
    }

    @Override
    protected final BookCategoryModel additionalSetup(BookCategoryModel category) {
        this.add(this.lang(JA_JP), this.context().categoryName(), this.categoryNameJa());
        var descJa = this.categoryDescriptionJa();
        if (!this.categoryDescription().isEmpty() && !descJa.isEmpty()) {
            this.add(this.lang(JA_JP), this.context().categoryDescription(), descJa);
        }
        return this.wrappedAdditionalSetup(category);
    }

    protected BookCategoryModel wrappedAdditionalSetup(BookCategoryModel category) {
        return category;
    }
}
