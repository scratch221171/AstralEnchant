package net.scratch221171.astralenchant.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;

public class AELootTables {

    public static final ResourceKey<LootTable> NETHER_WART = create(ID.ENTITIES_NETHER_WART),
            WITHER_SKELETON_SKULL = create(ID.ENTITIES_WITHER_SKELETON_SKULL),
            ECHO_SHARD = create(ID.ENTITIES_ECHO_SHARD),
            ROTTEN_BONE = create(ID.ENTITIES_ROTTEN_BONE),
            ALLAY_WING = create(ID.ENTITIES_ALLAY_WING);

    private static ResourceKey<LootTable> create(String id) {
        return ResourceKey.create(Registries.LOOT_TABLE, ModUtils.loc(id));
    }
    ;
}
