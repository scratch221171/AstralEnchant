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

public class ReactiveArmorEntry extends EnchantmentEntry {

    public ReactiveArmorEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.REACTIVE_ARMOR;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.REACTIVE_ARMOR;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText(
                        """
                        While wearing armor enchanted with {0}, the **armor-piercing effects** (such as void damage) and **enchantment-piercing effects** (such as the sonic boom of the Warden) of incoming attacks are negated, allowing that damage to be mitigated by armor.   \s
                        Note that the piercing effect from {1} cannot be negated.
                        """,
                        """
                        　{0}を付けた防具を身に着けている場合、受けた攻撃が持つ**防具貫通効果**(奈落ダメージなど)及び**エンチャント貫通効果**(ウォーデンの衝撃波など)が無効化され、それらのダメージが防具で軽減できるようになります。   \s
                        　なお、{1}による軽減貫通効果は無効化できません。
                        """,
                        translatedName(),
                        entryLink(
                                translatedName(AEEnchantments.NULLIFICATION),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.NULLIFICATION))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.TURTLE_HELMET);
    }
}
