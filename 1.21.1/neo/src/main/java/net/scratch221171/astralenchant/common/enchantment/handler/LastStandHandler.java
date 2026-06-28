package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;

@EventBusSubscriber(modid = Constants.MODID)
public class LastStandHandler {

    @SubscribeEvent
    private static void onLivingDeath(LivingDeathEvent event) {
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

            var baseCost = ServerConfig.EnchantmentSettings.LastStand.BASE_COST.getAsDouble();
            var costRate = ServerConfig.EnchantmentSettings.LastStand.COST_RATE.getAsDouble();
            var remaining = player.experienceLevel * (1 - costRate / Math.pow(sum, 0.5)) - baseCost / sum;
            if (remaining >= 0) {
                player.giveExperienceLevels((int) Math.floor(remaining) - player.experienceLevel);
                event.setCanceled(true);
                player.setHealth(1f);

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
}
