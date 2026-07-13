package net.scratch221171.astralenchant.datagen.model;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
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
        AEItems.REGISTER.getEntries().forEach(entry -> {
            if (!(entry.get() instanceof BlockItem)) basicItem(entry.get());
        });
    }
}
