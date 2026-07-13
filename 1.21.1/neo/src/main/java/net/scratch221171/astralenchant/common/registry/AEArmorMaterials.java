package net.scratch221171.astralenchant.common.registry;

import java.util.EnumMap;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ModUtils;

public class AEArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> REGISTER =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, Constants.MODID);

    public static final Holder<ArmorMaterial> ARCANIUM = REGISTER.register(
            "arcanium",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 5);
                    }),
                    50,
                    SoundEvents.ARMOR_EQUIP_GOLD,
                    () -> Ingredient.of(AEItems.ARCANIUM_INGOT),
                    List.of(new ArmorMaterial.Layer(ModUtils.loc("arcanium"))),
                    0f,
                    0f));

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
