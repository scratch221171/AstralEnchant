package net.scratch221171.astralenchant.compat.curios.handler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.config.ServerConfig;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;
import top.theillusivec4.curios.api.type.ISlotType;

public final class CuriosExpanseHandler {

    public static void addModifier(CurioAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;
        SlotContext slotContext = event.getSlotContext();

        int level = AEUtil.getEnchantmentLevel(AEEnchantments.EXPANSE, stack);
        if (level <= 0) return;

        if (ServerConfig.EnchantmentSettings.Expanse.BASE_SLOTS_ONLY.getAsBoolean()) {
            int baseSize = Optional.ofNullable(slotContext.entity())
                    .flatMap(entity -> CuriosApi.getSlot(slotContext.identifier(), entity.level())
                            .map(ISlotType::getSize))
                    .orElse(0);
            if (slotContext.index() >= baseSize) return;
        }

        Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
        CuriosApi.addSlotModifier(
                map, slotContext.identifier(), event.getId(), level, AttributeModifier.Operation.ADD_VALUE);
        map.forEach(event::addModifier);
    }
}
