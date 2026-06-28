package net.scratch221171.astralenchant.common.tag;

import com.google.gson.JsonElement;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.damagesource.DamageType;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ModUtils;

public class TagGroupLoader implements ResourceManagerReloadListener {

    public static final TagGroupLoader INSTANCE = new TagGroupLoader();

    private static final List<TagKey<DamageType>> nullificationLoadedTags = new ArrayList<>();
    private static final List<TagKey<DamageType>> reactiveArmorLoadedTags = new ArrayList<>();

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        nullificationLoadedTags.clear();
        reactiveArmorLoadedTags.clear();

        var nullificationFileId = ModUtils.loc("damage_type_tag_group/nullification_tags.json");
        var reactiveArmorFileId = ModUtils.loc("damage_type_tag_group/reactive_armor_tags.json");

        manager.getResource(nullificationFileId).ifPresent(resource -> loadTagsInto(resource, nullificationLoadedTags));

        manager.getResource(reactiveArmorFileId).ifPresent(resource -> loadTagsInto(resource, reactiveArmorLoadedTags));
    }

    public static List<TagKey<DamageType>> getNullificationTags() {
        return Collections.unmodifiableList(nullificationLoadedTags);
    }

    public static List<TagKey<DamageType>> getReactiveArmorTags() {
        return Collections.unmodifiableList(reactiveArmorLoadedTags);
    }

    private static void loadTagsInto(Resource resource, List<TagKey<DamageType>> loadedTags) {
        try (InputStream stream = resource.open()) {
            var json = GsonHelper.parse(new InputStreamReader(stream));
            var tags = json.getAsJsonArray("tags");

            for (JsonElement element : tags) {
                var tagId = ResourceLocation.tryParse(element.getAsString());
                if (tagId != null) {
                    loadedTags.add(TagKey.create(Registries.DAMAGE_TYPE, tagId));
                }
            }
        } catch (Exception e) {
            Constants.LOGGER.error("Error while loading tags from damage_type_tag_group!", e);
        }
    }
}
