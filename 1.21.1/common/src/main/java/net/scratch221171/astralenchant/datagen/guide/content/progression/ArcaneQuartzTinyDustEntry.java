package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class ArcaneQuartzTinyDustEntry extends ItemEntry {

    public ArcaneQuartzTinyDustEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ARCANE_QUARTZ_TINY_DUST;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText("""
                        """, """
                        　*{0}を粉々に粉砕して得られる、きめ細かな紫色の粉。*   \s
                        　*一粒一粒が淡く輝きを放っている。原型を微塵も留めていないが、その叡智は決して失われてはいない。*
                        """, itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ))));

        page("recipe", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc(id()))
                .withText(pageText("""
                """, """
                　**{0}で{1}を粉砕しても得られます。**
                """, itemLink(Items.ANVIL), itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ)))));
    }
}
