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
        page("desc", () -> BookSpotlightPageModel.create().withTitle(CommonUtil.getAEItemLangKey(id())))
                .withItem(item())
                .withText(pageText(
                        """
                        """,
                        """
                        　*燃え尽きた知識の結晶である{0}に、ネザーの力を宿す{1}と、地中深くで育まれた{2}を融合させることで生まれる魔法鉱石。*   \s
                        　*淡く輝くこの素材は、古のエンチャンターたちの探究の結晶である。扱う者は、その重みと価値を決して忘れてはならない。*
                        """,
                        itemLink(CommonUtil.getAEItem(ID.ENCHANTMENT_SHARD)),
                        itemLink(Items.QUARTZ),
                        itemLink(Items.AMETHYST_SHARD)));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(ID.ARCANE_QUARTZ)));
    }
}
