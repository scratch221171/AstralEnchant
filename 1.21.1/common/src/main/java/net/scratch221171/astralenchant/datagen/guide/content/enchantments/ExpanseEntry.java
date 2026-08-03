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

public class ExpanseEntry extends EnchantmentEntry {

    public ExpanseEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.EXPANSE;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.EXPANSE;
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
                        　*{0}は[Curios](https://modrinth.com/mod/curios)連携要素であり、**Curiosが存在しない場合無効化されます！***   \s
                        　{0}はCuriosアイテムにのみ効果を発揮し、装着されているCurioスロットを増やします。デフォルトでは効果は重複しません。
                        """, translatedName())));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.ENDER_CHEST);
    }
}
