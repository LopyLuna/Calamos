package uwu.lopyluna.calamos.core.items.equipment.wings;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.GameType;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;


public class FlightMeterOverlay implements LayeredDraw.Layer {
    public static final FlightMeterOverlay INSTANCE = new FlightMeterOverlay();
    public final ResourceLocation border = CalamosMod.asResource("textures/gui/sprites/hud/flight_meter/border.png");
    public final ResourceLocation overlay = CalamosMod.asResource("textures/gui/sprites/hud/flight_meter/overlay.png");
    static final int IMAGE_WIDTH = 11;
    static final int IMAGE_HEIGHT = 33;

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Window window = Minecraft.getInstance().getWindow();
        Minecraft mc = Minecraft.getInstance();
        int barX, barY;
        barX = window.getGuiScaledWidth() / 2 - 105;
        barY = window.getGuiScaledHeight() - 35;

        if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
            return;

        LocalPlayer player = mc.player;
        if (player == null)
            return;

        var maxFlight = player.getAttribute(ModAttributes.MAX_FLIGHT);
        WingsData wings = player.getData(ModAttachmentTypes.WINGS);
        if (maxFlight == null || maxFlight.getValue() == 0)
            return;

        guiGraphics.blit(border, barX, barY, 0, IMAGE_HEIGHT * 2, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);

        int barHeight = (int)(getProgress(wings, (float)maxFlight.getValue()) * 27.0F);
        if (barHeight > 0) {
            guiGraphics.blit(overlay, barX, barY + IMAGE_HEIGHT - barHeight, 0, IMAGE_HEIGHT - barHeight, IMAGE_WIDTH, barHeight, IMAGE_WIDTH, IMAGE_HEIGHT);
        }
    }

    private float getProgress(WingsData wings, float max) {
        return Mth.clamp((float)wings.getFlight() / max, 0.0F, 1.0F);
    }
}
