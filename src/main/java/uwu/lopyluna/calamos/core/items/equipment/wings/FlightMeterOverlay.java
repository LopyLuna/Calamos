package uwu.lopyluna.calamos.core.items.equipment.wings;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModAttachmentTypes;
import uwu.lopyluna.calamos.elements.ModAttributes;


public class FlightMeterOverlay implements LayeredDraw.Layer {
    public static final FlightMeterOverlay INSTANCE = new FlightMeterOverlay();
    public final static ResourceLocation flightMeter = CalamosMod.asResource("textures/gui/sprites/flight_meter.png");
    public final static ResourceLocation flightMeterFull = CalamosMod.asResource("textures/gui/sprites/flight_meter_full.png");
    static final int IMAGE_WIDTH = 16;
    static final int IMAGE_HEIGHT = 32;

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Window window = Minecraft.getInstance().getWindow();
        Minecraft mc = Minecraft.getInstance();
        int barX, barY;
        barX = window.getGuiScaledWidth() / 2 + 120;
        barY = window.getGuiScaledHeight() - 53;

        if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
            return;

        LocalPlayer player = mc.player;
        if (player == null)
            return;

        var maxFlight = player.getAttribute(ModAttributes.MAX_FLIGHT);
        if (maxFlight == null || maxFlight.getValue() == 0)
            return;
        guiGraphics.blit(flightMeter, barX, barY, 0, IMAGE_HEIGHT * 2, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);
        guiGraphics.blit(flightMeterFull, barX, barY, 0, IMAGE_HEIGHT * 2, IMAGE_WIDTH, (int) (IMAGE_HEIGHT * getFlightMeterPercent(player) + (IMAGE_HEIGHT - 26) / 2), IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    public float getFlightMeterPercent(Player player) {
        WingsData wings = player.getData(ModAttachmentTypes.WINGS);
        return (float) (wings.getFlight() / player.getAttributeValue(ModAttributes.MAX_FLIGHT));
    }
}
