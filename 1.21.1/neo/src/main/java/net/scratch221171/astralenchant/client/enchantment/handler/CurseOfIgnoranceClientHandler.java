package net.scratch221171.astralenchant.client.enchantment.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

import java.util.Optional;

public class CurseOfIgnoranceClientHandler {

    private static final String IGNORANCE_KEY = AEUtil.getLangKey(AEEnchantments.CURSE_OF_IGNORANCE);
    private static final String IGNORANCE_DESC_KEY = AEUtil.getDescLangKey(AEEnchantments.CURSE_OF_IGNORANCE);

    public static void makeUnreadable(ItemTooltipEvent event) {
        if (AEUtil.getEnchantmentLevel(AEEnchantments.CURSE_OF_IGNORANCE, event.getItemStack()) <= 0) return;

        AEUtil.modifyTooltip(
                event.getToolTip(),
                c -> !(AEUtil.isTranslationOf(c, IGNORANCE_KEY)
                        || c.getSiblings().stream().anyMatch(e -> AEUtil.isTranslationOf(e, IGNORANCE_DESC_KEY))),
                CurseOfIgnoranceClientHandler::scramble);
    }

    private static Component scramble(Component original) {
        MutableComponent result = Component.empty().withStyle(original.getStyle());

        appendScrambledText(result, selfText(original));

        for (Component sibling : original.getSiblings()) {
            result.append(scramble(sibling));
        }

        return result;
    }

    private static String selfText(Component original) {
        StringBuilder sb = new StringBuilder();
        original.getContents().visit(s -> {
            sb.append(s);
            return Optional.empty(); // 空を返し続けることで全内容を集める
        });
        return sb.toString();
    }

    private static void appendScrambledText(MutableComponent result, String text) {
        var random = RandomSource.create();
        var font = Minecraft.getInstance().font;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isWhitespace(c)) {
                result.append(Component.literal(String.valueOf(c)));
                continue;
            }

            int r = random.nextInt(100);
            if (r < 20) {
                result.append(Component.literal(String.valueOf(c)));
            } else if (r < 25) {
                result.append(Component.literal(String.valueOf(c)).withStyle(ChatFormatting.OBFUSCATED));
            } else {
                int width = font.width(String.valueOf(c));
                result.append(Component.literal("|".repeat(width / 2)));
            }
        }
    }
}
