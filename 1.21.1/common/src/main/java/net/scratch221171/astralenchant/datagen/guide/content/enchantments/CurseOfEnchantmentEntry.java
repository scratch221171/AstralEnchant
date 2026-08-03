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

public class CurseOfEnchantmentEntry extends EnchantmentEntry {

    public CurseOfEnchantmentEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.CURSE_OF_ENCHANTMENT;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.CURSE_OF_ENCHANTMENT;
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
                        　{0}はアイテムのエンチャントを変更不可にします。   \s
                        　{1}での合成、{2}での除去、{3}での切り替え、{4}の効果または他modで追加されるいかなる変更手段を用いても変更できません。
                        """,
                        translatedName(),
                        itemLink(Items.ANVIL),
                        itemLink(Items.GRINDSTONE),
                        itemLink(CommonUtil.getAEItem(ID.ENCHANTERS_WORKBENCH)),
                        entryLink(
                                translatedName(AEEnchantments.AFFINITY),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.AFFINITY))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.CHAIN);
    }
}
