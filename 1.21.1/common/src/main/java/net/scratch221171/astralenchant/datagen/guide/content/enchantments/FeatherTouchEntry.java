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
                .withText(pageText(
                        """
                        {0} is a complete upgrade over Silk Touch — in addition to blocks like {1}, you can now also collect blocks like {2} and {3}.   \s
                        If the target is a **block entity** (such as a chest or furnace), it can be turned into an item while preserving its contents. This produces [#](fc842d)orange particles[#]().   \s
                        Additionally, while sneaking, it also preserves the **block state** (such as orientation or whether the block is waterlogged). This produces [#](17adad)blue particles[#]().
                        """,
                        """
                        　シルクタッチの完全上位互換である{0}を付けることで、{1}などはもちろん、{2}や{3}も回収できるようになります。   \s
                        　対象が**ブロックエンティティ**(チェストやかまどなど)の場合、コンポーネントを保存したままアイテム化することが出来ます。この時[#](fc842d)オレンジ色のパーティクル[#]()が発生します。   \s
                        　また、スニーク時は**ブロックの状態**(ブロックの向きや水没状態など)なども保存します。この時[#](17adad)青色のパーティクル[#]()が発生します。
                        """,
                        translatedName(),
                        itemLink(Items.GLASS),
                        itemLink(Items.BUDDING_AMETHYST),
                        itemLink(Items.REINFORCED_DEEPSLATE))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.ECHO_SHARD);
    }
}
