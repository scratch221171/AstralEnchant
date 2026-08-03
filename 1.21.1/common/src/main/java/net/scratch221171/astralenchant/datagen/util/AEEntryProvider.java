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
        this.add(key, enUs);
        this.add(this.lang(JA_JP), key, jaJp);
    }

    /** MessageFormatパターン版 */
    protected void add(String key, String enUsPattern, String jaJpPattern, Object... args) {
        this.add(key, this.format(enUsPattern, args), this.format(jaJpPattern, args));
    }

    /** ショートハンド：キーを自動採番して登録し、そのままwithTitle()等に渡せるキーを返す */
    protected String pageTitle(String enUs, String jaJp) {
        String key = this.context().pageTitle();
        this.add(key, enUs, jaJp);
        return key;
    }

    protected String pageText(String enUs, String jaJp) {
        String key = this.context().pageText();
        this.add(key, enUs, jaJp);
        return key;
    }

    protected String pageText(String enUs, String jaJp, Object... args) {
        String key = this.context().pageText();
        this.add(key, enUs, jaJp, args);
        return key;
    }

    /** entryName/entryDescription の日本語版。空文字を返せばスキップされます */
    protected String entryNameJa() {
        return "";
    }

    protected String entryDescJa() {
        return "";
    }

    /** 既存の翻訳キーをエントリ名として再利用する。wrappedAdditionalSetup()内で呼んでください。 */
    protected void useExistingNameKey(String key) {
        this.existingNameKey = key;
    }

    protected void useExistingDescKey(String key) {
        this.existingDescriptionKey = key;
    }

    @Override
    protected final BookEntryModel additionalSetup(BookEntryModel entry) {
        if (!entryNameJa().isEmpty()) this.add(this.lang(JA_JP), this.context().entryName(), this.entryNameJa());
        if (!entryDescJa().isEmpty()) this.add(this.lang(JA_JP), this.context().entryDescription(), entryDescJa());

        var result = this.wrappedAdditionalSetup(entry);

        if (this.existingNameKey != null) {
            result.withName(this.existingNameKey);
        }
        if (this.existingDescriptionKey != null) {
            result.withDescription(this.existingDescriptionKey);
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
        return this.format("[{0}](item://{1}[{2}])", text, rl, components);
    }

    @Override
    public BookEntryModel generate(Vec2 location) {
        this.context().entry(this.entryId());

        var entry = LinkableBookEntryModel.create( // ← ここだけ変更
                        this.modLoc(this.context().categoryId() + "/"
                                + this.context().entryId()),
                        this.context().entryName())
                .withDescription(this.context().entryDescription());

        this.add(this.context().entryName(), this.entryName());
        this.add(this.context().entryDescription(), this.entryDescription());
        entry.withIcon(this.entryIcon());
        entry.withLocation(location);
        entry.withEntryBackground(this.entryBackground());

        this.entry = this.additionalSetup(entry);

        this.generatePages();

        this.parent.add(this.entry);
        return this.entry;
    }
}
