package net.scratch221171.astralenchant.common.event;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * Called when an item's enchantments are about to be changed through
 * {@link ItemStack#set(DataComponentType, Object)}.
 * Cancelling this event prevents the enchantments from being changed.
 */
public class ItemSetEnchantmentEvent extends Event implements ICancellableEvent {

    private final ItemStack stack;
    private ItemEnchantments enchantments;

    /**
     * @param stack the item stack whose enchantments are about to be changed
     * @param enchantments all enchantments the item will have after the event
     */
    public ItemSetEnchantmentEvent(ItemStack stack, ItemEnchantments enchantments) {
        this.stack = stack;
        this.enchantments = enchantments;
    }

    public ItemStack getStack() {
        return stack;
    }

    public ItemEnchantments getEnchantments() {
        return enchantments;
    }

    public void setEnchantments(ItemEnchantments enchantments) {
        this.enchantments = enchantments;
    }
}
