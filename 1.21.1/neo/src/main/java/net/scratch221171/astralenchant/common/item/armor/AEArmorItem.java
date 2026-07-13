package net.scratch221171.astralenchant.common.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.item.IAEDescribable;
import org.jspecify.annotations.Nullable;

public class AEArmorItem extends ArmorItem implements IAEDescribable {
    @Nullable private final String descKey;

    @Nullable private final Style style;

    public AEArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        this(material, type, properties, null, null);
    }

    public AEArmorItem(
            Holder<ArmorMaterial> material,
            Type type,
            Properties properties,
            @Nullable String descKey,
            @Nullable Style style) {
        super(material, type, properties);
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
