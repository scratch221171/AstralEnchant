package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class ResilienceEntry extends EnchantmentEntry {

    public ResilienceEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.RESILIENCE;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.RESILIENCE;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                .withText(pageText(
                        """
                        This is English!   \s
                        New line   \s
                        """,
                        """
                        　{0}を付けていると、全てのアイテムのクールダウン時間が短縮されます。
                        \\
                        　バニラで恩恵を受けるのは{1}、{2}、{3}、{4}だけですが、modアイテムにも効果があります。
                        """,
                        translatedName(),
                        itemLink(Items.ENDER_PEARL),
                        itemLink(Items.CHORUS_FRUIT),
                        itemLink(Items.WIND_CHARGE),
                        itemLink(Items.SHIELD))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.CLOCK);
    }
}
