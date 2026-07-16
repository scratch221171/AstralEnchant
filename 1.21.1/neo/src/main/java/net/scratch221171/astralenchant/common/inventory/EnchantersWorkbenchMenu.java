package net.scratch221171.astralenchant.common.inventory;

import java.util.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.scratch221171.astralenchant.common.component.DisabledEnchantments;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.registry.AEDataComponents;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.registry.AEMenuTypes;
import net.scratch221171.astralenchant.common.tag.AETags;
import net.scratch221171.astralenchant.common.util.AEUtil;
import org.jspecify.annotations.NonNull;

public class EnchantersWorkbenchMenu extends AbstractContainerMenu {

    public static final int FUEL_SLOT = 0;
    public static final int INPUT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    private static final int INV_SLOT_START = 3;
    private static final int USE_ROW_SLOT_END = 39;

    private static final int FUEL_X = 136;
    private static final int INPUT_X = 162;
    private static final int OUTPUT_X = 220;
    private static final int ROW_Y = 37;

    private static final int FUEL_PER_TOGGLE = 1;

    private final ContainerLevelAccess access;
    private final boolean serverSide;
    private final DataSlot cost = DataSlot.standalone();
    private final DataSlot canTakeOutput = DataSlot.standalone();

    public int getCost() {
        return this.cost.get();
    }

