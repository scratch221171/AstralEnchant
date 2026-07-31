package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class BuddingArcaniumIngotEntry extends ItemEntry {

    public BuddingArcaniumIngotEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.BUDDING_ARCANIUM_INGOT;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText("""
                        """, """
                        　*{0}は養分となり、結晶は静かに成長を続ける。外見に大きな変化はないものの、その内部では魔力と合金が溶け合い、未熟でありながらも確かな生命にも似た変化が息づいている。*
                        """, itemLink(CommonUtil.getAEItem(ID.ARCANIUM_INGOT))));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
