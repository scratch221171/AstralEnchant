package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class ArcaneQuartzEntry extends ItemEntry {

    public ArcaneQuartzEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ARCANE_QUARTZ;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        """,
                        """
                        　*燃え尽きた知識の結晶である{0}を、まっさらな{1}と{2}に憑依させることで生まれる素材。*   \s
                        　*淡く輝くこの結晶は、古のエンチャンターたちの探究の成果である。扱う者は、その重みと価値を決して忘れてはならない。*
                        """,
                        itemLink(CommonUtil.getAEItem(ID.ENCHANTMENT_SHARD)),
                        itemLink(Items.QUARTZ),
                        itemLink(Items.AMETHYST_SHARD)));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
