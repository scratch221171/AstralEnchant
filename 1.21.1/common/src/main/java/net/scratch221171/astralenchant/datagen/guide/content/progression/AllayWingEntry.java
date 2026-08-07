package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookEntityPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class AllayWingEntry extends ItemEntry {

    public AllayWingEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ALLAY_WING;
    }

    @Override
    protected void generatePages() {
        page("desc", BookSpotlightPageModel::create).withItem(item()).withText(pageText("""
                Oh no. Did you really have to kill an innocent Allay, of all things? It flew for you, gathered your dropped items, and worked without a single complaint.   \s
                Looking at the wing in your hand now, can you honestly say your chest does not ache at all? Tonight, as you lie in bed staring at the ceiling, you might find yourself wondering, did I really have to kill it...?   \s
                What was going through its mind in its final moment? Maybe, did I make the master angry? Or maybe, help me, master!   \s
                Of course, there is no way to know now. You are the one who killed it. That wing is exactly the result of your choice. Congratulations.
                """, """
                　*ああ、なんてこった！まさか、よりによって何の罪もないアレイを殺してしまうなんて。彼らはあなたのために空を飛び、落とし物を拾い、文句ひとつ言わず働いてくれていたというのに。*   \s
                　*今手に持っているその羽を見て、胸の奥が少しも痛まないと言い切れるだろうか？今夜ベッドに入ったあと、あなたはふと天井を見つめながら「別に殺さなくてもよかったんじゃないか…？」と考え込んでしまうかもしれない。*   \s
                　*彼らは最期の瞬間、何を思っていただろうか？「ご主人様を怒らせちゃったのかな？」だろうか。それとも、「助けてご主人様！」だろうか。*   \s
                　*もちろん、もう確かめる術はない。あなたが殺めてしまったからだ。その羽はまさしくあなたの選択の結果なのである。おめでとう。*
                """));

        page("entity", () -> BookEntityPageModel.create()
                .withText(pageText(
                        """
                        Rarely dropped when defeating <t>{1}<t> with a weapon enchanted with <t>{0}<t>.
                        """,
                        """
                        <t>{0}<t>を付けた武器で<t>{1}<t>を倒すと稀にドロップします。
                        """,
                        entryLink(
                                CommonUtil.getLangKey(AEEnchantments.MYSTIC_REMNANTS),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.MYSTIC_REMNANTS),
                        EntityType.ALLAY.getDescriptionId()))
                .withEntityId(
                        BuiltInRegistries.ENTITY_TYPE.getKey(EntityType.ALLAY).toString()));
    }
}
