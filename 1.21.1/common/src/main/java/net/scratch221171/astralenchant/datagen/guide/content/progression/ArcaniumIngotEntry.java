package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class ArcaniumIngotEntry extends ItemEntry {

    public ArcaniumIngotEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ARCANIUM_INGOT;
    }

    @Override
    protected void generatePages() {
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        A soft, pale purple alloy made by kneading {0} into {1} and {2}.   \s
                        It does not look much different from ordinary metal, but its excellent conductivity makes it an ideal seedbed for crystal growth.
                        """,
                        """
                        　*{0}を{1}と{2}に練り込むことで作れる、淡い紫色の柔らかい合金。*   \s
                        　*見た目は一般的な金属と大きく変わらないが、伝導性の良さゆえ結晶の苗床としてはうってつけだ。*
                        """,
                        itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ_TINY_DUST)),
                        itemLink(Items.IRON_INGOT),
                        itemLink(Items.GOLD_NUGGET)));

        page("recipe", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc("arcanium_ingot_from_arcane_quartz")));
    }
}
