package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class ReinforcedDeepslateEntry extends ItemEntry {

    public ReinforcedDeepslateEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return BuiltInRegistries.ITEM.getKey(Items.REINFORCED_DEEPSLATE).getPath();
    }

    @Override
    protected Item item() {
        return Items.REINFORCED_DEEPSLATE;
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        useExistingNameKey(Util.makeDescriptionId("block", ResourceLocation.withDefaultNamespace(id())));
        return entry;
    }

    @Override
    protected void generatePages() {

        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        Can be harvested with a tool that has <t>{0}<t> applied.
                        """,
                        """
                        　<t>{0}<t>を付けたツールで採取することができます。
                        """,
                        entryLink(
                                CommonUtil.getLangKey(AEEnchantments.FEATHER_TOUCH),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.FEATHER_TOUCH)));

        page("recipe", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc(id()))
                .withText(pageText("""
                        Can also be obtained by crafting it from {0}.
                        """, """
                        　{0}からクラフトして入手することもできます。
                        """, itemLink(CommonUtil.getAEItem(ID.ROTTEN_BONE)))));
    }
}
