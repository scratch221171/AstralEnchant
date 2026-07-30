package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class EssenceOfEnchantmentEntry extends EnchantmentEntry {

    public EssenceOfEnchantmentEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ESSENCE_OF_ENCHANTMENT;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.ESSENCE_OF_ENCHANTMENT;
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
                        　{0}は、アイテムの**自身を除く**全てのエンチャントの合計レベル(デフォルトでは{1}による増加レベルを除く)に応じて**全てのステータス**を増加させます。   \s
                        　デフォルトでは1レベルにつき1%%、**基本値乗算加算**(ADD_MULTIPLIED_BASE)で増加します。   \s
                        　大きさ、重力など中立的な属性は変化せず、炎上時間など有害な属性は減少します。   \s
                        　また、curiosアイテムでも動作します。
                        """,
                        translatedName(),
                        entryLink(
                                translatedName(AEEnchantments.OVERLOAD),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.OVERLOAD))));

        addDetails(lookups);
    }
}
