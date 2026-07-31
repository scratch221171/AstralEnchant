package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class GrownArcaneQuartzEntry extends ItemEntry {

    public GrownArcaneQuartzEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.GROWN_ARCANE_QUARTZ;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create).withItem(item()).withText(pageText("""
                        """, """
                        """));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
