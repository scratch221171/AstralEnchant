package net.scratch221171.astralenchant.datagen.guide.content;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookCategoryBackgroundParallaxLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.progression.ArcaneQuartzEntry;
import net.scratch221171.astralenchant.datagen.guide.content.progression.EnchantmentShardEntry;
import net.scratch221171.astralenchant.datagen.util.BilingualCategoryProvider;

public class ProgressionCategory extends BilingualCategoryProvider {

    public static final String CATEGORY_ID = "progression";

    public ProgressionCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[] {"a_b"};
    }

    @Override
    protected void generateEntries() {
        var enchantmentShard = add(new EnchantmentShardEntry(this).generate('a'));
        add(new ArcaneQuartzEntry(this).generate('b').withParent(enchantmentShard));
    }

    @Override
    protected BookCategoryModel wrappedAdditionalSetup(BookCategoryModel category) {
        return category.withBackgroundParallaxLayers(
                new BookCategoryBackgroundParallaxLayer(modLoc("textures/gui/guide/starfield/1.png"), 0.7f, -1),
                new BookCategoryBackgroundParallaxLayer(modLoc("textures/gui/guide/starfield/2.png"), 1f, -1),
                new BookCategoryBackgroundParallaxLayer(modLoc("textures/gui/guide/starfield/3.png"), 1.4f, -1));
    }

    @Override
    protected String categoryName() {
        return "Progression";
    }

    @Override
    protected String categoryNameJa() {
        return "進行";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(BuiltInRegistries.ITEM.get(ModUtils.loc(ID.GROWN_ARCANE_QUARTZ)));
    }

    @Override
    public String categoryId() {
        return CATEGORY_ID;
    }
}
