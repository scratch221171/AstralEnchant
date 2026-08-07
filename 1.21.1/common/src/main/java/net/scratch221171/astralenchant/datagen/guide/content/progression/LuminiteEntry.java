package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class LuminiteEntry extends ItemEntry {

    public LuminiteEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.LUMINITE;
    }

    @Override
    protected void generatePages() {

        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText("""
                        A crystal with a beautiful gradient, formed by treating {0} with lava.
                        Intense heat has altered its crystal structure, and with the right handling, energy could likely be drawn out from within.
                        """, """
                        　*{0}を溶岩で処理して出来上がる、美しいグラデーションを持つ結晶。*
                        　*高熱により結晶構造が変化していて、うまく扱えば内部からエネルギーを取り出せるだろう。*
                        """, itemLink(CommonUtil.getAEItem(ID.LAVAPROOF_ARCANE_QUARTZ))));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
