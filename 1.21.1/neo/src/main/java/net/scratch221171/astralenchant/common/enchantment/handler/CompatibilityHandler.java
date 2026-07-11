package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class CompatibilityHandler {

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
