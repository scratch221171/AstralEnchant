package net.scratch221171.astralenchant.common.enchantment;

import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;

public class AEEnchantments {

    public static final Set<ResourceKey<Enchantment>> LIST = new LinkedHashSet<>();

    public static final ResourceKey<Enchantment> NULLIFICATION = create(ID.NULLIFICATION);

    public static final ResourceKey<Enchantment> LAST_STAND = create(ID.LAST_STAND);

    //    public static final ResourceKey<Enchantment> ITEM_PROTECTION = create(ID.ITEM_PROTECTION);

    public static final ResourceKey<Enchantment> ESSENCE_OF_ENCHANTMENT = create(ID.ESSENCE_OF_ENCHANTMENT);

    public static final ResourceKey<Enchantment> RESILIENCE = create(ID.RESILIENCE);

    public static final ResourceKey<Enchantment> FEATHER_TOUCH = create(ID.FEATHER_TOUCH);

    //    public static final ResourceKey<Enchantment> ADVENTURERS_LORE = create(ID.ADVENTURERS_LORE);

    public static final ResourceKey<Enchantment> AFFINITY = create(ID.AFFINITY);

    public static final ResourceKey<Enchantment> ENDLESS_APPETITE = create(ID.ENDLESS_APPETITE);

    //    public static final ResourceKey<Enchantment> MOMENTUM = create(ID.MOMENTUM);

    //    public static final ResourceKey<Enchantment> INSTANT_TELEPORT = create(ID.INSTANT_TELEPORT);

    public static final ResourceKey<Enchantment> OVERLOAD = create(ID.OVERLOAD);

    public static final ResourceKey<Enchantment> EXPANSE = create(ID.EXPANSE);

    public static final ResourceKey<Enchantment> REACTIVE_ARMOR = create(ID.REACTIVE_ARMOR);

    //    public static final ResourceKey<Enchantment> MYSTIC_REMNANTS = create(ID.MYSTIC_REMNANTS);

    public static final ResourceKey<Enchantment> CURSE_OF_IGNORANCE = create(ID.CURSE_OF_IGNORANCE);

    public static final ResourceKey<Enchantment> CURSE_OF_ENCHANTMENT = create(ID.CURSE_OF_ENCHANTMENT);

    public static final ResourceKey<Enchantment> DISTORTION = create(ID.DISTORTION);

    //    public static final ResourceKey<Enchantment> OVER_MENDING = create(ID.OVER_MENDING);

    public static final ResourceKey<Enchantment> ALMIGHTY = create(ID.ALMIGHTY);

    private static ResourceKey<Enchantment> create(String name) {
        var key = ResourceKey.create(Registries.ENCHANTMENT, ModUtils.loc(name));
        LIST.add(key);
        return key;
    }
}
