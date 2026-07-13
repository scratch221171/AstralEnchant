package net.scratch221171.astralenchant.common.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.item.IAEDescribable;
import org.jspecify.annotations.Nullable;

public class AEArmorItem extends ArmorItem implements IAEDescribable {
    @Nullable private final String descKey;

    public AEArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        this(material, type, properties, null);
    }

    public AEArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, @Nullable String descKey) {
        super(material, type, properties);
        this.descKey = descKey;
    }

    @Override
    public String getDescKey(ItemStack stack) {
        return descKey;
    }

    @Override
    public Style getDescStyle(ItemStack stack) {
        return Style.EMPTY.withColor(ChatFormatting.GRAY);
    }
}
