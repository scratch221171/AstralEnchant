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

public class DexterityEntry extends EnchantmentEntry {

    public DexterityEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.DEXTERITY;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.DEXTERITY;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText("""
                        {0} prevents movement speed from being reduced while using an item, and even lets you start sprinting while still using one. For example, all slowdown caused by:
                        - Eating
                        - Drawing back a {1}
                        - Using a {2}

                        is removed entirely.
                        """, """
                        　{0}はアイテム使用中の歩行速度低下を防ぎ、さらに使用中でも走り始められるようにします。例えば、
                        - 食事
                        - {1}の引き絞り
                        - {2}の使用

                        などによる速度低下はすべてなくなります。
                        """, translatedName(), itemLink(Items.BOW), itemLink(Items.SHIELD))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.SUGAR);
    }
}
