package net.scratch221171.astralenchant.datagen.util;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.Vec2;

public abstract class AEEntryProvider extends EntryProvider {

    protected static final String JA_JP = "ja_jp";
    private String existingNameKey;
    private String existingDescriptionKey;

    public AEEntryProvider(CategoryProvider parent) {
        super(parent);
    }

    /** en_us と ja_jp に同時に登録する */
    protected void add(String key, String enUs, String jaJp) {
        add(key, enUs);
        add(lang(JA_JP), key, jaJp);
    }

    /** MessageFormatパターン版 */
    protected void add(String key, String enUsPattern, String jaJpPattern, Object... args) {
        add(key, format(enUsPattern, args), format(jaJpPattern, args));
    }

    /** ショートハンド：キーを自動採番して登録し、そのままwithTitle()等に渡せるキーを返す */
    protected String pageTitle(String enUs, String jaJp) {
        String key = context().pageTitle();
        add(key, enUs, jaJp);
        return key;
    }

    protected String pageText(String enUs, String jaJp) {
        String key = context().pageText();
        add(key, enUs, jaJp);
        return key;
    }

    protected String pageText(String enUs, String jaJp, Object... args) {
        String key = context().pageText();
        add(key, enUs, jaJp, args);
        return key;
    }

    @Override
    protected String entryDescription() {
        return "";
    }

    protected String entryNameJa() {
        return entryName();
    }
    ;

    protected String entryDescJa() {
        return entryDescription();
    }

    /** 既存の翻訳キーをエントリ名として再利用する。wrappedAdditionalSetup()内で呼んでください。 */
    protected void useExistingNameKey(String key) {
        existingNameKey = key;
    }

    protected void useExistingDescKey(String key) {
        existingDescriptionKey = key;
    }

    @Override
    protected final BookEntryModel additionalSetup(BookEntryModel entry) {
        var result = wrappedAdditionalSetup(entry);

        if (existingNameKey != null) {
            result.withName(existingNameKey);
        } else if (entryName().isEmpty()) {
            result.withName("");
        }
        if (existingDescriptionKey != null) {
            result.withDescription(existingDescriptionKey);
        } else if (entryDescription().isEmpty()) {
            result.withDescription("");
        }

        return result;
    }

    /** additionalSetup() の代わり (en/ja自動処理を上書きしないようfinalに) */
    protected BookEntryModel wrappedAdditionalSetup(BookEntryModel entry) {
        return entry;
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    protected String itemLink(ItemLike item, String components) {
        return itemLink("", item, components);
    }

    protected String itemLink(String text, ItemLike item, String components) {
        var rl = BuiltInRegistries.ITEM.getKey(item.asItem());
        return format("[{0}](item://{1}[{2}])", text, rl, components);
    }

    @Override
    public BookEntryModel generate(Vec2 location) {
        context().entry(entryId());

        var entry = LinkableBookEntryModel.create(
                        modLoc(context().categoryId() + "/" + context().entryId()),
                        context().entryName())
                .withDescription(context().entryDescription());

        if (!entryName().isEmpty()) add(context().entryName(), entryName());
        if (!entryDescription().isEmpty()) add(context().entryDescription(), entryDescription());
        if (!entryNameJa().isEmpty()) add(lang(JA_JP), context().entryName(), entryNameJa());
        if (!entryDescJa().isEmpty()) add(lang(JA_JP), context().entryDescription(), entryDescJa());

        entry.withIcon(entryIcon());
        entry.withLocation(location);
        entry.withEntryBackground(entryBackground());

        this.entry = additionalSetup(entry);

        generatePages();

        parent.add(entry);
        return entry;
    }
}
