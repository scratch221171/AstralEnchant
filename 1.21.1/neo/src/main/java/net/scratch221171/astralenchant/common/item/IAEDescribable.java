package net.scratch221171.astralenchant.common.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public interface IAEDescribable {

    /** 追加説明の翻訳キー。nullなら何も追加しない(既定でnull)。 */
    @Nullable default String getDescKey(ItemStack stack) {
        return null;
    }

    /** 翻訳の埋め込み引数。使わないなら空配列のまま。 */
    default Object[] getDescArgs(ItemStack stack) {
        return new Object[0];
    }

    /** 説明行のスタイル。Item側で自由に上書き可能。 */
    default Style getDescStyle(ItemStack stack) {
        return Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(true);
    }

    /** 挿入位置。既定は末尾追加。 */
    default int getDescInsertIndex(ItemStack stack, List<Component> currentTooltip) {
        return currentTooltip.size();
    }
}
