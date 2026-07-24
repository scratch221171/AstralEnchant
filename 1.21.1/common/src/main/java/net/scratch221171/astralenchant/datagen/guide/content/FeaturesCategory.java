// SPDX-FileCopyrightText: 2024 klikli-dev
//
// SPDX-License-Identifier: MIT

package net.scratch221171.astralenchant.datagen.guide.content;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookCategoryBackgroundParallaxLayer;
import com.klikli_dev.modonomicon.datagen.book.demo.features.*;
import net.minecraft.world.item.Items;

public class FeaturesCategory extends CategoryProvider {
    public static final String ID = "features";

    public FeaturesCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        // The entry map allows to define where entries are in relation to each other.
        // It is recommended to use a single character per entry
        // (if you think you are running out of characters .. any unicode character works.)
        // Alternatively the format used for multiblock below can be used.
        return new String[] {
            "_____________________",
            "_____________________",
            "_____________________",
            "__________c__________",
            "_____________________",
            "_____________________",
            "_____________________",
            "_____________________"
        };
    }

    @Override
    protected void generateEntries() {
        var recipeEntry = this.add(new RecipeEntry(this).generate('c'));
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        return category.withBackgroundParallaxLayers(
                new BookCategoryBackgroundParallaxLayer(this.modLoc("textures/block/arcanium_block.png"), 0.7f, -1)
                //                new
                // BookCategoryBackgroundParallaxLayer(this.modLoc("textures/gui/parallax/flow/1.png"), 1f, -1),
                //                new
                // BookCategoryBackgroundParallaxLayer(this.modLoc("textures/gui/parallax/flow/2.png"), 1.4f, -1)
                );
    }

    @Override
    protected String categoryName() {
        return "Features Category";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(Items.NETHER_STAR);
    }

    @Override
    public String categoryId() {
        return ID;
    }
}
