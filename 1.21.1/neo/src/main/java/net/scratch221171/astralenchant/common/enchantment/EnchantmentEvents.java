package net.scratch221171.astralenchant.common.enchantment;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.GrindstoneEvent;
import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.handler.*;
import net.scratch221171.astralenchant.common.event.ItemSetEnchantmentEvent;

@EventBusSubscriber(modid = Constants.MODID)
public class EnchantmentEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    private static void onEnchantmentChange(ItemSetEnchantmentEvent e) {
        CurseOfEnchantmentHandler.cancelChange(e);
    }

    @SubscribeEvent
    private static void onGrindstoneTake(GrindstoneEvent.OnTakeItem e) {
        CurseOfEnchantmentHandler.clearXp(e);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    private static void onBlockBreak(BlockEvent.BreakEvent e) {
        FeatherTouchHandler.createStack(e);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void onBlockDrops(BlockDropsEvent e) {
        FeatherTouchHandler.setDrop(e);
    }

    @SubscribeEvent
    private static void onTickPost(ServerTickEvent.Post e) {
        FeatherTouchHandler.clearCache(e);
    }

    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent e) {
        LastStandHandler.revivePlayer(e);
    }

    @SubscribeEvent
    private static void onInvulnerabilityCheck(EntityInvulnerabilityCheckEvent e) {
        NullificationHandler.addDamageTag(e);
        ReactiveArmorHandler.disableDamageTag(e);
    }

    @SubscribeEvent
    private static void onIncomingDamage(LivingIncomingDamageEvent e) {
        NullificationHandler.spawnParticles(e);
    }

    @SubscribeEvent
    private static void onGetEnchantmentLevel(GetEnchantmentLevelEvent e) {
        OverloadHandler.modifyLevels(e);
    }

    @SubscribeEvent
    private static void onAttributeModification(EntityAttributeModificationEvent e) {
        ResilienceHandler.modifyDefaultAttributes(e);
        EndlessAppetiteHandler.modifyDefaultAttributes(e);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    private static void onAnvilUpdate(AnvilUpdateEvent e) {
        AffinityHandler.forceUncanceled(e);
        CurseOfEnchantmentHandler.forceCancel(e);
    }
}
