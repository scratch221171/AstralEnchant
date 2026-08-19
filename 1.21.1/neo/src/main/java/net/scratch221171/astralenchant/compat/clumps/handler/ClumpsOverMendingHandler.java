package net.scratch221171.astralenchant.compat.clumps.handler;

import com.blamejared.clumps.api.events.RepairEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.scratch221171.astralenchant.common.enchantment.handler.OverMendingHandler;
import net.scratch221171.astralenchant.mixin.IExperienceOrbAccessor;

public class ClumpsOverMendingHandler {

    /** Clumpsは{@link ExperienceOrb#playerTouch(Player)}をまるごとInjectでキャンセルし、さらに修繕を実行する前にRepairEventを呼ぶため、
     * <p>
     * - 修繕可能な装備が存在する間は必ず修繕に使われる
     * <p>
     * - **その後**、OverMending可能な装備が存在する場合は必ずOverMendingに使われる
     * <p>
     * の二つの仕様を満たすためにだいぶ遠回りな実装をする必要が生じた。
     **/
    public static void onClumpsRepair(RepairEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer serverPlayer)) return;

        ExperienceOrb dummy = new ExperienceOrb(serverPlayer.level(), 0, 0, 0, 0);
        int afterMending =
                ((IExperienceOrbAccessor) dummy).astralenchant$repairPlayerItems(serverPlayer, event.getValue());
        int afterOverMending = OverMendingHandler.repairPlayerItems(serverPlayer, afterMending);

        event.setValue(afterOverMending);
    }
}
