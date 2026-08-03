package net.scratch221171.astralenchant.datagen.guide.content;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookDisplayMode;
import net.minecraft.world.item.Items;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.enchantments.*;
import net.scratch221171.astralenchant.datagen.util.AECategoryProvider;

public class EnchantmentCategory extends AECategoryProvider {

    public static final String CATEGORY_ID = "enchantment";

    public EnchantmentCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[0];
    }

    @Override
    protected void generateEntries() {
        add(new NullificationEntry(this).generate());
        add(new LastStandEntry(this).generate());
        add(new EssenceOfEnchantmentEntry(this).generate());
        add(new ResilienceEntry(this).generate());
        add(new FeatherTouchEntry(this).generate());
        add(new AffinityEntry(this).generate());
        add(new EndlessAppetiteEntry(this).generate());
        add(new OverloadEntry(this).generate());
        add(new ExpanseEntry(this).generate());
        add(new ReactiveArmorEntry(this).generate());
        add(new MysticRemnantsEntry(this).generate());
        add(new CurseOfIgnoranceEntry(this).generate());
        add(new CurseOfEnchantmentEntry(this).generate());
        add(new DistortionEntry(this).generate());
        add(new AlmightyEntry(this).generate());
    }

    @Override
    protected BookCategoryModel wrappedAdditionalSetup(BookCategoryModel category) {
        category.withDisplayMode(BookDisplayMode.INDEX);
        return category.withBackgroundParallaxLayer(ModUtils.loc("textures/gui/guide/parchment/parchment.png"));
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
        return BookIconModel.create(Items.ENCHANTING_TABLE);
    }

    @Override
    public String categoryId() {
        return CATEGORY_ID;
    }
}
