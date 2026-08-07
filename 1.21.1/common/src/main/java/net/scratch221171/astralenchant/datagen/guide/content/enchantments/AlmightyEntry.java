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

public class AlmightyEntry extends EnchantmentEntry {

    public AlmightyEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ALMIGHTY;
    }

    @Override
    protected ResourceKey<Enchantment> key() {
        return AEEnchantments.ALMIGHTY;
    }

    @Override
    protected void generatePages() {
        page("desc", () -> BookTextPageModel.create()
                .withTitle(CommonUtil.getLangKey(key()))
                .withText(pageText(
                        """
                        Applying {0} raises the mining tier of the tool — iron at level 1, diamond at level 2, and netherite at level 3 — and at level 4, by default, also allows mining unbreakable blocks such as {1}.   \s
                        {0} removes the tool-type restriction and raises the mining tier, but mining speed still depends on the original tier of the tool. For example, a level-4 {2} with {0} can mine {3}, but since its speed is still that of a wooden tool, it will take an incredibly long time.
                        """,
                        """
                        　{0}を付けると、そのツールの採掘ティアをlv1で鉄、lv2でダイヤモンド、lv3でネザライトまで増加させ、lv4でさらにデフォルトでは{1}などの不可壊なブロックが採掘できるようにします。   \s
                        　{0}はツールタイプの制約を無くし、採掘ティアを引き上げますが、採掘速度は元のティア依存なので、例えばlv4の{0}付きの{2}は{3}を採掘できますが、速度は木ツールのままなので恐ろしく時間がかかります。
                        """,
                        translatedName(),
                        itemLink(Items.BEDROCK),
                        itemLink(Items.WOODEN_HOE),
                        itemLink(Items.OBSIDIAN))));

        addDetails(lookups);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
    }
}
