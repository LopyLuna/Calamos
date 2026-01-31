package uwu.lopyluna.calamos.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;

public class EmptyLayer implements LayeredDraw.Layer {
    public static EmptyLayer INSTANCE = new EmptyLayer();

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        //no-op
    }
}
