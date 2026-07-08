package net.scratch221171.astralenchant.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.enchantment.handler.AlmightyHandler;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @ModifyVariable(method = "getDigSpeed", at = @At(value = "STORE", ordinal = 0))
    private float astralenchant$virtualSpeed(float f, BlockState state, BlockPos pos) {
        Player player = (Player) (Object) this;
        if (AEUtil.getEnchantmentLevel(AEEnchantments.ALMIGHTY, player) <= 0) return f;

        ItemStack tool = player.getMainHandItem();
        if (!(tool.getItem() instanceof TieredItem tiered)) return f;

        float virtual = AlmightyHandler.getVirtualDestroySpeed(tiered.getTier(), state);
        return Math.max(f, virtual);
    }
}
