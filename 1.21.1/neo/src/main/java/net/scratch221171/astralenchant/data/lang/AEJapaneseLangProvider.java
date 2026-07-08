package net.scratch221171.astralenchant.data.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ConfigID;

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
        addEnchantmentWithDesc(AEEnchantments.NULLIFICATION, "無効化", "様々なダメージ軽減効果を無視し、攻撃が真のダメージを与えるようにします。");
        addEnchantmentWithDesc(AEEnchantments.LAST_STAND, "ラストスタンド", "経験値を消費して死を回避します。");
        addEnchantmentWithDesc(
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                "エンチャントの極意",
                "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全ステータスに補正を掛けます。");
        addEnchantmentWithDesc(
                AEEnchantments.FEATHER_TOUCH,
                "フェザータッチ",
                "通常採取できないものも含め、ブロックをアイテム化します。ブロックエンティティのデータも保存し、スニーク時はブロックステートも同様に保存します。");
        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "オーバーロード", "既存のエンチャントのレベルをオーバーロードの値だけ増加させます。");
        addEnchantmentWithDesc(AEEnchantments.REACTIVE_ARMOR, "反応装甲", "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");
        addEnchantmentWithDesc(AEEnchantments.ALMIGHTY, "オールマイティ", "ツールを全てのブロックに対して適正にします。破壊不能なブロックを採掘可能にします。");

        // 設定
        addConfig("enchantment_settings", "エンチャントの設定");
        addConfigWithDesc(
                ConfigID.IGNORE_BYPASSES_INVULNERABILITY_TAG,
                "無敵貫通を無視",
                "オフの場合、無敵貫通タグ（BYPASSES_INVULNERABILITY）を持つダメージ（/killや奈落など）ではラストスタンドは発動しません。");
        addConfigWithDesc(
                ConfigID.BASE_COST, "レベル消費基本値", "レベルnのとき、消費されるレベルは以下の計算式で表されます：(現在の経験値) * (消費割合) / √n + (消費基本値) / n");
        addConfigWithDesc(
                ConfigID.COST_RATE, "レベル消費割合", "レベルnのとき、消費されるレベルは以下の計算式で表されます：(現在の経験値) * (消費割合) / √n + (消費基本値) / n");

        addConfigWithDesc("enchantment_toggling", "エンチャントの切り替え", "注意：変更を適用するにはワールドを再起動する必要があります！");
    }
}
