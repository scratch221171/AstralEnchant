package net.scratch221171.astralenchant.common.item;

import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.scratch221171.astralenchant.common.util.IPlayerExtension;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class EnchantmentSeedsItem extends AEItem {

    public EnchantmentSeedsItem(Properties properties, @Nullable String descKey, @Nullable Style style) {
        super(properties, descKey, style);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(
            @NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity entity) {
        ItemStack stack = super.finishUsingItem(itemStack, level, entity);
        if (entity instanceof Player player && !level.isClientSide) {
            ((IPlayerExtension) player)
                    .astralenchant$setEnchantmentSeed(player.getRandom().nextInt());
        }
        return stack;
    }
}
