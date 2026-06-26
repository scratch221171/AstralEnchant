package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.item.AEItems;

public class AEJapaneseLangProvider extends AELangProvider {

    public AEJapaneseLangProvider(PackOutput output) {
        super(output, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        // アイテム
        add(AEItems.ENCHANTMENT_SHARD.getKey().location().toLanguageKey("item"), "エンチャントの欠片");
        add(AEItems.ARCANE_QUARTZ.getKey().location().toLanguageKey("item"), "アーケインクォーツ");
        add(AEItems.ARCANIUM_INGOT.getKey().location().toLanguageKey("item"), "アルカニウムインゴット");
        add(AEItems.BUDDING_ARCANIUM_INGOT.getKey().location().toLanguageKey("item"), "芽生えたアルカニウムインゴット");

        // エンチャント
        addEnchantWithDesc(
                AEEnchantments.NULLIFICATION,
                "無効化",
                "様々なダメージ軽減が無効化され、攻撃が真のダメージを与えるようになります。");
        addEnchantWithDesc(
                AEEnchantments.REACTIVE_ARMOR,
                "反応装甲",
                "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");
    }
}
