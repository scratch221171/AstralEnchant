package net.scratch221171.astralenchant.datagen.guide.content;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookCategoryBackgroundParallaxLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.enchantments.EssenceOfEnchantmentEntry;
import net.scratch221171.astralenchant.datagen.guide.content.enchantments.LastStandEntry;
import net.scratch221171.astralenchant.datagen.guide.content.enchantments.NullificationEntry;

public class EnchantmentsCategory extends CategoryProvider {

    public static final String CATEGORY_ID = "enchantments";

    public EnchantmentsCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[] {"_______", "_n_l_e_", "_______"};
    }

    @Override
    protected void generateEntries() {
        add(new NullificationEntry(this).generate('n'));
        add(new LastStandEntry(this).generate('l'));
        add(new EssenceOfEnchantmentEntry(this).generate('e'));
        ;
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        return category.withBackgroundParallaxLayers(
                new BookCategoryBackgroundParallaxLayer(modLoc("textures/gui/parallax/starfield/1.png"), 0.7f, -1),
                new BookCategoryBackgroundParallaxLayer(modLoc("textures/gui/parallax/starfield/2.png"), 1f, -1),
                new BookCategoryBackgroundParallaxLayer(modLoc("textures/gui/parallax/starfield/3.png"), 1.4f, -1));
    }

    @Override
    protected String categoryName() {
        return "Enchantments";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(BuiltInRegistries.ITEM.get(ModUtils.loc(ID.ENCHANTMENT_VESSEL)));
    }

    @Override
    public String categoryId() {
        return CATEGORY_ID;
    }
}
