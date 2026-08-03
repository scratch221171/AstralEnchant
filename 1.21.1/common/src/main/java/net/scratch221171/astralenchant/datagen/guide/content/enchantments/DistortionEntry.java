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

public class DistortionEntry extends EnchantmentEntry {

    public DistortionEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.DISTORTION;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.DISTORTION;
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
                        　{0}は外れた攻撃を命中させます。エンティティにあたっていない攻撃判定をプレイヤーの視線から一番近いエンティティに与え、デフォルトではレベル毎に許容角度が7.5°増加します。   \s
                        　視線の先がブロックである場合、当たり判定を持たないブロック({1}や{2}など)の場合のみ攻撃判定が発生します。
                        """, translatedName(), itemLink(Items.TALL_GRASS), itemLink(Items.COBWEB))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.FISHING_ROD);
    }
}
