package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSmeltingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class EnchantmentShardEntry extends ItemEntry {

    public EnchantmentShardEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ENCHANTMENT_SHARD;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create).withItem(item()).withText(pageText("""
                        """, """
                        　*本を焼き尽くしても、知識が消え去ることはない。紙は灰となり、文字は消え失せても、その本質たる叡智だけは炎によって磨かれ、純粋な欠片へと姿を変える。*
                        """));

        page("recipe", () -> BookSmeltingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc("enchantment_shard_smelting"))
                .withText(pageText(
                        """
                        """,
                        """
                        　任意の{0}を{1}や{2}で精錬すること作れます。
                        """,
                        itemLink(Items.ENCHANTED_BOOK),
                        itemLink(Items.FURNACE),
                        itemLink((Items.BLAST_FURNACE)))));
    }
}
