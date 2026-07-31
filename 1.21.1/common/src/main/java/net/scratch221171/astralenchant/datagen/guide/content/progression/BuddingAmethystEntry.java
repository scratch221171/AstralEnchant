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
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class BuddingAmethystEntry extends ItemEntry {

    public BuddingAmethystEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return BuiltInRegistries.ITEM.getKey(Items.BUDDING_AMETHYST).getPath();
    }

    @Override
    protected Item item() {
        return Items.BUDDING_AMETHYST;
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        useExistingNameKey(Util.makeDescriptionId("block", ResourceLocation.withDefaultNamespace(id())));
        return entry;
    }

    @Override
    protected void generatePages() {
        // TODO: English Translation
        page("desc", BookSpotlightPageModel::create).withItem(item()).withText(pageText("""
                        """, """
                        temp
                        """));

        page("recipe", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ModUtils.loc(id()))
                .withText(pageText("""
                """, """
                """)));
    }
}
