package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.Util;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class OverMendingHandler {

    public static void addExtraDurability(PlayerXpEvent.PickupXp event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            event.getOrb().value = repairPlayerItems(player, event.getOrb().value);
        }
    }

    public static int repairPlayerItems(ServerPlayer player, int value) {
        var optional = getRandomItemWith(player, stack -> {
            int level = AEUtil.getEnchantmentLevel(AEEnchantments.OVER_MENDING, stack);
            return level > 0 && stack.getOrDefault(AEDataComponents.EXTRA_DURABILITY, 0) < maxExtra(level);
        });
        if (optional.isPresent()) {
            var stack = optional.get();
            int level = AEUtil.getEnchantmentLevel(AEEnchantments.OVER_MENDING, stack);
            int i = EnchantmentHelper.modifyDurabilityToRepairFromXp(
                    (ServerLevel) player.level(), stack, (int) (value * stack.getXpRepairRatio()));
            int currentExtra = stack.getOrDefault(AEDataComponents.EXTRA_DURABILITY, 0);
            int j = Math.min(i, maxExtra(level) - currentExtra);
            stack.set(AEDataComponents.EXTRA_DURABILITY, currentExtra + j);
            if (j > 0) {
                int k = value - j * value / i;
                if (k > 0) {
                    return repairPlayerItems(player, value);
                }
            }

            return 0;
        } else {
            return value;
        }
    }

    public static int maxExtra(int level) {
        return 100 * level * level;
    }

    private static Optional<ItemStack> getRandomItemWith(LivingEntity entity, Predicate<ItemStack> filter) {
        List<ItemStack> list = new ArrayList<>();

        for (EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(equipmentslot);
            if (filter.test(stack)) {
                list.add(stack);
            }
        }

        return Util.getRandomSafe(list, entity.getRandom());
    }
}
