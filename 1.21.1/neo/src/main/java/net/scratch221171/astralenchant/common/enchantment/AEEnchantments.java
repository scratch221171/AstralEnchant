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

    public static final ResourceKey<Enchantment> NULLIFICATION = create(ID.NULLIFICATION),
            LAST_STAND = create(ID.LAST_STAND),
            ESSENCE_OF_ENCHANTMENT = create(ID.ESSENCE_OF_ENCHANTMENT),
            RESILIENCE = create(ID.RESILIENCE),
            FEATHER_TOUCH = create(ID.FEATHER_TOUCH),
            AFFINITY = create(ID.AFFINITY),
            ENDLESS_APPETITE = create(ID.ENDLESS_APPETITE),
            OVERLOAD = create(ID.OVERLOAD),
            EXPANSE = create(ID.EXPANSE),
            REACTIVE_ARMOR = create(ID.REACTIVE_ARMOR),
            CURSE_OF_IGNORANCE = create(ID.CURSE_OF_IGNORANCE),
            CURSE_OF_ENCHANTMENT = create(ID.CURSE_OF_ENCHANTMENT),
            DISTORTION = create(ID.DISTORTION),
            ALMIGHTY = create(ID.ALMIGHTY);

    private static ResourceKey<Enchantment> create(String name) {
        var key = ResourceKey.create(Registries.ENCHANTMENT, ModUtils.loc(name));
        LIST.add(key);
        return key;
    }
}
