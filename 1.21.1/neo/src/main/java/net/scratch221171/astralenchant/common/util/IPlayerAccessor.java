package net.scratch221171.astralenchant.common.util;

import java.util.Optional;
import net.minecraft.world.entity.player.Player;

public interface IPlayerAccessor {
    Optional<Player> astralenchant$getPlayer();

    void astralenchant$setPlayer(Player player);
}
