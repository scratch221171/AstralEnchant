package net.scratch221171.astralenchant.common.recipe.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.registry.AEItems;

public class TransformRecipeHandler {

    private static final String NBT_KEY_TIME = ModUtils.loc("lava_transform").toString();

    private static final int REQUIRED_TIME = 20;

    public static void handle(EntityTickEvent event) {
        if (event.getEntity().level().isClientSide) return;
        // ae2があればそっちのレシピを使ってもらおう
        if (ModList.get().isLoaded("ae2")) return;
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        var stack = itemEntity.getItem();
        if (!stack.is(AEItems.LAVAPROOF_ARCANE_QUARTZ)) return;

        var fluidState = itemEntity
                .level()
                .getFluidState(new BlockPos(
                        (int) Math.floor(itemEntity.getX()),
                        (int) Math.floor((itemEntity.getBoundingBox().minY + itemEntity.getBoundingBox().maxY) / 2.0D),
                        (int) Math.floor(itemEntity.getZ())));
        if (fluidState.is(FluidTags.LAVA)) {
            var nbt = itemEntity.getPersistentData();
            int currentTime = nbt.getInt(NBT_KEY_TIME) + 1;

            if (currentTime >= REQUIRED_TIME) {
                int number = Math.min(stack.getCount(), 4);
                var level = (ServerLevel) itemEntity.level();

                double x = itemEntity.getX(), y = itemEntity.getY() + 0.1, z = itemEntity.getZ();
                final ItemEntity newEntity =
                        new ItemEntity(level, x, y, z, new ItemStack(AEItems.LUMINITE.get(), number));
                var random = RandomSource.create();
                final double vx = random.nextDouble() * 0.25 - 0.125;
                final double vy = random.nextDouble() * 0.25 - 0.125;
                final double vz = random.nextDouble() * 0.25 - 0.125;
                newEntity.setDeltaMovement(vx, vy, vz);
                level.addFreshEntity(newEntity);
                level.sendParticles(ParticleTypes.LAVA, x, y, z, 1, 0, 0, 0, 1);
                level.playSound(null, x, y, z, SoundEvents.GENERIC_BURN, SoundSource.AMBIENT, 0.3f, 1);

                stack.setCount(stack.getCount() - number);
            } else {
                nbt.putInt(NBT_KEY_TIME, currentTime);
            }
        } else {
            var nbt = itemEntity.getPersistentData();
            if (nbt.contains(NBT_KEY_TIME)) {
                nbt.remove(NBT_KEY_TIME);
            }
        }
    }
}
