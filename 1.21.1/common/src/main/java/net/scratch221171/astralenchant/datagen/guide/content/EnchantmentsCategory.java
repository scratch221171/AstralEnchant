package net.scratch221171.astralenchant.datagen.guide.content;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookCategoryBackgroundParallaxLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.enchantments.*;
import net.scratch221171.astralenchant.datagen.util.BilingualCategoryProvider;

public class EnchantmentsCategory extends BilingualCategoryProvider {

    public static final String CATEGORY_ID = "enchantments";

    public EnchantmentsCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[] {"_n_l_e_", "", "r_f_a_E", "", "_o_x_R_", "", "m"};
    }

    @Override
    protected void generateEntries() {
        add(new NullificationEntry(this).generate('n'));
        add(new LastStandEntry(this).generate('l'));
        add(new EssenceOfEnchantmentEntry(this).generate('e'));
        add(new ResilienceEntry(this).generate('r'));
        add(new FeatherTouchEntry(this).generate('f'));
        add(new AffinityEntry(this).generate('a'));
        add(new EndlessAppetiteEntry(this).generate('E'));
        add(new OverloadEntry(this).generate('o'));
        add(new ExpanseEntry(this).generate('x'));
        add(new ReactiveArmorEntry(this).generate('R'));
        add(new MysticRemnantsEntry(this).generate('m'));
    }

    @Override
    protected BookCategoryModel wrappedAdditionalSetup(BookCategoryModel category) {
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
    protected String categoryNameJa() {
        return "エンチャント";
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
