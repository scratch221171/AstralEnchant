package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookEntityPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class RottenBoneEntry extends ItemEntry {

    public RottenBoneEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ROTTEN_BONE;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create).withItem(item()).withText(pageText("""
                """, """
                　*朽ち果ててなお異様な強度を保ち続ける骨片。古代文明では深層岩の補強技術に組み込まれ、貴重な素材として重宝された。*
                """));

        page("entity", () -> BookEntityPageModel.create()
                .withText(pageText(
                        """
                """,
                        """
                <t>{0}<t>を付けた武器で<t>{1}<t>を倒すと稀にドロップします。
                """,
                        entryLink(
                                CommonUtil.getLangKey(AEEnchantments.MYSTIC_REMNANTS),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.MYSTIC_REMNANTS),
                        EntityType.BOGGED.getDescriptionId()))
                .withEntityId(
                        BuiltInRegistries.ENTITY_TYPE.getKey(EntityType.BOGGED).toString()));
    }
}
