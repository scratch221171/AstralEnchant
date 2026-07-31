package net.scratch221171.astralenchant.datagen.util;

import com.google.gson.JsonObject;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;

/** entryへのリンクがうまく動いてないようなので独自実装しちゃうもんね */
public class LinkableBookEntryModel extends BookEntryModel {
    private boolean isEntryLink = false;

    protected LinkableBookEntryModel(ResourceLocation id, String name) {
        super(id, name);
    }

    public static LinkableBookEntryModel create(ResourceLocation id, String name) {
        return new LinkableBookEntryModel(id, name);
    }

    @Override
    public BookEntryModel withEntryToOpen(ResourceLocation entryToOpen) {
        this.isEntryLink = true;
        return super.withEntryToOpen(entryToOpen);
    }

    @Override
    public JsonObject toJson(HolderLookup.Provider provider) {
        var json = super.toJson(provider);
        if (this.isEntryLink) {
            json.addProperty("type", "modonomicon:entry_link");
        }
        return json;
    }
}
