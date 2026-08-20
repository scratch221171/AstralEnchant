package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class OverMendingEntry extends EnchantmentEntry {

    public OverMendingEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.OVER_MENDING;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.OVER_MENDING;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText("""
                        Applying {0} converts any experience orbs you collect into extra durability. This extra durability is consumed with priority, and its cap is calculated as   \s
                        100\\*(enchantment level)^2   \s
                        If a piece of equipment has {1} applied, repair from {1} takes priority first.   \s
                        It is also compatible with [Clumps](https://modrinth.com/mod/clumps).
                        """, """
                        　{0}を付けると、取得した経験値オーブが追加の耐久値に変換されます。追加の耐久値は優先的に消費され、上限は   \s
                        100\\*(エンチャントレベル)^2   \s
                        で計算されます。   \s
                        　{1}が付いている装備が存在する場合はまず{1}による修復が優先されます。
                        　[Clumps](https://modrinth.com/mod/clumps)とも互換性があります。
                        """, translatedName(), translatedName(Enchantments.MENDING))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.EXPERIENCE_BOTTLE);
    }
}
