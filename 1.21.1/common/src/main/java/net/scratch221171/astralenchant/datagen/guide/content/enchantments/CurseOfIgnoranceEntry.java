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

public class CurseOfIgnoranceEntry extends EnchantmentEntry {

    public CurseOfIgnoranceEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.CURSE_OF_IGNORANCE;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.CURSE_OF_IGNORANCE;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText("""
                        {0} makes the tooltip hard to read. If you squint, you might be able to make out a little...   \s
                        By default it also hides the name of the item, which may affect things like the item preview from [EnhancedTooltip](https://modrinth.com/mod/enhancedtooltips).
                        """, """
                        　{0}はツールチップを読みにくくします。目を凝らせばいくらかは見えるかもしれません...   \s
                        　デフォルトではアイテムの名前を隠しますが、[EnhancedTooltip](https://modrinth.com/mod/enhancedtooltips)のアイテムプレビューなどに影響するかもしれません。
                        """, translatedName())));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.INK_SAC);
    }
}
