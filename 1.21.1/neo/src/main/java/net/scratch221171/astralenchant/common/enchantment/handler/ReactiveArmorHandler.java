package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;
import net.scratch221171.astralenchant.config.ServerConfig;

public class ReactiveArmorHandler {

    public static void disableDamageTag(EntityInvulnerabilityCheckEvent event) {
        var entity = event.getEntity();
        if (entity.level().isClientSide) return;
        var source = event.getSource();
        if (entity instanceof LivingEntity victim) {
            AEUtil.getEnchantmentHolder(AEEnchantments.REACTIVE_ARMOR, victim).ifPresent(holder -> {
                if (EnchantmentHelper.getEnchantmentLevel(holder, victim) > 0) {
                    var accessor = (IDamageSourceExtension) source;
                    var targets = getReactiveArmorTags();
                    targets.forEach(accessor::astralenchant$addDisabledTag);
                }
            });
        }
    }

    private static List<TagKey<DamageType>> getReactiveArmorTags() {
        List<TagKey<DamageType>> tags = new ArrayList<>();
        boolean error = false;
        for (String loc : ServerConfig.EnchantmentSettings.ReactiveArmor.DISABLED_TAG.get()) {
            try {
                var id = ResourceLocation.tryParse(loc);
                if (id != null) tags.add(TagKey.create(Registries.DAMAGE_TYPE, id));
            } catch (Exception e) {
                Constants.LOGGER.error("Error while loading tags for Reactive Armor!", e);
                Constants.LOGGER.error("using default value instead...");
                error = true;
            }
        }
        if (error) {
            for (String loc : ServerConfig.EnchantmentSettings.ReactiveArmor.DISABLED_TAG.defaultValue()) {
                var id = ResourceLocation.tryParse(loc);
                if (id != null) tags.add(TagKey.create(Registries.DAMAGE_TYPE, id));
            }
        }
        return tags;
    }
}
