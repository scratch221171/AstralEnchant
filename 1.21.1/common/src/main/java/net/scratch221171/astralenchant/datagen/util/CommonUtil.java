package net.scratch221171.astralenchant.datagen.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ModUtils;

public class CommonUtil {

    public static Item getAEItem(String name) {
        return BuiltInRegistries.ITEM.get(ModUtils.loc(name));
    }

    public static String getLangKey(ResourceKey<Enchantment> key) {
        return key.location().toLanguageKey("enchantment");
    }

    public static String getDescLangKey(ResourceKey<Enchantment> key) {
        return key.location().toLanguageKey("enchantment", "desc");
    }
}
