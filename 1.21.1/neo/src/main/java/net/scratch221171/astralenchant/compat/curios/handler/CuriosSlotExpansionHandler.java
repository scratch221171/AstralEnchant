package net.scratch221171.astralenchant.compat.curios.handler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;
import top.theillusivec4.curios.api.type.ISlotType;

public final class CuriosSlotExpansionHandler {

    public static void expandSlot(CurioAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;

        SlotContext slotContext = event.getSlotContext();
        String slotId = slotContext.identifier();

        int baseSize = CuriosApi.getSlot(slotId, slotContext.entity().level())
                .map(ISlotType::getSize)
                .orElse(0);
        if (slotContext.index() >= baseSize) return;

        AEUtil.getEnchantmentHolder(AEEnchantments.EXPANSE, slotContext.entity())
                .map(stack::getEnchantmentLevel)
                .filter(level -> level > 0)
                .ifPresent(level -> {
                    Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
                    CuriosApi.addSlotModifier(map, slotId, event.getId(), level, AttributeModifier.Operation.ADD_VALUE);
                    map.forEach(event::addModifier);
                });
    }
}
