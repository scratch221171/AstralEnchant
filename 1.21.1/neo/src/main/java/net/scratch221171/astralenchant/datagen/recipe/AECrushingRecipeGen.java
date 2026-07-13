package net.scratch221171.astralenchant.datagen.recipe;

import com.simibubi.create.api.data.recipe.CrushingRecipeGen;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.tag.AETags;

public class AECrushingRecipeGen extends CrushingRecipeGen {
    public AECrushingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Constants.MODID);
    }

    GeneratedRecipe
            ARCANE_QUARTZ =
                    create(ID.ARCANE_QUARTZ, b -> b.duration(250)
                            .require(AETags.Items.GEMS_ARCANE_QUARTZ)
                            .output(AEItems.ARCANE_QUARTZ_TINY_DUST)),
            GROWN_ARCANE_QUARTZ =
                    create(ID.GROWN_ARCANE_QUARTZ, b -> b.duration(250)
                            .require(AEItems.GROWN_ARCANE_QUARTZ)
                            .output(AEItems.ARCANE_QUARTZ_DUST));
}
