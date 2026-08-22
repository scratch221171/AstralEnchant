package net.scratch221171.astralenchant.common.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.scratch221171.astralenchant.ModUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ArcaniumArmorItem extends AEArmorItem implements IAEDescribable {

    public ArcaniumArmorItem(
            Holder<ArmorMaterial> material,
            Type type,
            Properties properties,
            @Nullable String descKey,
            @Nullable Style style) {
        super(material, type, properties, descKey, style);
    }

    @Override
    public @NonNull ItemAttributeModifiers getDefaultAttributeModifiers() {
        return switch (type) {
            case Type.HELMET ->
                super.getDefaultAttributeModifiers()
                        .withModifierAdded(
                                Attributes.BLOCK_INTERACTION_RANGE,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()),
                                        0.2,
                                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                EquipmentSlotGroup.HEAD)
                        .withModifierAdded(
                                Attributes.ENTITY_INTERACTION_RANGE,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()),
                                        0.2,
                                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                EquipmentSlotGroup.HEAD);

            case CHESTPLATE ->
                super.getDefaultAttributeModifiers()
                        .withModifierAdded(
                                Attributes.BLOCK_BREAK_SPEED,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()),
                                        0.2,
                                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                EquipmentSlotGroup.CHEST)
                        .withModifierAdded(
                                Attributes.ATTACK_SPEED,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()),
                                        0.2,
                                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                EquipmentSlotGroup.CHEST);
            case LEGGINGS ->
                super.getDefaultAttributeModifiers()
                        .withModifierAdded(
                                Attributes.MOVEMENT_SPEED,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()),
                                        0.2,
                                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                EquipmentSlotGroup.LEGS)
                        .withModifierAdded(
                                Attributes.LUCK,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()), 10, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.LEGS);
            case BOOTS ->
                super.getDefaultAttributeModifiers()
                        .withModifierAdded(
                                Attributes.STEP_HEIGHT,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()), 0.5, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.FEET)
                        .withModifierAdded(
                                Attributes.SAFE_FALL_DISTANCE,
                                new AttributeModifier(
                                        ModUtils.loc(type.getName()), 2, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.FEET);
            default -> super.getDefaultAttributeModifiers();
        };
    }
}
