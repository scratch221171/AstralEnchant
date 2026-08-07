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

        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        {1}, quietly continuing to grow while feeding on {0} as nourishment. Its outward appearance shows little change, but inside, alloy and crystal are steadily merging and growing.
                        """,
                        """
                        　*{0}を養分としながら静かに成長を続ける{1}。外見に大きな変化はないものの、その内部では合金と結晶が溶け合い、着実に成長してゆく。*
                        """,
                        itemLink(CommonUtil.getAEItem(ID.ARCANIUM_INGOT)),
                        itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ))));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
