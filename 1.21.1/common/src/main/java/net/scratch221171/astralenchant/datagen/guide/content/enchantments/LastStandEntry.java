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

public class LastStandEntry extends EnchantmentEntry {

    public LastStandEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.LAST_STAND;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.LAST_STAND;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText("""
                        Applying {0} to armor lets you consume experience points to cancel death.   \s
                        The amount of experience consumed scales with **the total level of all worn armor**. Using the *current level* (N) and *enchantment level* (L) along with the *base cost* (B), *rate* (R), and *maximum* (M) values configurable in the settings, the cost consumed is calculated as   \s
                        min(M,N\\*R/√L+B/L)   \s
                        By default, this also cancels death from damage that bypasses invulnerability, such as /kill.
                        """, """
                        　防具に{0}を付けることで、経験値を消費して死亡をキャンセルできます。   \s
                        　消費経験値は**全ての防具のレベルの合計**に応じて変動します。*現在の経験値レベル*(N)、*エンチャントレベル*(L)及び設定で変更できる*基本値*(B)、*割合*(R)、*最大値*(M)を用いて、消費されるコストは   \s
                        min(M,N\\*R/√L+B/L)   \s
                        で計算されます。   \s
                        　デフォルトでは、/killなど無敵を貫通するダメージによる死亡もキャンセルします。
                        """, translatedName())));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.TOTEM_OF_UNDYING);
    }
}
