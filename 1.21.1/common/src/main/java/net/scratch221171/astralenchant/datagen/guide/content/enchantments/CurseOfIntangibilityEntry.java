package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class CurseOfIntangibilityEntry extends EnchantmentEntry {

    public CurseOfIntangibilityEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.CURSE_OF_INTANGIBILITY;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.CURSE_OF_INTANGIBILITY;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText("""
                        {0} completely removes collision between entities and blocks. As a result, **mobs and players without the ability to fly will fall into the void when wearing it.**   \s
                        """, """
                        　{0}はエンティティとブロックの衝突を完全になくします。そのため、**飛行能力を持たないモブやプレイヤーは装着すると奈落へ落ちます。**   \s
                        """, translatedName())));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(CommonUtil.getAEItem(ID.VEX_WING));
    }
}
