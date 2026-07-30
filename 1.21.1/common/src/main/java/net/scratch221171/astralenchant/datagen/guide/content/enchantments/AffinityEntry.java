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

public class AffinityEntry extends EnchantmentEntry {

    public AffinityEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.AFFINITY;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.AFFINITY;
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
                        　{0}が付いているアイテムは、以下の制限を無視して**任意のエンチャント**を付与できるようになります。
                        - 対応の対象かどうか
                        - エンチャント可能か
                        - エンチャントの競合

                        　他modによって追加されるエンチャント合成手段には対応できない可能性があります。その場合は代わりに{1}を使用してください。
                        """, translatedName(), itemLink(Items.ANVIL))));

        addDetails(lookups);
    }
}
