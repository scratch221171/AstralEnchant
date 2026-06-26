package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.tag.TagGroupLoader;
import net.scratch221171.astralenchant.util.AEUtil;
import net.scratch221171.astralenchant.util.IDamageSourceExtension;

import java.util.List;

@EventBusSubscriber(modid = Constants.MODID)
public class NullificationHandler {

    @SubscribeEvent
    private static void addDamageTag(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof LivingEntity attacker) {
            AEUtil.getEnchantmentHolder(AEEnchantments.NULLIFICATION, attacker)
                    .ifPresent(holder -> {
                        var weapon = source.getWeaponItem();
                        if (weapon != null && weapon.getEnchantmentLevel(holder) > 0) {
                            Constants.LOGGER.info("hi");
                            var accessor = (IDamageSourceExtension) source;
                            List<TagKey<DamageType>> targets = TagGroupLoader.getNullificationTags();
                            Constants.LOGGER.info(targets.toString());
                            targets.forEach(accessor::astralenchant$addExtraTag);
                            targets.forEach(tag -> Constants.LOGGER.info("tag: " + tag.toString()));
                        }
                    });
        }
    }

    // パーティクル
    @SubscribeEvent
    private static void onDamage(LivingIncomingDamageEvent event) {
        Entity entity = event.getEntity();
        AEUtil.getEnchantmentHolder(AEEnchantments.NULLIFICATION, entity).ifPresent(holder -> {
            ItemStack weapon = event.getSource().getWeaponItem();
            if (weapon != null
                    && weapon.getEnchantmentLevel(holder) > 0
                    && entity.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 10, 0f, 1f, 0f, 0.04f);
            }
        });
    }
}
