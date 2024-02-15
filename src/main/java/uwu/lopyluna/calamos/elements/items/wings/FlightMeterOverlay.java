package uwu.lopyluna.calamos.elements.items.wings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.definitions.WardenAnimation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.elements.ModAttributes;
import uwu.lopyluna.calamos.mixin.AccessorRangedAttribute;

public class FlightMeterOverlay implements IGuiOverlay {
    public static final FlightMeterOverlay INSTANCE = new FlightMeterOverlay();
    public final static ResourceLocation flightMeter = new ResourceLocation(CalamosMod.MODID, "textures/gui/sprites/flight_meter.png");
    public final static ResourceLocation flightMeterFull = new ResourceLocation(CalamosMod.MODID, "textures/gui/sprites/flight_meter_full.png");
    static final int IMAGE_WIDTH = 16;
    static final int IMAGE_HEIGHT = 32;
    @Override
    public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
            return;
        
        LocalPlayer player = mc.player;
        
        if (player == null)
            return;
        
        if (!(player.getInventory().armor.get(2).getItem() instanceof WingsItem wingsItem))
            return;
        int barX, barY;
        barX = screenWidth / 2 + 120;
        barY = screenHeight - 53;
        guiGraphics.blit(flightMeter, barX, barY, 0, IMAGE_HEIGHT * 2, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);
        guiGraphics.blit(flightMeterFull, barX, barY, 0, IMAGE_HEIGHT * 2, IMAGE_WIDTH, (int) (IMAGE_HEIGHT * getFlightMeterPercent(player, wingsItem) + (IMAGE_HEIGHT - 26) / 2), IMAGE_WIDTH, IMAGE_HEIGHT);
    }
    public float getFlightMeterPercent(Player player, WingsItem wings) {
        return wings.getFlightMeter(player) / wings.getMaxFlightMeter(player);
    }
}
