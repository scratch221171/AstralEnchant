package net.scratch221171.astralenchant.common.util;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;

public interface EnchantmentLookups {
    HolderLookup.RegistryLookup<Item> items();

    HolderLookup.RegistryLookup<Attribute> attributes();

    HolderSet<Item> anyItems();
}
