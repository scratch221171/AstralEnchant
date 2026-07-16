package net.scratch221171.astralenchant.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.inventory.EnchantersWorkbenchMenu;

public class AEMenuTypes {
    public static final DeferredRegister<MenuType<?>> REGISTER =
            DeferredRegister.create(Registries.MENU, Constants.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<EnchantersWorkbenchMenu>> ENCHANTERS_WORKBENCH =
            REGISTER.register(
                    ID.ENCHANTERS_WORKBENCH,
                    () -> new MenuType<>(EnchantersWorkbenchMenu::new, FeatureFlags.VANILLA_SET));

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
