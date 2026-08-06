package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class ArcaneQuartzDustEntry extends ItemEntry {

    public ArcaneQuartzDustEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ARCANE_QUARTZ_DUST;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText("""
                        """, """
                        　*{0}を集め、精製して作られた高純度の粉末。*   \s
                        　*結晶としての形は失われたが、その膨大な表面積によって反応性は結晶以上に優れる。*
                        """, itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ_TINY_DUST))));

        page("recipe", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc(id()))
                .withText(pageText(
                        """
                """, """
                　**{0}で{1}を粉砕しても得られます。**
                """, itemLink(Items.ANVIL), itemLink(CommonUtil.getAEItem(ID.GROWN_ARCANE_QUARTZ)))));
    }
}
