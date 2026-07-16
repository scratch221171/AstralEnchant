package net.scratch221171.astralenchant.client.screen;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.scratch221171.astralenchant.ModUtils;
import net.scratch221171.astralenchant.common.inventory.EnchantersWorkbenchMenu;
import net.scratch221171.astralenchant.common.inventory.EnchantersWorkbenchMenu.DisplayEntry;
import net.scratch221171.astralenchant.network.ToggleEnchantmentPayload;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class EnchantersWorkbenchScreen extends AbstractContainerScreen<EnchantersWorkbenchMenu> {

    private static final ResourceLocation TEXTURE = ModUtils.loc("textures/gui/container/enchanters_workbench.png");
    private static final int TEXTURE_WIDTH = 512;
    private static final int TEXTURE_HEIGHT = 256;

    private static final ResourceLocation SCROLLER_SPRITE =
            ResourceLocation.withDefaultNamespace("container/villager/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE =
            ResourceLocation.withDefaultNamespace("container/villager/scroller_disabled");

    private static final int VISIBLE_ROWS = 7;
    private static final int ROW_HEIGHT = 20;
    private static final int ROW_WIDTH = 88;

    private final EnchantmentButton[] rowButtons = new EnchantmentButton[VISIBLE_ROWS];
    private List<DisplayEntry> currentEntries = List.of();
    private int scrollOff;
    private boolean isDragging;
    private final Player player;

    public EnchantersWorkbenchScreen(EnchantersWorkbenchMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 276;
        this.inventoryLabelX = 107;
        this.player = playerInventory.player;
    }

    @Override
    protected void init() {
        super.init();
        int rowY = this.topPos + 16 + 2;
        for (int i = 0; i < VISIBLE_ROWS; i++) {
            EnchantmentButton button = new EnchantmentButton(this.leftPos + 5, rowY, this::onEnchantmentButtonPressed);
            this.rowButtons[i] = button;
            this.addRenderableWidget(button);
            rowY += ROW_HEIGHT;
        }
    }

    private void onEnchantmentButtonPressed(Button button) {
        if (!(button instanceof EnchantmentButton enchantmentButton)) return;
        DisplayEntry entry = enchantmentButton.getEntry();
        if (entry == null || entry.curse()) return;
        if (this.minecraft == null) return;

        PacketDistributor.sendToServer(
                new ToggleEnchantmentPayload(this.menu.containerId, entry.id().location()));
    }

    /** 毎フレーム、現在の出力(なければ入力)スロットからエンチャント一覧を再取得し、7個のボタンに割り当てる。 */
    private void updateRowButtons() {
        if (this.minecraft == null || this.minecraft.level == null) {
            this.currentEntries = List.of();
        } else {
            var output = this.menu.getSlot(EnchantersWorkbenchMenu.OUTPUT_SLOT).getItem();
            var input = this.menu.getSlot(EnchantersWorkbenchMenu.INPUT_SLOT).getItem();
            var reference = output.isEmpty() ? input : output;

            if (reference.isEmpty()) {
                this.currentEntries = List.of();
            } else {
                HolderLookup.Provider provider = this.minecraft.level.registryAccess();
                this.currentEntries = EnchantersWorkbenchMenu.getEnchantmentList(reference, provider);
            }
        }

        int maxScroll = Math.max(0, this.currentEntries.size() - VISIBLE_ROWS);
        this.scrollOff = Mth.clamp(this.scrollOff, 0, maxScroll);

        HolderLookup.Provider provider =
                this.minecraft != null && this.minecraft.level != null ? this.minecraft.level.registryAccess() : null;

        for (int i = 0; i < VISIBLE_ROWS; i++) {
            int dataIndex = i + this.scrollOff;
            EnchantmentButton button = this.rowButtons[i];
            if (dataIndex < this.currentEntries.size() && provider != null) {
                DisplayEntry entry = this.currentEntries.get(dataIndex);
                button.setEntry(entry, resolveName(entry, provider));
                button.visible = true;
                button.active = !entry.curse() && !entry.blockedByConflict();
                button.setTooltip(resolveTooltip(entry));
            } else {
                button.setEntry(null, null);
                button.visible = false;
                button.setTooltip(null);
            }
        }
    }

    private static Component resolveName(DisplayEntry entry, HolderLookup.Provider provider) {
        Holder<Enchantment> holder =
                provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(entry.id());
        var component = Enchantment.getFullname(holder, entry.level()).copy();

        if (entry.curse()) return component;

        ChatFormatting color;
        if (entry.enabled()) {
            color = ChatFormatting.WHITE;
        } else if (entry.blockedByConflict()) {
            color = ChatFormatting.DARK_GRAY;
        } else {
            color = ChatFormatting.GRAY;
        }
        return component.withStyle(color);
    }

    @Nullable private static Tooltip resolveTooltip(DisplayEntry entry) {
        if (entry.curse()) {
            return net.minecraft.client.gui.components.Tooltip.create(
                    Component.translatable("gui.astralenchant.enchanters_workbench.locked_curse"));
        }
        if (entry.blockedByConflict()) {
            return net.minecraft.client.gui.components.Tooltip.create(
                    Component.translatable("gui.astralenchant.enchanters_workbench.locked_conflict"));
        }
        return null;
    }

    @Override
    protected void renderLabels(@NonNull GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(
                this.font, this.title, 49 + this.imageWidth / 2 - this.font.width(this.title) / 2, 6, 4210752, false);
        graphics.drawString(
                this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
        int i = this.menu.getCost();
        if (i > 0) {
            int j = 8453920;
            Component component;
            if (!this.menu.getSlot(2).hasItem()) {
                component = null;
            } else {
                component = Component.translatable("container.enchanters_workbench.cost", i);
                if (!this.menu.getSlot(2).mayPickup(this.player)) {
                    j = 16736352;
                }
            }

            if (component != null) {
                int k = this.imageWidth - 8 - this.font.width(component) - 2;
                int l = 69;
                graphics.fill(k - 2, 67, this.imageWidth - 8, 79, 1325400064);
                graphics.drawString(this.font, component, k, l, j);
            }
        }
    }

    @Override
    protected void renderBg(@NonNull GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(
                TEXTURE,
                this.leftPos,
                this.topPos,
                0,
                0.0F,
                0.0F,
                this.imageWidth,
                this.imageHeight,
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT);
        renderScroller(graphics);
    }

    private void renderScroller(GuiGraphics graphics) {
        int size = this.currentEntries.size();
        if (size > VISIBLE_ROWS) {
            int range = size + 1 - VISIBLE_ROWS;
            int step = 1 + (139 - (27 + (range - 1) * 139 / range)) / range + 139 / range;
            int offsetY = this.scrollOff == range - 1 ? 113 : Math.min(113, this.scrollOff * step);
            graphics.blitSprite(SCROLLER_SPRITE, this.leftPos + 94, this.topPos + 18 + offsetY, 0, 6, 27);
        } else {
            graphics.blitSprite(SCROLLER_DISABLED_SPRITE, this.leftPos + 94, this.topPos + 18, 0, 6, 27);
        }
    }

    @Override
    public void render(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        updateRowButtons();
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    private boolean canScroll() {
        return this.currentEntries.size() > VISIBLE_ROWS;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (canScroll()) {
            int maxScroll = this.currentEntries.size() - VISIBLE_ROWS;
            this.scrollOff = Mth.clamp((int) (this.scrollOff - scrollY), 0, maxScroll);
        }
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.isDragging) {
            int top = this.topPos + 18;
            int bottom = top + 139;
            int maxScroll = this.currentEntries.size() - VISIBLE_ROWS;
            float f = ((float) mouseY - (float) top - 13.5F) / ((float) (bottom - top) - 27.0F);
            f = f * (float) maxScroll + 0.5F;
            this.scrollOff = Mth.clamp((int) f, 0, maxScroll);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.isDragging = canScroll()
                && mouseX > (double) (this.leftPos + 94)
                && mouseX < (double) (this.leftPos + 94 + 6)
                && mouseY > (double) (this.topPos + 18)
                && mouseY <= (double) (this.topPos + 18 + 139 + 1);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    /** エンチャント1件分を表すボタン。ホバー光/クリック音はvanilla Buttonの標準挙動に任せている。 */
    @OnlyIn(Dist.CLIENT)
    private static class EnchantmentButton extends Button {
        private DisplayEntry entry;

        EnchantmentButton(int x, int y, OnPress onPress) {
            super(x, y, ROW_WIDTH, ROW_HEIGHT, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);
            this.visible = false;
        }

        void setEntry(DisplayEntry entry, Component displayName) {
            this.entry = entry;
            this.setMessage(displayName == null ? CommonComponents.EMPTY : displayName);
        }

        DisplayEntry getEntry() {
            return entry;
        }
    }
}
