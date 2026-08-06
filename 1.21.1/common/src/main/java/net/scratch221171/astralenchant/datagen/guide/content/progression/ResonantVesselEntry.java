package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class ResonantVesselEntry extends ItemEntry {

    public ResonantVesselEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.RESONANT_VESSEL;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create).withItem(item()).withText(pageText("""
                        """, """
                        　*スカルクの力を用いて、エンチャントとの共鳴を極限まで高めた書物。*   \s
                        　*より高位で繊細なエンチャントを引き寄せるには必須不可欠だろう。*
                        """));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
