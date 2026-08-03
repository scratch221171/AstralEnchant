package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class EnchantmentVesselEntry extends ItemEntry {

    public EnchantmentVesselEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ENCHANTMENT_VESSEL;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText("""
                        """, """
                        　*情報を保存するという目的を切り捨てたことで、エンチャントとの相性に関しては{0}よりも優れるようになった書物。*
                        　*様々な素材を触媒として使うことで、決まった種類のエンチャントを呼び寄せることができる。*
                        """, itemLink(Items.BOOK)));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
