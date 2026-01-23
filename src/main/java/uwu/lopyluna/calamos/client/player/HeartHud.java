package uwu.lopyluna.calamos.client.player;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import uwu.lopyluna.calamos.utilities.HeartType;

public class HeartHud {

    protected final Player player;
    protected final RandomSource random;

    public HeartHud(Player player) {
        this.player = player;
        this.random = player.getRandom();
    }

    public void render(GuiGraphics pGuiGraphics, int x, int y, int height, int offsetHeartIndex,
                       float maxHealth,
                       int currentHealth,
                       int displayHealth,
                       int absorptionAmount, boolean renderHighlight) {
        HeartType heartType = HeartType.forPlayer(this.player, maxHealth);
        boolean hardcore = this.player.level().getLevelData().isHardcore();
        int i = Mth.ceil(maxHealth / 20.0);
        int j = Mth.ceil(absorptionAmount / 20.0);
        int k = i * 2;

        for(int l = i + j - 1; l >= 0; --l) {
            int i1 = l / 10;
            int j1 = l % 10;
            int k1 = x + j1 * 10;
            int l1 = y - i1 * height;
            if (currentHealth + absorptionAmount <= 4) {
                l1 += this.random.nextInt(2);
            }
            if (l < i && l == offsetHeartIndex) {
                l1 -= 2;
            }

            HeartType.renderContainer(pGuiGraphics, heartType, k1, l1, hardcore);
            int i2 = l * 2;
            boolean flag1 = l >= i;
            if (flag1) {
                int j2 = i2 - k;
                if (j2 < absorptionAmount) {
                    boolean flag2 = j2 + 1 == absorptionAmount;
                    HeartType.renderHeart(pGuiGraphics,
                            heartType == HeartType.STELLATECH ? heartType :
                                    heartType == HeartType.ENLIGHTENED ? heartType :
                                            HeartType.GOLDEN,
                            k1, l1, hardcore, false, flag2);
                }
            }

            if (renderHighlight && i2 < displayHealth) {
                boolean flag3 = i2 + 1 == displayHealth;
                HeartType.renderHeart(pGuiGraphics, heartType, k1, l1, hardcore, true, flag3);
            }

            if (i2 < currentHealth) {
                boolean flag4 = i2 + 1 == currentHealth;
                HeartType.renderHeart(pGuiGraphics, heartType, k1, l1, hardcore, false, flag4);
            }
        }
    }
}
