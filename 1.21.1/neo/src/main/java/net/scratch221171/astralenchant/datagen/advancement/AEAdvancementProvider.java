package net.scratch221171.astralenchant.datagen.advancement;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.registry.AEItems;
import org.jspecify.annotations.NonNull;

public class AEAdvancementProvider extends AdvancementProvider {

    public AEAdvancementProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new AEAdvancementGenerator()));
    }

    private static final class AEAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(
                HolderLookup.@NonNull Provider registries,
                @NonNull Consumer<AdvancementHolder> saver,
                @NonNull ExistingFileHelper existingFileHelper) {
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            new ItemStack(AEItems.ARCANE_QUARTZ.get()),
                            Component.translatable("advancements.astralenchant.root.title"),
                            Component.translatable("advancements.astralenchant.root.description"),
                            ResourceLocation.withDefaultNamespace("textures/block/obsidian.png"),
                            AdvancementType.TASK,
                            false,
                            true,
                            false)
                    .addCriterion("requirement", InventoryChangeTrigger.TriggerInstance.hasItems(AEItems.ARCANE_QUARTZ))
                    .save(saver, ModUtils.loc(ID.ROOT), existingFileHelper);

            createChild(
                    root,
                    ID.ENCHANTMENT_VESSEL,
                    AEItems.ENCHANTMENT_VESSEL,
                    AdvancementType.TASK,
                    false,
                    InventoryChangeTrigger.TriggerInstance.hasItems(AEItems.ENCHANTMENT_VESSEL),
                    saver,
                    existingFileHelper);

            var budding_arcanium_ingot = createChild(
                    root,
                    ID.BUDDING_ARCANIUM_INGOT,
                    AEItems.BUDDING_ARCANIUM_INGOT,
                    AdvancementType.TASK,
                    false,
                    InventoryChangeTrigger.TriggerInstance.hasItems(AEItems.BUDDING_ARCANIUM_INGOT),
                    saver,
                    existingFileHelper);

            createChild(
                    budding_arcanium_ingot,
                    ID.RESONANT_VESSEL,
                    AEItems.RESONANT_VESSEL,
                    AdvancementType.TASK,
                    false,
                    InventoryChangeTrigger.TriggerInstance.hasItems(AEItems.RESONANT_VESSEL),
                    saver,
                    existingFileHelper);

            createChild(
                    root,
                    ID.ENCHANTERS_WORKBENCH,
                    AEBlocks.ENCHANTERS_WORKBENCH,
                    AdvancementType.GOAL,
                    true,
                    InventoryChangeTrigger.TriggerInstance.hasItems(AEBlocks.ENCHANTERS_WORKBENCH),
                    saver,
                    existingFileHelper);
        }
    }

    private static AdvancementHolder createChild(
            AdvancementHolder parent, // 親となる進捗
            String id, // 進捗のID（ファイル名になります）
            ItemLike icon, // アイコンにするアイテム
            AdvancementType type,
            boolean showToast, // 枠の種類 (TASK, CHALLENGE, GOAL)
            Criterion<?> criterion, // 達成条件
            Consumer<AdvancementHolder> saver, // セーバー
            ExistingFileHelper existingFileHelper // ファイルヘルパー
            ) {
        return Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        new ItemStack(icon),
                        Component.translatable("advancements." + Constants.MODID + "." + id + ".title"),
                        Component.translatable("advancements." + Constants.MODID + "." + id + ".description"),
                        null,
                        type,
                        showToast,
                        true,
                        false)
                .addCriterion("requirement", criterion) // 条件を設定
                .save(saver, ResourceLocation.fromNamespaceAndPath(Constants.MODID, id), existingFileHelper);
    }
}
