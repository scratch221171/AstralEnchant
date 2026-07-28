package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class EndlessAppetiteEntry extends EnchantmentEntry {

    public EndlessAppetiteEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ENDLESS_APPETITE;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.ENDLESS_APPETITE;
    }

    @Override
    protected void generatePages() {
        page("content", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                .withText(pageText("""
                        This is English!   \s
                        New line   \s
                        """, """
                        　果てしない食欲は、満腹度および隠し満腹度の上限を引き上げます。   \s
                        　**なお、デフォルトの上限を超えた分は描写されません。**
                        """)));

        addDetails(lookups);
    }
}
