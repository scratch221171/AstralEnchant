package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
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
        page("content", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                .withText(pageText("""
                        This is English!   \s
                        New line   \s
                        """, """
                        防具にラストスタンドを付けることで、経験値を消費して死亡をキャンセルできます。
                        \\
                        消費経験値は**全ての防具のレベルの合計**に応じて変動します。
                        *現在のレベル*(N)及び設定で変更できる*基本値*(B)、*割合*(R)、*最大値*(M)を用いて、消費されるコストは
                        \\
                        min(M,N\\*R/√L+B/L)
                        \\
                        で計算されます。
                        \\
                        デフォルトでは、/killなど無敵を貫通するダメージによる死亡もキャンセルします。
                        """)));

        addDetails(lookups);
    }
}
