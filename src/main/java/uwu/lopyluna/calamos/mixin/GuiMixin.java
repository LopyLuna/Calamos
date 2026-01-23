package uwu.lopyluna.calamos.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import uwu.lopyluna.calamos.client.player.HeartHud;

@Mixin(Gui.class)
public class GuiMixin {
    
    @Final
    @Shadow
    private final RandomSource random = RandomSource.create();
    
    /**
     * @author PouffyDev
     * @reason Change the health bar to suit Calamos' style
     */
    @Overwrite
    protected void renderHearts(GuiGraphics pGuiGraphics, Player pPlayer, int pX, int pY, int pHeight, int pOffsetHeartIndex,
                                float pMaxHealth, int pCurrentHealth, int pDisplayHealth, int pAbsorptionAmount, boolean pRenderHighlight) {
        new HeartHud(pPlayer).render(pGuiGraphics, pX, pY, pHeight, pOffsetHeartIndex, pMaxHealth, pCurrentHealth, pDisplayHealth, pAbsorptionAmount, pRenderHighlight);
    }
}
