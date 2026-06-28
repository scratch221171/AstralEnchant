package net.scratch221171.astralenchant.common.enchantment.handler;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.tag.TagGroupLoader;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;

@EventBusSubscriber(modid = Constants.MODID)
public class ReactiveArmorHandler {

    @SubscribeEvent
    private static void disableDamageTag(EntityInvulnerabilityCheckEvent event) {
        var entity = event.getEntity();
        if (entity.level().isClientSide) return;
        var source = event.getSource();
        if (entity instanceof LivingEntity victim) {
            AEUtil.getEnchantmentHolder(AEEnchantments.REACTIVE_ARMOR, victim).ifPresent(holder -> {
                if (EnchantmentHelper.getEnchantmentLevel(holder, victim) > 0) {
                    var accessor = (IDamageSourceExtension) source;
                    var targets = TagGroupLoader.getReactiveArmorTags();
                    targets.forEach(accessor::astralenchant$addDisabledTag);
                }
            });
        }
    }
}
