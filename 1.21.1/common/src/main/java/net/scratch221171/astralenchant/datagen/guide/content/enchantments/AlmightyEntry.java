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
                // TODO: English Docs
                .withText(pageText("""
                        This is English!   \s
                        New line   \s
                        """, """
                        　{0}を付けると、そのツールの採掘ティアをlv1で鉄、lv2でダイヤモンド, lv3でネザライトまで増加させ、, lv4でさらにデフォルトでは{1}などの不可壊なブロックが採掘できるようにします。   \s
                        　{0}はツールタイプの制約を無くし、採掘ティアを引き上げますが、採掘速度は元のティア依存なので、例えばlv4の{0}付きの{2}は黒曜石を採掘できますが、速度は木ツールのままなので恐ろしく時間がかかります。
                        """, translatedName(), itemLink(Items.BEDROCK), itemLink(Items.WOODEN_HOE))));

        addDetails(lookups);
    }
}
