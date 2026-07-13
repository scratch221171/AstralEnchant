package net.scratch221171.astralenchant.common.item;

import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class AEItem extends Item implements IAEDescribable {

    @Nullable private final String descKey;

    @Nullable private final Style style;

    /** REGISTER.registerSimpleItem相当(説明なし) */
    public AEItem(Properties properties) {
        this(properties, null, null);
    }

    /** 説明キー付き */
    public AEItem(Properties properties, @Nullable String descKey, @Nullable Style style) {
        super(properties);
        this.descKey = descKey;
        this.style = style;
    }

    @Override
    public String getDescKey(ItemStack stack) {
        return descKey;
    }

    @Override
    public Style getDescStyle(ItemStack stack) {
        return style;
    }
}
