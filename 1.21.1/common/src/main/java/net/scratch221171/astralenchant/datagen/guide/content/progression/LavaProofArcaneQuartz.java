package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class LavaProofArcaneQuartz extends ItemEntry {

    public LavaProofArcaneQuartz(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.LAVAPROOF_ARCANE_QUARTZ;
    }

    @Override
    protected void generatePages() {

        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        A thing made by smearing {1} all over {0}. Surely this must count as heat-resistant treatment!
                        """, """
                         *{0}に{1}を塗りたくった代物。きっと耐熱処理が出来ているに違いない！*
                        """, itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ)), itemLink(Items.MAGMA_CREAM)));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
