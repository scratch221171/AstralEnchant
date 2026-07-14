package net.scratch221171.astralenchant.datagen.recipe;

import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.registry.AEItems;

public class AEMixingRecipeGen extends MixingRecipeGen {
    public AEMixingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Constants.MODID);
    }

    GeneratedRecipe LAVAPROOF_ARCANE_QUARTZ = create(ID.LAVAPROOF_ARCANE_QUARTZ, b -> b.duration(250)
            .require(AEItems.LAVAPROOF_ARCANE_QUARTZ)
            .require(Tags.Fluids.LAVA, 250)
            .output(AEItems.LUMINITE));
}
