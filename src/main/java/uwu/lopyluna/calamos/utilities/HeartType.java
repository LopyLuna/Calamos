package uwu.lopyluna.calamos.utilities;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum HeartType {
    DEFAULT(
            new ResourceLocation("hud/heart/calamos/default_large"),
            new ResourceLocation("hud/heart/calamos/default_medium"),
            new ResourceLocation("hud/heart/calamos/default_small"),
            new ResourceLocation("hud/heart/calamos/default_empty"),
            new ResourceLocation("hud/heart/calamos/default_hardcore_large"),
            new ResourceLocation("hud/heart/calamos/default_hardcore_medium"),
            new ResourceLocation("hud/heart/calamos/default_hardcore_small"),
            new ResourceLocation("hud/heart/calamos/default_hardcore_empty")
    ),
    GOLDEN(
            new ResourceLocation("hud/heart/calamos/golden_large"),
            new ResourceLocation("hud/heart/calamos/golden_medium"),
            new ResourceLocation("hud/heart/calamos/golden_small"),
            new ResourceLocation("hud/heart/calamos/golden_empty"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_large"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_medium"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_small"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_empty")
    ),
    ENLIGHTENED(
            new ResourceLocation("hud/heart/calamos/enlightened_large"),
            new ResourceLocation("hud/heart/calamos/enlightened_medium"),
            new ResourceLocation("hud/heart/calamos/enlightened_small"),
            new ResourceLocation("hud/heart/calamos/enlightened_empty"),
            new ResourceLocation("hud/heart/calamos/enlightened_hardcore_large"),
            new ResourceLocation("hud/heart/calamos/enlightened_hardcore_medium"),
            new ResourceLocation("hud/heart/calamos/enlightened_hardcore_small"),
            new ResourceLocation("hud/heart/calamos/enlightened_hardcore_empty")
    ),
    STELLATECH(
            new ResourceLocation("hud/heart/calamos/stellatech_large"),
            new ResourceLocation("hud/heart/calamos/stellatech_medium"),
            new ResourceLocation("hud/heart/calamos/stellatech_small"),
            new ResourceLocation("hud/heart/calamos/stellatech_empty"),
            new ResourceLocation("hud/heart/calamos/stellatech_hardcore_large"),
            new ResourceLocation("hud/heart/calamos/stellatech_hardcore_medium"),
            new ResourceLocation("hud/heart/calamos/stellatech_hardcore_small"),
            new ResourceLocation("hud/heart/calamos/stellatech_hardcore_empty")
    ),
    ;
    private final ResourceLocation large;
    private final ResourceLocation medium;
    private final ResourceLocation small;
    private final ResourceLocation empty;
    private final ResourceLocation hardcoreLarge;
    private final ResourceLocation hardcoreMedium;
    private final ResourceLocation hardcoreSmall;
    private final ResourceLocation hardcoreEmpty;
    
    HeartType(
            ResourceLocation pLarge,
            ResourceLocation pMedium,
            ResourceLocation pSmall,
            ResourceLocation pEmpty,
            ResourceLocation pHardcoreLarge,
            ResourceLocation pHardcoreMedium,
            ResourceLocation pHardcoreSmall,
            ResourceLocation pHardcoreEmpty
    ) {
        this.large = pLarge;
        this.medium = pMedium;
        this.small = pSmall;
        this.empty = pEmpty;
        this.hardcoreLarge = pHardcoreLarge;
        this.hardcoreMedium = pHardcoreMedium;
        this.hardcoreSmall = pHardcoreSmall;
        this.hardcoreEmpty = pHardcoreEmpty;
    }
    
    public ResourceLocation getSprite(boolean pHardcore, boolean pHalfHeart, boolean pBlinking) {
        if (!pHardcore) {
            if (pHalfHeart) {
                return pBlinking ? this.small : this.medium;
            } else {
                return pBlinking ? this.medium : this.large;
            }
        } else if (pHalfHeart) {
            return pBlinking ? this.hardcoreSmall : this.hardcoreMedium;
        } else {
            return pBlinking ? this.hardcoreMedium : this.hardcoreLarge;
        }
    }
    public ResourceLocation getEmptySprite(boolean pHardcore) {
        return pHardcore ? this.hardcoreEmpty : this.empty;
    }
    /**
     * Returns the {@link HeartType} based on the player's status effects.
     * <p>
     * @return the {@link HeartType} based on the player's status effects.
     *
     * @param pPlayer the player for which to determine the HeartType.
     */
    public static HeartType forPlayer(Player pPlayer, float pMaxHealth) {
        HeartType gui$hearttype;
        if (pMaxHealth >= 400.0D && pMaxHealth < 600.0D) {
            gui$hearttype = GOLDEN;
        } else if (pMaxHealth >= 600.0D && pMaxHealth < 800.0D) {
            gui$hearttype = ENLIGHTENED;
        } else if (pMaxHealth >= 800.0D) {
            gui$hearttype = STELLATECH;
        } else {
            gui$hearttype = DEFAULT;
        }
        
        return gui$hearttype;
    }

    public static void renderHeart(GuiGraphics pGuiGraphics, HeartType pHeartType, int pX, int pY, boolean pHardcore, boolean pHalfHeart, boolean pBlinking) {
        pGuiGraphics.blitSprite(pHeartType.getSprite(pHardcore, pBlinking, pHalfHeart), pX, pY, 11, 11);
    }
    public static void renderContainer(GuiGraphics pGuiGraphics, HeartType pHeartType, int pX, int pY, boolean pHardcore) {
        pGuiGraphics.blitSprite(pHeartType.getEmptySprite(pHardcore), pX, pY, 11, 11);
    }
}
