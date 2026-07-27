package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
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
        page("content", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                // TODO: Overloadのリンク
                .withText(pageText("""
                        This is English!   \s
                        New line   \s
                        """, """
                        エンチャントの極意は、**自身を除く**アイテムの全てのエンチャントの合計レベル(デフォルトではオーバーロードによる増加レベルを除く)に応じて、アイテムの**全てのステータス**を増加させます。
                        \\
                        デフォルトでは、1レベルにつき1%%、**基本値乗算加算**(ADD_MULTIPLIED_BASE)で増加します。
                        \\
                        エンティティのサイズ、重力など中立的な属性は変化せず、炎上時間など有害な属性は減少します。
                        \\
                        また、curiosアイテムでも動作します。
                        """)));

        addDetails(lookups);
    }
}
