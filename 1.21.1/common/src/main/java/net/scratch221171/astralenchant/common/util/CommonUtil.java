package net.scratch221171.astralenchant.common.util;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ModUtils;

public class CommonUtil {

    public static Item getAEItem(String id) {
        var item = BuiltInRegistries.ITEM.get(ModUtils.loc(id));
        if (item == Items.AIR) throw new IllegalArgumentException("Item \"" + id + "\" was not found!");
        return item;
    }

    public static String getLangKey(ResourceKey<Enchantment> key) {
        return key.location().toLanguageKey("enchantment");
    }

    public static String getAEItemLangKey(String name) {
        return Util.makeDescriptionId("item", ModUtils.loc(name));
    }

    public static String getDescLangKey(ResourceKey<Enchantment> key) {
        return key.location().toLanguageKey("enchantment", "desc");
    }
}
