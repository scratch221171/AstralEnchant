package net.scratch221171.astralenchant.common.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
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

    public static void dropBookOnBreak(ItemStack stack, Entity entity) {
        if (!EnchantmentHelper.hasAnyEnchantments(stack) || entity == null) return;
        var book = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantmentHelper.setEnchantments(book, stack.getTagEnchantments());
        if (entity instanceof Player player) {
            player.getInventory().placeItemBackInInventory(book);
        } else {
            entity.spawnAtLocation(book);
        }
    }
}
