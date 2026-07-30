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

public class FeatherTouchEntry extends EnchantmentEntry {

    public FeatherTouchEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.FEATHER_TOUCH;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.FEATHER_TOUCH;
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
                        　シルクタッチの完全上位互換である{0}を付けることで、{1}などはもちろん、{2}や{3}も回収できるようになります。
                        \\
                        　対象が**ブロックエンティティ**(チェストやかまどなど)の場合、コンポーネントを保存したままアイテム化することが出来ます。この時[#](fc842d)オレンジ色のパーティクル[#]()が発生します。
                        \\
                        　また、スニーク時は**ブロックの状態**(ブロックの向きや水没状態など)なども保存します。この時[#](17adad)青色のパーティクル[#]()が発生します。
                        """,
                        translatedName(),
                        itemLink(Items.GLASS),
                        itemLink(Items.BUDDING_AMETHYST),
                        itemLink(Items.REINFORCED_DEEPSLATE))));

        addDetails(lookups);
    }
}
