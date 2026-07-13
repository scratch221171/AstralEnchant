package net.scratch221171.astralenchant.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class AEItem extends Item implements IAEDescribable {

    @Nullable private final String descKey;

    /** REGISTER.registerSimpleItem相当(説明なし) */
    public AEItem(Properties properties) {
        this(properties, null);
    }

    /** 説明キー付き */
    public AEItem(Properties properties, @Nullable String descKey) {
        super(properties);
        this.descKey = descKey;
    }

    @Override
    public String getDescKey(ItemStack stack) {
        return descKey;
    }
}
