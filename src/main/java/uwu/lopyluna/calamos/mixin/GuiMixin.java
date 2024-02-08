package uwu.lopyluna.calamos.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import uwu.lopyluna.calamos.utilities.HeartType;

@Mixin(Gui.class)
public class GuiMixin {
    
    @Final
    @Shadow
    protected final RandomSource random = RandomSource.create();
    
    /**
     * @author PouffyDev
     * @reason Change the health bar to suit Calamos' style
     */
    @Overwrite
    protected void renderHearts(GuiGraphics pGuiGraphics, Player pPlayer, int pX, int pY, int pHeight, int pOffsetHeartIndex,
                                float pMaxHealth,
                                int pCurrentHealth,
                                int pDisplayHealth,
                                int pAbsorptionAmount, boolean pRenderHighlight) {
        HeartType gui$hearttype = HeartType.forPlayer(pPlayer, pMaxHealth);
        boolean flag = pPlayer.level().getLevelData().isHardcore();
        int i = Mth.ceil((double)pMaxHealth / 20.0);
        int j = Mth.ceil((double)pAbsorptionAmount / 20.0);
        int k = i * 2;
        
        for(int l = i + j - 1; l >= 0; --l) {
            int i1 = l / 10;
            int j1 = l % 10;
            int k1 = pX + j1 * 8;
            int l1 = pY - i1 * pHeight;
            if (pCurrentHealth + pAbsorptionAmount <= 4) {
                l1 += this.random.nextInt(2);
            }
            
            if (l < i && l == pOffsetHeartIndex) {
                l1 -= 2;
            }
            
            HeartType.renderContainer(pGuiGraphics, gui$hearttype, k1, l1, flag);
            int i2 = l * 2;
            boolean flag1 = l >= i;
            if (flag1) {
                int j2 = i2 - k;
                if (j2 < pAbsorptionAmount) {
                    boolean flag2 = j2 + 1 == pAbsorptionAmount;
                    HeartType.renderHeart(pGuiGraphics,
                            gui$hearttype == HeartType.STELLATECH ? gui$hearttype :
                                    gui$hearttype == HeartType.ENLIGHTENED ? gui$hearttype :
                                            HeartType.GOLDEN,
                            k1, l1, flag, false, flag2);
                }
            }
            
            if (pRenderHighlight && i2 < pDisplayHealth) {
                boolean flag3 = i2 + 1 == pDisplayHealth;
                HeartType.renderHeart(pGuiGraphics, gui$hearttype, k1, l1, flag, true, flag3);
            }
            
            if (i2 < pCurrentHealth) {
                boolean flag4 = i2 + 1 == pCurrentHealth;
                HeartType.renderHeart(pGuiGraphics, gui$hearttype, k1, l1, flag, false, flag4);
            }
        }
    }
}
