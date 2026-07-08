package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.function.BiConsumer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;
import net.scratch221171.astralenchant.common.util.IAttributeSentimentExtension;
import net.scratch221171.astralenchant.config.ServerConfig;

public class EssenceOfEnchantmentHandler {

    public static void handle(
            ItemStack stack,
            Holder<Attribute> attribute,
            BiConsumer<Holder<Attribute>, AttributeModifier> consumer,
            ResourceLocation id,
            String slotName) {
        if (stack.isEmpty()) return;

        AEUtil.getEnchantmentHolder(AEEnchantments.ESSENCE_OF_ENCHANTMENT).ifPresent(holder -> {
            var level = stack.getEnchantmentLevel(holder);
            if (level <= 0) return;

            int totalLevel = 0;
            var enchantments = ServerConfig.EnchantmentSettings.EssenceOfEnchantment.INCLUDE_OVERLOAD.getAsBoolean()
                    ? AEUtil.getAllEnchantments(stack).entrySet()
                    : stack.getTagEnchantments().entrySet();
            for (var entry : enchantments) {
                if (!entry.getKey().is(AEEnchantments.ESSENCE_OF_ENCHANTMENT)) {
                    totalLevel += entry.getIntValue();
                }
            }

            var newId = ModUtils.loc("eoe_bonus_" + id.getPath() + "_" + slotName);
            Attribute.Sentiment sentiment =
                    ((IAttributeSentimentExtension) attribute.value()).astralenchant$getSentiment();
            AttributeModifier newModifier;

            var multiplier = ServerConfig.EnchantmentSettings.EssenceOfEnchantment.MULTIPLIER.getAsDouble();
            switch (sentiment) {
                // *(1 + a)
                case POSITIVE ->
                    newModifier = new AttributeModifier(
                            newId,
                            totalLevel * level * multiplier / 100f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                // /(1 + a)
                case NEGATIVE ->
                    newModifier = new AttributeModifier(
                            newId,
                            -1 + 1 / (totalLevel * level * multiplier / 100f + 1),
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                default -> {
                    return;
                }
            }

            consumer.accept(attribute, newModifier);
        });
    }
}
