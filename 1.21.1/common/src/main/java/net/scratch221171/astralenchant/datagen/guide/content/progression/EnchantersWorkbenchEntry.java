package net.scratch221171.astralenchant.datagen.guide.content.progression;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.CommonUtil;
import net.scratch221171.astralenchant.datagen.guide.content.EnchantmentCategory;
import net.scratch221171.astralenchant.datagen.util.ItemEntry;

public class EnchantersWorkbenchEntry extends ItemEntry {

    public EnchantersWorkbenchEntry(CategoryProvider parent) {
        super(parent);
    }

    @Override
    protected String id() {
        return ID.ENCHANTERS_WORKBENCH;
    }

    @Override
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        useExistingNameKey(CommonUtil.getAEBlockLangKey(ID.ENCHANTERS_WORKBENCH));
        return entry;
    }

    @Override
    protected void generatePages() {
        page("desc", BookSpotlightPageModel::create)
                .withItem(item())
                .withText(pageText(
                        """
                        At <t>{0}<t>, you can consume {1} to switch the item's existing enchantments.   \s
                        Even enchantments that conflict with each other can coexist by disabling one of them, letting you switch between them depending on your needs.   \s
                        If <t>{2}<t> is applied, the above restriction can be ignored.   \s
                        Note that curse enchantments cannot be switched.
                        """,
                        """
                        　<t>{0}<t>では{1}を消費することで、アイテムの既存のエンチャントを切り替えることができます。   \s
                        　競合するエンチャント同士でも、片方を無効化すれば共存でき、目的に応じて切り替えることができます。   \s
                        　<t>{2}<t>が付いている場合は上記の制限を無視できます。   \s
                        　なお、呪いエンチャントを切り替えることはできません。
                        """,
                        CommonUtil.getAEBlockLangKey(ID.ENCHANTERS_WORKBENCH),
                        itemLink(CommonUtil.getAEItem(ID.LUMINITE)),
                        entryLink(
                                CommonUtil.getLangKey(AEEnchantments.AFFINITY),
                                EnchantmentCategory.CATEGORY_ID,
                                ID.AFFINITY)));

        page("recipe", () -> BookCraftingRecipePageModel.create().withRecipeId1(ModUtils.loc(id())));
    }
}
