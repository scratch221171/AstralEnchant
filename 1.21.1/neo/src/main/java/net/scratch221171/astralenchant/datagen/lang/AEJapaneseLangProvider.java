package net.scratch221171.astralenchant.datagen.lang;

import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import net.minecraft.data.PackOutput;
import net.scratch221171.astralenchant.Constants;
import net.scratch221171.astralenchant.ID;
import net.scratch221171.astralenchant.common.init.AEEnchantments;
import net.scratch221171.astralenchant.common.registry.AEAttributes;
import net.scratch221171.astralenchant.common.registry.AEBlocks;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.config.ConfigID;

public class AEJapaneseLangProvider extends AELangProvider implements ModonomiconLanguageProvider {

    public AEJapaneseLangProvider(PackOutput output) {
        super(output, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        add("modmenu.descriptionTranslation." + Constants.MODID, "エンチャントであなたを、驚かせたい");

        // アイテム
        addItem(AEItems.ENCHANTMENT_SHARD, "エンチャントの欠片");
        addItemWithDesc(AEItems.ARCANE_QUARTZ, "アーケインクォーツ", "おそらくクォーツではない");
        addItemWithDesc(AEItems.GROWN_ARCANE_QUARTZ, "成長したアーケインクォーツ", "正確に言えばクォーツではない");
        addItemWithDesc(AEItems.RESONANT_ARCANE_QUARTZ, "残響のアーケインクォーツ", "確実にクォーツではない");
        addItem(AEItems.ARCANE_QUARTZ_DUST, "アーケインクォーツダスト");
        addItem(AEItems.ARCANE_QUARTZ_TINY_DUST, "小さいアーケインクォーツダスト");
        addItemWithDesc(AEItems.LAVAPROOF_ARCANE_QUARTZ, "耐熱処理済みアーケインクォーツ", "うわぁ！ベトベトだぁ！");
        addItemWithDesc(AEItems.LUMINITE, "ルミナイト", "なんという輝き！");
        addItem(AEItems.ARCANIUM_INGOT, "アルカニウムインゴット");
        addItem(AEItems.BUDDING_ARCANIUM_INGOT, "芽生えたアルカニウムインゴット");
        addItem(AEItems.ENCHANTMENT_VESSEL, "エンチャントの依代");
        addItem(AEItems.RESONANT_VESSEL, "残響の依代");
        addItem(AEItems.ROTTEN_BONE, "朽ちた骨");
        addItemWithDesc(AEItems.ALLAY_WING, "アレイの羽", "なんと非人道的な！");

        addItem(AEItems.ARCANIUM_HELMET, "アルカニウムのヘルメット");
        addItem(AEItems.ARCANIUM_CHESTPLATE, "アルカニウムのチェストプレート");
        addItem(AEItems.ARCANIUM_LEGGINGS, "アルカニウムのレギンス");
        addItem(AEItems.ARCANIUM_BOOTS, "アルカニウムのブーツ");
        add("armorMaterial.astralenchant.arcanium.desc", "アイテムが壊れると、付いていたエンチャントが本としてドロップします。");

        // ブロック
        addBlock(AEBlocks.ARCANIUM_BLOCK, "アルカニウムブロック");
        addBlock(AEBlocks.ENCHANTERS_WORKBENCH, "エンチャンターのワークベンチ");
        add("container." + Constants.MODID + ".enchanters_workbench", "エンチャントの切り替え");
        add("gui.astralenchant.enchanters_workbench.locked_curse", "呪いは切り替えられません！");
        add("gui.astralenchant.enchanters_workbench.locked_conflict", "他のエンチャントと競合しているため有効化できません！");
        add("container.enchanters_workbench.cost", "エンチャント変更コスト：%1$s");

        // エンチャント
        addEnchantmentWithDesc(AEEnchantments.NULLIFICATION, "帰無", "攻撃が真のダメージを与えるようにします。");
        addEnchantmentWithDesc(AEEnchantments.LAST_STAND, "ラストスタンド", "経験値を消費して死を回避します。");
        addEnchantmentWithDesc(AEEnchantments.ESSENCE_OF_ENCHANTMENT, "エンチャントの極意", "エンチャントの合計レベルのに応じてステータスを上昇させます。");
        addEnchantmentWithDesc(AEEnchantments.RESILIENCE, "復調", "アイテムの使用クールダウンを短縮します。");
        addEnchantmentWithDesc(AEEnchantments.FEATHER_TOUCH, "フェザータッチ", "様々なデータを保存しながら、あらゆるブロックを採取できるようにします。");
        addEnchantmentWithDesc(AEEnchantments.AFFINITY, "親和", "すべての制限を無視して、あらゆるエンチャントを付与できるようにします。");
        addEnchantmentWithDesc(AEEnchantments.ENDLESS_APPETITE, "果てしない食欲", "満腹度および隠し満腹度の上限を引き上げます。");
        addEnchantmentWithDesc(AEEnchantments.DEXTERITY, "器用", "アイテム使用による速度低下を防ぎます。");
        addEnchantmentWithDesc(AEEnchantments.OVERLOAD, "オーバーロード", "他のエンチャントのレベルを増加させます。");
        addEnchantmentWithDesc(AEEnchantments.EXPANSE, "拡張", "キュリオスロットの数を増加させます。");
        addEnchantmentWithDesc(AEEnchantments.REACTIVE_ARMOR, "反応装甲", "全てのダメージが防具およびエンチャントで軽減できるようにします。");
        addEnchantmentWithDesc(AEEnchantments.MYSTIC_REMNANTS, "神秘の残滓", "一部のモブに特殊ドロップを追加します。");
        addEnchantmentWithDesc(AEEnchantments.CURSE_OF_IGNORANCE, "無知の呪い", "ツールチップを呪われた文字に置き換えます。");
        addEnchantmentWithDesc(AEEnchantments.CURSE_OF_ENCHANTMENT, "エンチャントの呪い", "アイテムのエンチャントを変更できなくします。");
        addEnchantmentWithDesc(AEEnchantments.DISTORTION, "歪曲", "外れた攻撃の判定を近くの敵へが歪めます。");
        addEnchantmentWithDesc(AEEnchantments.ALMIGHTY, "オールマイティ", "ツールの採掘レベルを増加させ、全てのブロックに対して適正にします。");

        // attribute
        addAttributeWithDesc(AEAttributes.COOLDOWN_DURATION, "クールダウン時間", "アイテム使用のクールダウン時間の倍率");
        addAttributeWithDesc(AEAttributes.FOOD_LEVEL_CAP_MULTIPLIER, "満腹度上限", "満腹度の上限の倍率");
        addAttributeWithDesc(AEAttributes.SATURATION_LEVEL_CAP_MULTIPLIER, "隠し満腹度上限", "隠し満腹度の上限の倍率");

        // データコンポーネント
        add("astralenchant.item.disabled_enchantments", "無効化されたエンチャント: %s個");

        // クリエタブ
        add("itemGroup.astralenchant.main", "Astral Enchant");
        add("itemGroup.astralenchant.enchantment", "Astral Enchant - エンチャント");

        // 進捗
        addAdvancementWithDesc(ID.ROOT, "Astral Enchant", "あなたも今日からエンチャンター！");
        addAdvancementWithDesc(ID.ENCHANTMENT_VESSEL, "奇妙な魔導書", "エンチャントの依代を手に入れる。素晴らしいエンチャントを作ろう！");
        addAdvancementWithDesc(ID.BUDDING_ARCANIUM_INGOT, "成長する結晶", "芽生えたアメジストブロックを回収し、アルカニウムインゴットに埋め込む。");
        addAdvancementWithDesc(ID.RESONANT_VESSEL, "神秘的な魔導書", "残響の依代を手に入れる。より素晴らしいエンチャントを作ろう！");
        addAdvancementWithDesc(ID.ENCHANTERS_WORKBENCH, "こいつを待ってたぜ", "エンチャンターのワークベンチを作る。自由に切り替えよう！");

        // 設定
        addConfig("enchantment_settings", "エンチャントの設定");
        addConfigWithDesc(ConfigID.EXTRA_TAG, "適用されるダメージタグ", "攻撃に適用されるタグを管理します。無効な値が存在する場合はデフォルト値が使用されます。");
        addConfigWithDesc(
                ConfigID.IGNORE_BYPASSES_INVULNERABILITY_TAG,
                "無敵貫通を無視",
                "オフの場合、無敵貫通タグ（BYPASSES_INVULNERABILITY）を持つダメージ（/killや奈落など）ではラストスタンドは発動しません。");
        addConfigWithDesc(
                ConfigID.BASE_COST, "レベル消費基本値", "レベルnのとき、消費されるレベルは以下の計算式で表されます：(現在の経験値) * (消費割合) / √n + (消費基本値) / n");
        addConfigWithDesc(
                ConfigID.COST_RATE, "レベル消費割合", "レベルnのとき、消費されるレベルは以下の計算式で表されます：(現在の経験値) * (消費割合) / √n + (消費基本値) / n");
        addConfigWithDesc(ConfigID.MAX_COST, "消費上限", "上二つの設定にかかわらず、これ以上の経験値は消費されません。");
        addConfigWithDesc(ConfigID.RECOVER_HEALTH, "蘇生後の体力", "効果が発動し、プレイヤーが蘇生されたときの体力。0だと次tickに再度死亡します。");
        addConfigWithDesc(
                ConfigID.INCLUDE_OVERLOAD_IN_TOTAL,
                "オーバーロードレベルを含める",
                "他のエンチャントに適用されるオーバーロードによるボーナスを総レベルに含めるかどうか。この設定に関係なく、エンチャントの極意自体にはオーバーロードは適用されます。");
        addConfigWithDesc(ConfigID.MULTIPLIER, "レベルごとのモディファイア倍率（％）", "例：1に設定すると、合計20レベルのときモディファイア倍率が+20%になります。");
        addConfigWithDesc(ConfigID.MAX_TOTAL_LEVEL, "レベル合計の上限", "大量にエンチャントを乗せることでステータスを際限なく増やさせないための上限。");
        addConfigWithDesc(ConfigID.CURIOS_COMPAT, "Curiosアイテム互換性", "Curiosアイテムのモディファイアにもボーナスが適用されます。");
        addConfigWithDesc(ConfigID.SAVE_BLOCK_ENTITY, "ブロックエンティティを保存", "チェストのようなブロックエンティティの中身を含めてアイテム化します。");
        addConfigWithDesc(ConfigID.SAVE_BLOCK_STATE, "ブロック状態を保存", "向きなどのブロックの状態を含めてアイテム化します。");
        addConfigWithDesc(ConfigID.STACKABLE, "重複可能", "入れ子状態にすることでスロットを際限なく増やせます。");
        addConfigWithDesc(ConfigID.DISABLED_TAG, "消去されるダメージタグ", "地震への攻撃から消去されるタグを管理します。無効な値が存在する場合はデフォルト値が使用されます。");
        addConfigWithDesc(ConfigID.HIDE_NAME, "アイテムの名前を隠す", "オフにすると、一部のツールチップ装飾modとの互換性が向上する場合があります。");
        addConfigWithDesc(
                ConfigID.ANGLE_PER_LEVEL, "レベルあたりの角度", "レベルnのとき、エンティティへの向きと視線の角度差の許容量は以下の計算式で表されます：n * (角度) * π / 180");
        addConfigWithDesc(
                ConfigID.EFFECTIVE_FOR_ALL_BLOCKS, "全てのブロックに対して適正", "ガラスなど、適正ツールが存在しないブロックにも採掘速度ボーナスが適用されるかどうか。");
        addConfigWithDesc(
                ConfigID.CAN_BREAK_UNBREAKABLE, "破壊不能ブロックを破壊可能", "レベル4で岩盤などを破壊できます。プレイヤー以外が使ったときの効果は保証できません。");
        addConfigWithDesc(ConfigID.UNBREAKABLE_BLOCK_HARDNESS, "破壊不能なブロックの硬さ", "岩盤など破壊不能なブロックを採掘する際に適用される硬さ。");
        addConfigWithDesc("enchantment_toggling", "エンチャントの切り替え", "サーバーの再起動が必要です！");
    }

    @Override
    public void accept(String s, String s2) {
        add(s, s2);
    }
}
