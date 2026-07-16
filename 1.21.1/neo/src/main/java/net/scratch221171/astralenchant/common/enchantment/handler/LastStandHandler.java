package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;

public class LastStandHandler {

    public static void revivePlayer(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)
                && !ServerConfig.EnchantmentSettings.LastStand.IGNORE_BYPASSES_INVULNERABILITY_TAG.getAsBoolean())
            return;

        AEUtil.getEnchantmentHolder(AEEnchantments.LAST_STAND).ifPresent(holder -> {
            int sum = 0;
            for (ItemStack armor : player.getArmorSlots()) {
                sum += armor.getEnchantmentLevel(holder);
            }
            if (sum == 0) return;

            double consumed = getConsumed(player.experienceLevel, sum);

            if (player.experienceLevel - consumed >= 0) {
                player.giveExperienceLevels((int) Math.floor(-consumed));
                event.setCanceled(true);
                player.setHealth((float) ServerConfig.EnchantmentSettings.LastStand.RECOVER_HEALTH.getAsDouble());

                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            ParticleTypes.TOTEM_OF_UNDYING,
                            player.getX(),
                            player.getY() + 1.0D,
                            player.getZ(),
                            30,
                            0.5,
                            0.5,
                            0.5,
                            0.1);
                    serverLevel.playSound(
                            null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }
        });
    }

    private static double getConsumed(int currentXPLevel, int enchantmentLevel) {
        double baseCost = ServerConfig.EnchantmentSettings.LastStand.BASE_COST.getAsDouble();
        double costRate = ServerConfig.EnchantmentSettings.LastStand.COST_RATE.getAsDouble();
        double maxCost = ServerConfig.EnchantmentSettings.LastStand.MAX_COST.getAsDouble();
        return Math.min(
                maxCost, currentXPLevel * (costRate / Math.pow(enchantmentLevel, 0.5)) + baseCost / enchantmentLevel);
    }
}
