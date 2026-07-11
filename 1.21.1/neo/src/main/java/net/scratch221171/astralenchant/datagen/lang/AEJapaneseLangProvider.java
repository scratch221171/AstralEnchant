package net.scratch221171.astralenchant.datagen.lang;

import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ConfigID;

public class AEJapaneseLangProvider extends AELangProvider {

    public AEJapaneseLangProvider(PackOutput output) {
        super(output, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        // アイテム

        addItem(AEItems.ENCHANTMENT_SHARD, "エンチャントの欠片");
        addItem(AEItems.ARCANE_QUARTZ, "アーケインクォーツ");
        addItem(AEItems.GROWN_ARCANE_QUARTZ, "成長したアーケインクォーツ");
        addItem(AEItems.ARCANE_QUARTZ_DUST, "アーケインクォーツの粉");
        addItem(AEItems.ARCANE_QUARTZ_TINY_DUST, "小さいアーケインクォーツの粉");
        addItem(AEItems.LAVAPROOF_ARCANE_QUARTZ, "耐溶岩アーケインクォーツ");
        addItem(AEItems.LUMINITE, "ルミナイト");
        addItem(AEItems.ARCANIUM_INGOT, "アルカニウムインゴット");
        addItem(AEItems.BUDDING_ARCANIUM_INGOT, "芽生えたアルカニウムインゴット");
        addItem(AEItems.ENCHANTMENT_VESSEL, "エンチャントの依代");

        // エンチャント
        addEnchantmentWithDesc(AEEnchantments.NULLIFICATION, "無効化", "様々なダメージ軽減効果を無視し、攻撃が真のダメージを与えるようにします。");
        addEnchantmentWithDesc(AEEnchantments.LAST_STAND, "ラストスタンド", "経験値を消費して死を回避します。");
        addEnchantmentWithDesc(
                AEEnchantments.ESSENCE_OF_ENCHANTMENT,
                "エンチャントの極意",
                "このエンチャント以外のエンチャントのレベルの合計に応じて、アイテムの全ステータスに補正を掛けます。");
        addEnchantmentWithDesc(AEEnchantments.RESILIENCE, "復調", "すべてのアイテムの使用クールダウンを短縮します。");
        addEnchantmentWithDesc(
                AEEnchantments.FEATHER_TOUCH,
                "フェザータッチ",
                "通常採取できないものも含め、ブロックをアイテム化します。ブロックエンティティのデータも保存し、スニーク時はブロックステートも同様に保存します。");
        addEnchantmentWithDesc(AEEnchantments.AFFINITY, "親和", "すべての制限を無視して、あらゆるエンチャントを付与できるようにします。");

        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "オーバーロード", "既存のエンチャントのレベルをオーバーロードの値だけ増加させます。");
        addEnchantmentWithDesc(AEEnchantments.REACTIVE_ARMOR, "反応装甲", "一部のダメージタイプのアーマーおよびエンチャントを貫通する効果を無効化します。");
        addEnchantmentWithDesc(AEEnchantments.CURSE_OF_IGNORANCE, "無知の呪い", "ツールチップを呪われた文字に置き換えます。");
        addEnchantmentWithDesc(AEEnchantments.CURSE_OF_ENCHANTMENT, "エンチャントの呪い", "アイテムのエンチャントを固定し、変更できなくします。");
        addEnchantmentWithDesc(AEEnchantments.ALMIGHTY, "オールマイティ", "ツールを全てのブロックに対して適正にします。破壊不能なブロックを採掘可能にします。");

        // attribute
        addAttributeWithDesc(AEAttributes.COOLDOWN_DURATION, "クールダウン時間", "アイテム使用のクールダウン時間の倍率");

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
        addConfigWithDesc(
                ConfigID.INCLUDE_OVERLOAD_IN_TOTAL,
                "オーバーロードレベルを含める",
                "他のエンチャントに適用されるオーバーロードによるボーナスを総レベルに含めるかどうか。この設定に関係なく、エンチャントの極意自体にはオーバーロードは適用されます。");
        addConfigWithDesc(ConfigID.MULTIPLIER, "レベルごとのモディファイア倍率（％）", "例：1に設定すると、合計20レベルのときモディファイア倍率が+20%になります。");
        addConfigWithDesc(
                ConfigID.EFFECTIVE_FOR_ALL_BLOCKS, "全てのブロックに対して適正", "ガラスなど、適正ツールが存在しないブロックにも採掘速度ボーナスが適用されるかどうか。");
        addConfigWithDesc(ConfigID.UNBREAKABLE_BLOCK_HARDNESS, "破壊不能なブロックの硬さ", "岩盤など破壊不能なブロックを採掘する際に適用される硬さ。");
        addConfigWithDesc("enchantment_toggling", "エンチャントの切り替え", "サーバーの再起動が必要です！");
    }
}
