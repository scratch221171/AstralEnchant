package net.scratch221171.astralenchant.common.enchantment.handler;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.AEUtil;

@EventBusSubscriber(modid = Constants.MODID)
public class FeatherTouchHandler {

    private static final Map<Key, ItemStack> CACHE = new HashMap<>();

    @SubscribeEvent(priority = EventPriority.LOW)
    private static void onBreak(BlockEvent.BreakEvent event) {
        var player = event.getPlayer();
        var level = (ServerLevel) player.level();
        var pos = event.getPos();
        var state = event.getState();

        AEUtil.getEnchantmentHolder(AEEnchantments.FEATHER_TOUCH).ifPresent(holder -> {
            if (player.getMainHandItem().getEnchantmentLevel(holder) <= 0) return;

            // 複数ブロックのもの(ドアやベッド)を除外する
            if (checkBlockState(state, BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                    || checkBlockState(state, BlockStateProperties.BED_PART, BedPart.FOOT)) return;

            ItemStack stack;
            BlockEntity be = level.getBlockEntity(pos);
            if (be != null) {
                BlockHitResult hitResult = new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false);
                stack = state.getCloneItemStack(hitResult, level, pos, player);
                be.saveToItem(stack, level.registryAccess());
                be.setRemoved();

                level.sendParticles(
                        ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        20,
                        0.3,
                        0.3,
                        0.3,
                        0);
            } else {
                stack = new ItemStack(state.getBlock());
            }

            if (player.isShiftKeyDown()) {
                BlockItemStateProperties properties = BlockItemStateProperties.EMPTY;
                for (Property<?> property : state.getProperties()) {
                    properties = properties.with(property, state);
                }
                stack.set(DataComponents.BLOCK_STATE, properties);

                level.sendParticles(
                        ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        20,
                        0.3,
                        0.3,
                        0.3,
                        0);
            }

            CACHE.put(new Key(level.dimension(), pos), stack);
        });
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void onDrops(BlockDropsEvent event) {
        ItemStack cached = CACHE.remove(new Key(event.getLevel().dimension(), event.getPos()));
        if (cached == null) return;

        event.getDrops().clear();
        event.setDroppedExperience(0);
        event.getDrops()
                .add(new ItemEntity(
                        event.getLevel(),
                        event.getPos().getX() + 0.5,
                        event.getPos().getY() + 0.5,
                        event.getPos().getZ() + 0.5,
                        cached));
    }

    // 何らかの理由でBlockDropsEventが発火しなかった場合の保険(メモリリーク防止)
    @SubscribeEvent
    private static void onTick(ServerTickEvent.Post event) {
        CACHE.clear();
    }

    static <T extends Comparable<T>> boolean checkBlockState(BlockState state, Property<T> property, T value) {
        return state.hasProperty(property) && state.getValue(property) == value;
    }

    record Key(ResourceKey<Level> level, BlockPos pos) {}
}
