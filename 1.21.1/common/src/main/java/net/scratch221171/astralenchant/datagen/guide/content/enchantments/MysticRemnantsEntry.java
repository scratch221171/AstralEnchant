package net.scratch221171.astralenchant.datagen.guide.content.enchantments;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.key.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.util.EnchantmentEntry;

public class MysticRemnantsEntry extends EnchantmentEntry {

    public MysticRemnantsEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.MYSTIC_REMNANTS;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.MYSTIC_REMNANTS;
    }

    @Override
    protected void generatePages() {
        page("content", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                // TODO: English Docs
                .withText(pageText(
                        """
                        This is English!   \s
                        New line   \s
                        """,
                        """
                        神秘の残滓を付けた武器で一部の敵を倒すと、特殊なドロップをランダムでいくつか得られます。
                        - ウィザー: {0}
                        - ウォーデン: {1}
                        - ボグド: {2}
                        - アレイ: {3}

                        確率は(レベル\\*5)%%、ロール数はそれぞれ異なります。
                        """,
                        itemLink(Items.WITHER_SKELETON_SKULL),
                        itemLink(Items.ECHO_SHARD),
                        itemLink(CommonUtil.getAEItem(ID.ROTTEN_BONE)),
                        itemLink(CommonUtil.getAEItem(ID.ALLAY_WING)))));

        addDetails(lookups);
    }
}
