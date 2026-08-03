package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class GrownArcaneQuartzEntry extends ItemEntry {

    public GrownArcaneQuartzEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.GROWN_ARCANE_QUARTZ;
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
                        　*ポーションを触媒として{0}を完全に吸収し、成長しきった{1}の姿。*
                        　*ひんやりとした触感とずしりとした重量感が、その貴重さを物語っている。*
                        """,
                        itemLink(CommonUtil.getAEItem(ID.ARCANIUM_INGOT)),
                        itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ))));

        page("recipe", () -> BookSpotlightPageModel.create()
                .withItem(Items.BREWING_STAND)
                .withTitle(CommonUtil.getAEItemLangKey(id()))
                .withText(pageText(
                        """
                    """,
                        """
                    {0}に順番に{1}、{2}、{3}、{4}を加えることで醸造できます。
                    """,
                        itemLink(CommonUtil.getAEItem(ID.BUDDING_ARCANIUM_INGOT)),
                        itemLink("再生のポーション", Items.POTION, "potion_contents={potion:\"long_regeneration\"}"),
                        itemLink("治癒のポーション", Items.POTION, "potion_contents={potion:\"strong_healing\"}"),
                        itemLink("耐火のポーション", Items.POTION, "potion_contents={potion:\"long_fire_resistance\"}"),
                        itemLink("力のポーション", Items.POTION, "potion_contents={potion:\"strong_strength\"}"))));
    }
}
