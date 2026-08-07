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
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
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
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText(
                        """
                        {0} raises the level of every enchantment on the item **other than itself**, by an amount equal to **its own level**.   \s
                        By default, the levels it adds are not included in the total level of {1}.   \s
                        In the tooltip, it shines in rainbow colors, making its presence known.
                        """,
                        """
                        　{0}は、**自身を除く**アイテムの全てのエンチャントのレベルを、**自身のレベルだけ**引き上げます。   \s
                        　デフォルトでは、加算されたレベルは{1}の合計レベルには含まれません。   \s
                        　ツールチップでは虹色に輝き、存在を主張してきます。
                        """,
                        translatedName(),
                        entryLink(
                                translatedName(AEEnchantments.ESSENCE_OF_ENCHANTMENT),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.ESSENCE_OF_ENCHANTMENT))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.NETHER_STAR);
    }
}
