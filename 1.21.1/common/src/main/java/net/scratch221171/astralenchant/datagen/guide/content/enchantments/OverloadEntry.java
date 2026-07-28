package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentsCategory;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class OverloadEntry extends EnchantmentEntry {

    public OverloadEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.OVERLOAD;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.OVERLOAD;
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
                        　オーバーロードは、**自身を除く**アイテムの全てのエンチャントのレベルを、**自身のレベルだけ**引き上げます。   \s
                        　デフォルトでは、加算されたレベルは{0}の合計レベルには含まれません。   \s
                        　ツールチップでは虹色に輝き、存在を主張してきます。
                        """,
                        entryLink("エンチャントの極意", EnchantmentsCategory.CATEGORY_ID, ID.ESSENCE_OF_ENCHANTMENT))));

        addDetails(lookups);
    }
}
