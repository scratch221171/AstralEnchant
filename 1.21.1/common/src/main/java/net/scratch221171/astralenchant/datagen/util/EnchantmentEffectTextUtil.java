package net.scratch221171.astralenchant.datagen.util;

import java.util.List;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

public final class EnchantmentEffectTextUtil {

    /** attribute系のエフェクトだけをフォーマットする */
    public static String formatAttributeEffects(List<EnchantmentAttributeEffect> effects) {
        var sb = new StringBuilder();
        for (var effect : effects) {
            sb.append("   <t>")
                    .append(effect.attribute().value().getDescriptionId())
                    .append("<t>:")
                    .append(formatAmount(effect.amount(), effect.operation()))
                    .append("\n");
        }
        return sb.toString();
    }

    private static String formatAmount(LevelBasedValue value, AttributeModifier.Operation operation) {
        boolean isPercent = operation != AttributeModifier.Operation.ADD_VALUE;

        if (value instanceof LevelBasedValue.Constant(float value1)) {
            return format(value1, isPercent);
        }
        if (value instanceof LevelBasedValue.Linear(float base1, float perLevelAboveFirst)) {
            var base = format(base1, isPercent);
            if (perLevelAboveFirst == 0f) return base;
            return base + "+(" + format(perLevelAboveFirst, isPercent) + ")\\*レベル";
        }
        // Clamped/Fraction/Lookup等は未対応
        return value.toString();
    }

    private static String format(float value, boolean isPercent) {
        String sign = value >= 0 ? "+" : "";
        return isPercent ? sign + Math.round(value * 100) + "%%" : sign + value;
    }
}
