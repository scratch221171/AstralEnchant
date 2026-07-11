package net.scratch221171.astralenchant.datagen.model;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.registry.AEItems;

public class AEItemModelProvider extends ItemModelProvider {
    public AEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(AEItems.ENCHANTMENT_SHARD.get());
        basicItem(AEItems.ARCANE_QUARTZ.get());
        basicItem(AEItems.GROWN_ARCANE_QUARTZ.get());
        basicItem(AEItems.ARCANE_QUARTZ_DUST.get());
        basicItem(AEItems.ARCANE_QUARTZ_TINY_DUST.get());
        basicItem(AEItems.LAVAPROOF_ARCANE_QUARTZ.get());
        basicItem(AEItems.LUMINITE.get());
        basicItem(AEItems.ARCANIUM_INGOT.get());
        basicItem(AEItems.BUDDING_ARCANIUM_INGOT.get());
        basicItem(AEItems.ENCHANTMENT_VESSEL.get());
    }
}
