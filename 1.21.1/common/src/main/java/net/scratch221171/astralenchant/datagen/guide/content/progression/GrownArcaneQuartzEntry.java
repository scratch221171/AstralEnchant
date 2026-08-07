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

        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        {1} that has fully grown, using a potion as a catalyst to completely absorb {0}.
                        Its cool touch and heavy weight speak to its rarity.
                        """,
                        """
                        　*ポーションを触媒として{0}を完全に吸収し、成長しきった{1}。*
                        　*ひんやりとした触感とずしりとした重量感が、その貴重さを物語っている。*
                        """,
                        itemLink(CommonUtil.getAEItem(ID.ARCANIUM_INGOT)),
                        itemLink(CommonUtil.getAEItem(ID.ARCANE_QUARTZ))));

        page("recipe", () -> BookSpotlightPageModel.create()
                .withItem(Items.BREWING_STAND)
                .withTitle(CommonUtil.getAEItemLangKey(id()))
                .withText(pageText(
                        """
                        Can be brewed by adding {1}, {2}, {3}, and {4} in order to {0}.
                        """,
                        """
                        {0}に順番に{1}、{2}、{3}、{4}を加えることで醸造できます。
                        """,
                        itemLink(CommonUtil.getAEItem(ID.BUDDING_ARCANIUM_INGOT)),
                        itemLink(
                                "item.minecraft.potion.effect.regeneration",
                                Items.POTION,
                                "potion_contents={potion:\"long_regeneration\"}"),
                        itemLink(
                                "item.minecraft.potion.effect.healing",
                                Items.POTION,
                                "potion_contents={potion:\"strong_healing\"}"),
                        itemLink(
                                "item.minecraft.potion.effect.fire_resistance",
                                Items.POTION,
                                "potion_contents={potion:\"long_fire_resistance\"}"),
                        itemLink(
                                "item.minecraft.potion.effect.strength",
                                Items.POTION,
                                "potion_contents={potion:\"strong_strength\"}"))));
    }
}
