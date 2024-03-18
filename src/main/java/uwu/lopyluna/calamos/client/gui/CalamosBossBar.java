package uwu.lopyluna.calamos.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import uwu.lopyluna.calamos.elements.ModEntity;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.HashMap;
import java.util.Map;

public class CalamosBossBar {
    /**
     * customBars.put(BuiltInRegistries.ENTITY_TYPE.getKey(ModEntity.WORM_HEAD.get()), new CalamosBossBar(
     barLocation("worm_bar_base"),
     barLocation("worm_bar_overlay"),
     4, 8, 2, -12, -6, 256, 16, 21, ChatFormatting.GOLD
     ));
     */
    public static Map<ResourceLocation, CalamosBossBar> customBars = new HashMap<>();

    static {
        
        customBars.put(BuiltInRegistries.ENTITY_TYPE.getKey(ModEntity.WORM_HEAD.get()), new CalamosBossBar(
                /*
                I stole mowzie mob's bar and overlay as a base + I couldn't be bothered to make my own
                -Boxx
                */
                "worm_bar",
                4, 8, 2, -12, -6, 256, 16, 21, ChatFormatting.GOLD
        ));
        
    }

    private final ResourceLocation baseTexture;
    private final ResourceLocation overlayTexture;
    private final boolean hasOverlay;

    private final int baseHeight;
    private final int baseTextureHeight;
    private final int baseOffsetY;
    private final int overlayOffsetX;
    private final int overlayOffsetY;
    private final int overlayWidth;
    private final int overlayHeight;

    private final int verticalIncrement;

    private final ChatFormatting textColor;

    public CalamosBossBar(ResourceLocation baseTexture, ResourceLocation overlayTexture, int baseHeight, int baseTextureHeight, int baseOffsetY, int overlayOffsetX, int overlayOffsetY, int overlayWidth, int overlayHeight, int verticalIncrement, ChatFormatting textColor) {
        this.baseTexture = baseTexture;
        this.overlayTexture = overlayTexture;
        this.hasOverlay = overlayTexture != null;
        this.baseHeight = baseHeight;
        this.baseTextureHeight = baseTextureHeight;
        this.baseOffsetY = baseOffsetY;
        this.overlayOffsetX = overlayOffsetX;
        this.overlayOffsetY = overlayOffsetY;
        this.overlayWidth = overlayWidth;
        this.overlayHeight = overlayHeight;
        this.verticalIncrement = verticalIncrement;
        this.textColor = textColor;
    }
    public CalamosBossBar(String name, int baseHeight, int baseTextureHeight, int baseOffsetY, int overlayOffsetX, int overlayOffsetY, int overlayWidth, int overlayHeight, int verticalIncrement, ChatFormatting textColor) {
        this.baseTexture = barLocation(name + "_base");
        this.overlayTexture = barLocation(name + "_overlay");;
        this.hasOverlay = overlayTexture != null;
        this.baseHeight = baseHeight;
        this.baseTextureHeight = baseTextureHeight;
        this.baseOffsetY = baseOffsetY;
        this.overlayOffsetX = overlayOffsetX;
        this.overlayOffsetY = overlayOffsetY;
        this.overlayWidth = overlayWidth;
        this.overlayHeight = overlayHeight;
        this.verticalIncrement = verticalIncrement;
        this.textColor = textColor;
    }

    public ResourceLocation getBaseTexture() {
        return baseTexture;
    }

    public ResourceLocation getOverlayTexture() {
        return overlayTexture;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    public int getBaseTextureHeight() {
        return baseTextureHeight;
    }

    public int getBaseOffsetY() {
        return baseOffsetY;
    }

    public int getOverlayOffsetX() {
        return overlayOffsetX;
    }

    public int getOverlayOffsetY() {
        return overlayOffsetY;
    }

    public int getOverlayWidth() {
        return overlayWidth;
    }

    public int getOverlayHeight() {
        return overlayHeight;
    }

    public int getVerticalIncrement() {
        return verticalIncrement;
    }

    public ChatFormatting getTextColor() {
        return textColor;
    }

    public void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
        PoseStack stack = event.getGuiGraphics().pose();
        int y = event.getY();
        int i = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int j = y-9;
        int k = i/2-91;
        Minecraft.getInstance().getProfiler().push("customBossBarBase");

        GuiGraphics graphics = event.getGuiGraphics();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getBaseTexture());
        drawBar(graphics, getBaseTexture(), event.getX() + 1, y + getBaseOffsetY(), event.getBossEvent());
        Component component = event.getBossEvent().getName().copy().withStyle(getTextColor());
        Minecraft.getInstance().getProfiler().pop();

        int l = Minecraft.getInstance().font.width(component);
        int i1 = i/2 - l/2;
        int j1 = j;
        graphics.drawString(Minecraft.getInstance().font, component, i1, j1, 16777215);

        if (hasOverlay()) {
            Minecraft.getInstance().getProfiler().push("customBossBarOverlay");
            RenderSystem.setShaderTexture(0, getOverlayTexture());
            graphics.blit(getOverlayTexture(), event.getX() + 1 + getOverlayOffsetX(), y + getOverlayOffsetY() + getBaseOffsetY(), 0, 0, getOverlayWidth(), getOverlayHeight(), getOverlayWidth(), getOverlayHeight());
            Minecraft.getInstance().getProfiler().pop();
        }

        event.setIncrement(getVerticalIncrement());
    }

    private void drawBar(GuiGraphics graphics, ResourceLocation location, int x, int y, BossEvent event) {
        graphics.blit(location, x, y, 0, 0, 182, getBaseHeight(), 256, getBaseTextureHeight());
        int i = (int)(event.getProgress() * 183.0F);
        if (i > 0) {
            graphics.blit(location, x, y, 0, getBaseHeight(), i, getBaseHeight(), 256, getBaseTextureHeight());
        }
    }

    private static ResourceLocation barLocation(String tex) {
        return ModUtils.location("textures/gui/boss_bar/" + tex + ".png");
    }
}
