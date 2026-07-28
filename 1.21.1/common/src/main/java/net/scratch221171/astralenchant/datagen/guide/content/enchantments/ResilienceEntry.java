package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
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
        page("content", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                .withText(pageText(
                        """
                        This is English!   \s
                        New line   \s
                        """,
                        """
                        　復調を付けていると、全てのアイテムのクールダウン時間が短縮されます。
                        \\
                        　バニラで恩恵を受けるのは{0}、{1}、{2}、{3}だけですが、modアイテムにも効果があります。
                        """,
                        itemLink(Items.ENDER_PEARL),
                        itemLink(Items.CHORUS_FRUIT),
                        itemLink(Items.WIND_CHARGE),
                        itemLink(Items.SHIELD))));

        addDetails(lookups);
    }
}
