package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class AffinityHandler {

    public static void forceUncanceled(AnvilUpdateEvent event) {
        if (AEUtil.getEnchantmentLevel(
                                AEEnchantments.AFFINITY,
                                event.getRight()
                                        .getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY))
                        > 0
                || (AEUtil.getEnchantmentLevel(AEEnchantments.AFFINITY, event.getLeft()) > 0
                        && event.getRight().has(DataComponents.STORED_ENCHANTMENTS))) {
            event.setOutput(ItemStack.EMPTY);
            event.setCanceled(false);
        }
    }

    public static final class Context {
        private static final ThreadLocal<Deque<ItemStack>> STACK = ThreadLocal.withInitial(ArrayDeque::new);

        private Context() {}

        public static void push(ItemStack stack) {
            STACK.get().push(stack);
        }

        public static void pop() {
            Deque<ItemStack> deque = STACK.get();
            if (!deque.isEmpty()) deque.pop();
        }

        public static boolean isActive() {
            Deque<ItemStack> deque = STACK.get();
            if (deque.isEmpty()) return false;
            return AEUtil.getEnchantmentLevel(AEEnchantments.AFFINITY, deque.peek()) > 0;
        }
    }
}
