package net.scratch221171.astralenchant.common.registry;

import java.util.Comparator;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

public class AECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN =
            REGISTER.register("astralenchant_main", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.astralenchant.main"))
                    .icon(() -> new ItemStack(AEItems.ARCANE_QUARTZ.get()))
                    .displayItems((parameters, output) -> {
                        AEItems.REGISTER.getEntries().forEach(e -> {
                            if (e == AEItems.BUDDING_ARCANIUM_INGOT) {
                                for (float progress = 0.0f; progress < 1.0f; progress += 0.25f) {
                                    ItemStack stack = new ItemStack(AEItems.BUDDING_ARCANIUM_INGOT.get());
                                    stack.set(AEDataComponents.PROCESSING_PROGRESS, progress);
                                    output.accept(stack);
                                }
                            } else {
                                output.accept(e.get());
                            }
                        });
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ENCHANTMENT =
            REGISTER.register("astralenchant_enchantment", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.astralenchant.enchantment"))
                    .icon(() -> new ItemStack(AEItems.ENCHANTMENT_VESSEL.get()))
                    .displayItems((parameters, output) -> AEEnchantments.LIST.stream()
                            .sorted(Comparator.comparing(key -> key.location().getPath()))
                            .forEach(e -> AEUtil.getEnchantmentHolder(e)
                                    .ifPresent(h -> output.accept(
                                            EnchantedBookItem.createForEnchantment(new EnchantmentInstance(
                                                    h.getDelegate(), h.value().getMaxLevel()))))))
                    .build());

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
