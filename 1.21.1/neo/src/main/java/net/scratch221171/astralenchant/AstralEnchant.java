package net.scratch221171.astralenchant;

import java.util.Comparator;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.scratch221171.astralenchant.common.condition.ConfigCondition;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.loot.MysticRemnantsLootModifier;
import net.scratch221171.astralenchant.common.registry.*;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.compat.curios.CuriosCompat;
import net.scratch221171.astralenchant.config.ModConfigs;
import net.scratch221171.astralenchant.mdk.config.PlatformConfigRegistrar;
import net.scratch221171.astralenchant.mdk.config.VersionedConfigSpec;

@Mod(Constants.MODID)
public class AstralEnchant {
    public AstralEnchant(IEventBus modEventBus, ModContainer modContainer) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.21.1-neo"));
        PlatformConfigRegistrar.registerAll(modContainer, VersionedConfigSpec.bindAll(ModConfigs.ALL));

        modEventBus.addListener(this::registerMisc);
        modEventBus.addListener(this::registerCreativeTabs);
        modEventBus.addListener(this::commonSetup);

        AEItems.register(modEventBus);
        AEArmorMaterials.register(modEventBus);
        AEBlocks.register(modEventBus);
        AEDataComponents.register(modEventBus);
        AEMenuTypes.register(modEventBus);
        AEAttributes.register(modEventBus);

        if (ModList.get().isLoaded("curios")) {
            CuriosCompat.registerCuriosEvents();
        }

        NeoForgeMod.enableMergedAttributeTooltips();
    }

    private void registerMisc(RegisterEvent event) {
        event.register(NeoForgeRegistries.Keys.CONDITION_CODECS, helper -> {
            helper.register(ModUtils.loc("config_condition"), ConfigCondition.CODEC);
        });
        event.register(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
            helper.register(ModUtils.loc("mystic_remnants_loot_modifier"), MysticRemnantsLootModifier.CODEC);
        });
    }

    private void registerCreativeTabs(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, helper -> {
            helper.register(
                    ModUtils.loc("astralenchant_main"),
                    CreativeModeTab.builder()
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
            helper.register(
                    ModUtils.loc("astralenchant_enchantment"),
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.astralenchant.enchantment"))
                            .icon(() -> new ItemStack(AEItems.ENCHANTMENT_VESSEL.get()))
                            .displayItems((parameters, output) -> AEEnchantments.getAll().stream()
                                    .sorted(Comparator.comparing(
                                            key -> key.location().getPath()))
                                    .forEach(key -> AEUtil.getEnchantmentHolder(key)
                                            .ifPresent(h -> output.accept(EnchantedBookItem.createForEnchantment(
                                                    new EnchantmentInstance(
                                                            h.getDelegate(),
                                                            h.value().getMaxLevel()))))))
                            .build());
        });
    }

    private void commonSetup(FMLCommonSetupEvent event) {}
}
