package net.scratch221171.astralenchant.datagen.guide.content;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.book.BookCategoryBackgroundParallaxLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.datagen.guide.content.progression.*;
import net.scratch221171.astralenchant.datagen.util.BilingualCategoryProvider;

public class ProgressionCategory extends BilingualCategoryProvider {

    public static final String CATEGORY_ID = "progression";

    public ProgressionCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[] {
            "____________h______", "", "__s_f_______g______", "", "__r_e___k_j_i_o_p__", "", "a_b_c_d___l_m_n___q", "",
        };
    }

    @Override
    protected void generateEntries() {
        var enchantmentShard = add(new EnchantmentShardEntry(this).generate('a'));
        var arcaneQuartz = add(new ArcaneQuartzEntry(this).generate('b').withParent(enchantmentShard));
        var arcaneQuartzTinyDust =
                add(new ArcaneQuartzTinyDustEntry(this).generate('c').withParent(arcaneQuartz));
        var arcaniumIngot = add(new ArcaniumIngotEntry(this).generate('d').withParent(arcaneQuartzTinyDust));
        var arcaneQuartzDust = add(new ArcaneQuartzDustEntry(this).generate('e').withParent(arcaneQuartzTinyDust));
        var enchantmentVessel =
                add(new EnchantmentVesselEntry(this).generate('f').withParent(arcaneQuartzDust));
        var enchantmentCategoryRedirect =
                add(new EnchantmentCategoryRedirectEntry(this).generate('g').withParent(enchantmentVessel));
        var featherTouchEntryRedirect =
                add(new FeatherTouchEntryRedirectEntry(this).generate('h').withParent(enchantmentCategoryRedirect));
        var mysticRemnantsEntryRedirect =
                add(new MysticRemnantsEntryRedirectEntry(this).generate('i').withParent(enchantmentCategoryRedirect));
        var allayWing = add(new AllayWingEntry(this).generate('j').withParent(mysticRemnantsEntryRedirect));
        var buddingAmethyst = add(new BuddingAmethystEntry(this)
                .generate('k')
                .withParent(featherTouchEntryRedirect)
                .withParent(allayWing));
        var buddingArcaniumIngot = add(new BuddingArcaniumIngotEntry(this)
                .generate('l')
                .withParent(arcaniumIngot)
                .withParent(parent(buddingAmethyst).withLineReversed(true)));
        var grownArcaneQuartz =
                add(new GrownArcaneQuartzEntry(this).generate('m').withParent(buddingArcaniumIngot));
        var resonantArcaneQuartz =
                add(new ResonantArcaneQuartzEntry(this).generate('n').withParent(grownArcaneQuartz));
        var rottenBone = add(new RottenBoneEntry(this).generate('o').withParent(mysticRemnantsEntryRedirect));
        var reinforcedDeepslate = add(new ReinforcedDeepslateEntry(this)
                .generate('p')
                .withParent(rottenBone)
                .withParent(featherTouchEntryRedirect));
        var resonantVessel = add(new ResonantVesselEntry(this)
                .generate('q')
                .withParent(resonantArcaneQuartz)
                .withParent(parent(reinforcedDeepslate).withLineReversed(true)));
        enchantmentCategoryRedirect.addParent(parent(resonantVessel).withLineReversed(true));
        var lavaProofArcaneQuartz =
                add(new LavaProofArcaneQuartz(this).generate('r').withParent(arcaneQuartz));
        var luminite = add(new LuminiteEntry(this).generate('s').withParent(lavaProofArcaneQuartz));
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
