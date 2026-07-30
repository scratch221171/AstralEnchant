package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class NullificationEntry extends EnchantmentEntry {

    public NullificationEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.NULLIFICATION;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.NULLIFICATION;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                .withText(pageText("""
                        This is English!   \s
                        New line   \s
                        """, """
                        　{0}を付けることによって、攻撃は以下の様々なダメージ軽減＆無効を貫通します。
                        - 防御力、防具強度
                        - 無敵フレーム
                        - エフェクト
                        - エンチャント
                        - 無敵状態
                        - 盾

                        　{0}が付いた武器で攻撃すると、白いパーティクルが発生します。
                        """, translatedName())));

        addDetails(lookups);
    }
}