    private final Container resultSlots = new ResultContainer();
    private final Container inputSlots = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            EnchantersWorkbenchMenu.this.slotsChanged(this);
        }
    };

    /** 保留中のトグル状態。入力アイテムを置き直す(別アイテムに変わる)とリセットされる。 */
    private final Set<ResourceKey<Enchantment>> pendingToggles = new LinkedHashSet<>();

    private ItemStack lastInputSnapshot = ItemStack.EMPTY;

    /** 出力スロットへの書き込みが自分自身のリスナーを再度呼んでしまう再帰を防ぐガード */
    private boolean updatingResult = false;

    /** クライアント側コンストラクタ(MenuTypeのファクトリから呼ばれる)。 */
    public EnchantersWorkbenchMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    /** サーバー側コンストラクタ(ブロックのMenuProviderから呼ばれる)。 */
    public EnchantersWorkbenchMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(AEMenuTypes.ENCHANTERS_WORKBENCH.get(), containerId);
        this.access = access;
        this.serverSide = access != ContainerLevelAccess.NULL;

        this.addSlot(new Slot(inputSlots, FUEL_SLOT, FUEL_X, ROW_Y) {
            @Override
            public boolean mayPlace(@NonNull ItemStack stack) {
                return stack.is(AETags.Items.GEMS_LUMINITE);
            }
        });
        this.addSlot(new Slot(inputSlots, INPUT_SLOT, INPUT_X, ROW_Y));
        this.addSlot(new Slot(resultSlots, OUTPUT_SLOT, OUTPUT_X, ROW_Y) {
            @Override
            public boolean mayPlace(@NonNull ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(@NonNull Player player) {
                return EnchantersWorkbenchMenu.this.canTakeOutput(player);
            }

            @Override
            public void onTake(@NonNull Player player, @NonNull ItemStack stack) {
                EnchantersWorkbenchMenu.this.onOutputTaken(player);
                super.onTake(player, stack);
            }
        });
        this.addDataSlot(cost);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 108 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 108 + k * 18, 142));
        }
    }

    @Override
    public void slotsChanged(@NonNull Container container) {
        super.slotsChanged(container);
        if (updatingResult) return; // 出力スロットへの書き込みによる再入を無視する
        if (!this.serverSide) return;

        ItemStack input = this.inputSlots.getItem(INPUT_SLOT);
        if (!ItemStack.isSameItemSameComponents(input, lastInputSnapshot)) {
            pendingToggles.clear();
            lastInputSnapshot = input.copy();
        }
        updateResult();
    }

    private void updateResult() {
        ItemStack input = this.inputSlots.getItem(INPUT_SLOT);

        updatingResult = true;
        try {
            if (input.isEmpty() || pendingToggles.isEmpty()) {
                this.resultSlots.setItem(OUTPUT_SLOT, ItemStack.EMPTY);
                return;
            }

            HolderLookup.Provider provider = resolveRegistries();
            if (provider == null) {
                this.resultSlots.setItem(OUTPUT_SLOT, input.copy());
                return;
            }

            this.resultSlots.setItem(OUTPUT_SLOT, applyToggles(input, pendingToggles, provider));
            this.cost.set(pendingToggles.size() * FUEL_PER_TOGGLE);
        } finally {
            updatingResult = false;
        }
    }

    /**
     * 保留中トグルを適用した予測アイテムを生成する。破壊的操作は行わず、コピー上で計算する。
     * エンチャ本(ENCHANTED_BOOK)の場合は STORED_ENCHANTMENTS を、それ以外は ENCHANTMENTS を対象にする。
     */
    public static ItemStack applyToggles(
            ItemStack input, Set<ResourceKey<Enchantment>> toggles, HolderLookup.Provider provider) {
        ItemStack preview = input.copy();
        boolean isBook = preview.is(Items.ENCHANTED_BOOK);
        var component = isBook ? DataComponents.STORED_ENCHANTMENTS : DataComponents.ENCHANTMENTS;

        ItemEnchantments current = preview.getOrDefault(component, ItemEnchantments.EMPTY);
        DisabledEnchantments disabled =
                preview.getOrDefault(AEDataComponents.DISABLED_ENCHANTMENTS.get(), DisabledEnchantments.EMPTY);

        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(current);
        var lookup = provider.lookupOrThrow(Registries.ENCHANTMENT);

        // 【重要・バグ修正】互換性(競合)チェックはここでは一切行わない。
        // pendingToggles は handleToggleRequest 内の resolveConflicts() によって、
        // ここに来る前に既に「AFFINITYが無い限り競合は起きない」状態へ確定調整済みという前提。
        // ここで改めて mutable の途中経過に対して互換性判定を行うと、同一ループ内での処理順序
        // (このキーを処理する時点で、まだ他のキーの分がmutableに反映されていない)に結果が
        // 左右されてしまう(実際に発生していた不具合の原因)。適用は常に機械的に行うだけにする。
        for (ResourceKey<Enchantment> key : toggles) {
            var holderOpt = lookup.get(key);
            if (holderOpt.isEmpty()) continue;
            var holder = holderOpt.get();
            if (holder.is(EnchantmentTags.CURSE)) continue;

            int currentLevel = mutable.getLevel(holder);
            if (currentLevel > 0) {
                mutable.set(holder, 0);
                disabled = disabled.with(key, currentLevel);
            } else {
                var entry = disabled.get(key);
                if (entry.isPresent()) {
                    mutable.set(holder, entry.get().level());
                    disabled = disabled.without(key);
                }
            }
        }

        preview.set(component, mutable.toImmutable());
        if (disabled.isEmpty()) {
            preview.remove(AEDataComponents.DISABLED_ENCHANTMENTS.get());
        } else {
            preview.set(AEDataComponents.DISABLED_ENCHANTMENTS.get(), disabled);
        }
        return preview;
    }

    public void handleToggleRequest(Player player, ResourceKey<Enchantment> key) {
        if (!this.serverSide) return;

        ItemStack input = this.inputSlots.getItem(INPUT_SLOT);
        if (input.isEmpty()) return;

        HolderLookup.Provider provider = resolveRegistries();
        if (provider == null) return;

        var holderOpt = provider.lookupOrThrow(Registries.ENCHANTMENT).get(key);
        if (holderOpt.isEmpty() || holderOpt.get().is(EnchantmentTags.CURSE)) return;

        // affinityがpending中でも正しく動作させるために、copyしてapply
        boolean present = getEnchantmentList(applyToggles(input, pendingToggles, provider), provider).stream()
                .anyMatch(e -> e.id().equals(key));
        if (!present) return;

        if (!pendingToggles.remove(key)) {
            pendingToggles.add(key);
        }

        // 【追加】AFFINITYが無い状態での競合を解消する。負けた側は pendingToggles 自体を書き換えて
        // OFF状態を確定させる(これにより、後の無関係な操作で勝手に復活することがなくなる)。
        resolveConflicts(input, provider);

        updateResult();
    }

    private void resolveConflicts(ItemStack input, HolderLookup.Provider provider) {
        boolean isBook = input.is(Items.ENCHANTED_BOOK);
        var component = isBook ? DataComponents.STORED_ENCHANTMENTS : DataComponents.ENCHANTMENTS;
        ItemEnchantments original = input.getOrDefault(component, ItemEnchantments.EMPTY);
        var lookup = provider.lookupOrThrow(Registries.ENCHANTMENT);

        boolean affinityOriginallyEnabled = AEUtil.getEnchantmentLevel(AEEnchantments.AFFINITY, original) > 0;
        boolean affinityDesired = affinityOriginallyEnabled ^ pendingToggles.contains(AEEnchantments.AFFINITY);
        if (affinityDesired) return; // AFFINITY有効なら制約なし、何もしない

        // 優先度付き候補リストを構築する
        List<ResourceKey<Enchantment>> candidates = new ArrayList<>();

        for (var entry : original.entrySet()) {
            entry.getKey().unwrapKey().ifPresent(key -> {
                if (key.equals(AEEnchantments.AFFINITY)) return;
                if (entry.getKey().is(EnchantmentTags.CURSE)) return;
                if (!pendingToggles.contains(key)) {
                    candidates.add(key); // 元々有効・未操作 = 現在も有効
                }
            });
        }
        for (ResourceKey<Enchantment> key : pendingToggles) {
            if (key.equals(AEEnchantments.AFFINITY)) continue;
            if (AEUtil.getEnchantmentLevel(key, original) > 0) continue; // 元々有効なものは上のループで処理済み
            var holderOpt = lookup.get(key);
            if (holderOpt.isEmpty() || holderOpt.get().is(EnchantmentTags.CURSE)) continue;
            candidates.add(key); // 元々無効 → クリックでON化されたもの
        }

        Set<Holder<Enchantment>> keptHolders = new HashSet<>();
        List<ResourceKey<Enchantment>> forcedOff = new ArrayList<>();
        for (ResourceKey<Enchantment> key : candidates) {
            var holderOpt = lookup.get(key);
            if (holderOpt.isEmpty()) continue;
            var holder = holderOpt.get();
            if (EnchantmentHelper.isEnchantmentCompatible(keptHolders, holder)) {
                keptHolders.add(holder);
            } else {
                forcedOff.add(key);
            }
        }

        for (ResourceKey<Enchantment> key : forcedOff) {
            if (AEUtil.getEnchantmentLevel(key, original) > 0) {
                pendingToggles.add(key); // 元ONだったものを強制的にOFF側へ倒す
            } else {
                pendingToggles.remove(key); // 元OFF(ユーザーがONにした)を強制的にOFF側へ戻す
            }
        }
    }

    private void onOutputTaken(Player player) {
        ItemStack input = this.inputSlots.getItem(INPUT_SLOT);
        if (input.isEmpty()) return;

        int cost = getCost();
        ItemStack fuel = this.inputSlots.getItem(FUEL_SLOT);
        if (!player.hasInfiniteMaterials()) {
            if (fuel.getCount() < cost) {
                return;
            }
            fuel.shrink(cost);
        }

        this.access.execute(
                (level, pos) -> level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F));

        this.inputSlots.setItem(INPUT_SLOT, ItemStack.EMPTY);
        this.pendingToggles.clear();
        this.lastInputSnapshot = ItemStack.EMPTY;
    }

    /** GUI描画用に、現在のエンチャント一覧(有効/無効/呪い判定込み)を取得する。クライアント側からも呼ばれる。 */
    public static List<DisplayEntry> getEnchantmentList(ItemStack stack, HolderLookup.Provider provider) {
        List<DisplayEntry> result = new ArrayList<>();
        if (stack.isEmpty()) return result;

        boolean isBook = stack.is(Items.ENCHANTED_BOOK);
        var component = isBook ? DataComponents.STORED_ENCHANTMENTS : DataComponents.ENCHANTMENTS;
        ItemEnchantments current = stack.getTagEnchantments();
        DisabledEnchantments disabled =
                stack.getOrDefault(AEDataComponents.DISABLED_ENCHANTMENTS.get(), DisabledEnchantments.EMPTY);
        var lookup = provider.lookupOrThrow(Registries.ENCHANTMENT);

        boolean affinityActive = lookup.get(AEEnchantments.AFFINITY)
                .map(h -> current.getLevel(h) > 0)
                .orElse(false);

        for (var entry : current.entrySet()) {
            var holder = entry.getKey();
            boolean curse = holder.is(EnchantmentTags.CURSE);
            holder.unwrapKey()
                    .ifPresent(key -> result.add(new DisplayEntry(key, entry.getIntValue(), true, curse, false)));
        }
        for (var e : disabled.entries()) {
            lookup.get(e.id()).ifPresent(holder -> {
                boolean curse = holder.is(EnchantmentTags.CURSE);
                boolean blocked = !curse && !affinityActive && !isCompatibleWithCurrent(holder, current);
                result.add(new DisplayEntry(e.id(), e.level(), false, curse, blocked));
            });
        }
        result.sort(Comparator.comparing(e -> e.id.location().getPath()));
        return result;
    }

    private HolderLookup.Provider resolveRegistries() {
        final HolderLookup.Provider[] holder = new HolderLookup.Provider[1];
        this.access.execute((level, pos) -> holder[0] = level.registryAccess());
        return holder[0];
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return stillValid(this.access, player, AEBlocks.ENCHANTERS_WORKBENCH.get());
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            result = stackInSlot.copy();

            if (index == OUTPUT_SLOT) {
                if (!canTakeOutput(player)) {
                    return ItemStack.EMPTY;
                }
                if (!this.moveItemStackTo(stackInSlot, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, result);
                this.onOutputTaken(player);
            } else if (index == INPUT_SLOT || index == FUEL_SLOT) {
                if (!this.moveItemStackTo(stackInSlot, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (stackInSlot.is(AETags.Items.GEMS_LUMINITE)) {
                if (!this.moveItemStackTo(stackInSlot, FUEL_SLOT, FUEL_SLOT + 1, false)
                        && !this.moveItemStackTo(stackInSlot, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stackInSlot, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return result;
    }

    @Override
    public void removed(@NonNull Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> this.clearContainer(player, this.inputSlots));
    }

    /** 出力スロットから取り出し可能か(=燃料が足りているか)を判定する */
    private boolean canTakeOutput(Player player) {
        if (this.cost.get() <= 0) return false;
        if (player.hasInfiniteMaterials()) return true;
        if (this.inputSlots.getItem(INPUT_SLOT).isEmpty()) return false;
        return this.inputSlots.getItem(FUEL_SLOT).getCount() >= this.cost.get();
    }

    /** AFFINITYが無い状態で、holderを現在の有効エンチャント群に追加しても競合しないかを判定する。 */
    private static boolean isCompatibleWithCurrent(Holder<Enchantment> holder, ItemEnchantments current) {
        for (var entry : current.entrySet()) {
            Holder<Enchantment> existing = entry.getKey();
            if (existing.equals(holder)) continue;
            if (!EnchantmentHelper.isEnchantmentCompatible(current.keySet(), holder)) return false;
        }
        return true;
    }

    public record DisplayEntry(
            ResourceKey<Enchantment> id, int level, boolean enabled, boolean curse, boolean blockedByConflict) {}
}
