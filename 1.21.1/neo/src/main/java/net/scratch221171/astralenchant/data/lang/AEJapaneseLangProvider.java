package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ServerConfig;

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
        addEnchantWithDesc(AEEnchantments.NULLIFICATION, "無効化", "様々なダメージ軽減効果を無視し、攻撃が真のダメージを与えるようになります。");
        addEnchantWithDesc(AEEnchantments.LAST_STAND, "ラストスタンド", "経験値を消費して死を回避します。");
        addEnchantWithDesc(
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                "エンチャントの極意",
                "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全ステータスに補正がかかります。");
        addEnchantWithDesc(AEEnchantments.OVERLOAD, "オーバーロード", "既存のエンチャントのレベルがオーバーロードの値だけ上昇します。");
        addEnchantWithDesc(AEEnchantments.REACTIVE_ARMOR, "反応装甲", "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");

        // 設定
        add(Constants.MODID + ".configuration.enchantment_settings", "エンチャントの設定");
        addConfigWithDesc(
                ServerConfig.EnchantmentSettings.LastStand.IGNORE_BYPASSES_INVULNERABILITY_TAG,
                "無敵貫通を無視",
                "オフの場合、無敵貫通タグ（BYPASSES_INVULNERABILITY）を持つダメージ（/killや奈落など）ではラストスタンドは発動しません。");
        addConfigWithDesc(
                ServerConfig.EnchantmentSettings.LastStand.BASE_COST,
                "レベル消費基本値",
                "レベルnのとき、消費されるレベルは以下の計算式で表されます：(現在の経験値) * (消費割合) / √n + (消費基本値) / n");
        addConfigWithDesc(
                ServerConfig.EnchantmentSettings.LastStand.COST_RATE,
                "レベル消費割合",
                "レベルnのとき、消費されるレベルは以下の計算式で表されます：(現在の経験値) * (消費割合) / √n + (消費基本値) / n");
    }
}
