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
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText("""
                        {0} raises the maximum Food Level and hidden Saturation Level.   \s
                        **Note that by default, the food bar will be displayed as a numerical value. For more details, please refer to the client settings.**
                        """, """
                        　{0}は、満腹度および隠し満腹度の上限を引き上げます。   \s
                        　**なお、デフォルトでは満腹度バーの描画が数値ベースに変更されます。詳しくはクライアントの設定を参照してください。**
                        """, translatedName())));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.GOLDEN_CARROT);
    }
}
