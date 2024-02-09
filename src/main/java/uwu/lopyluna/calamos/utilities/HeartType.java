package uwu.lopyluna.calamos.utilities;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum HeartType {
    DEFAULT(
            "default_large",
            "default_medium",
            "default_small",
            "default_empty",
            "default_hardcore_large",
            "default_hardcore_medium",
            "default_hardcore_small",
            "default_hardcore_empty"
    ),
    GOLDEN(
            "golden_large",
            "golden_medium",
            "golden_small",
            "golden_empty",
            "golden_hardcore_large",
            "golden_hardcore_medium",
            "golden_hardcore_small",
            "golden_hardcore_empty"
    ),
    ENLIGHTENED(
            "enlightened_large",
            "enlightened_medium",
            "enlightened_small",
            "enlightened_empty",
            "enlightened_hardcore_large",
            "enlightened_hardcore_medium",
            "enlightened_hardcore_small",
            "enlightened_hardcore_empty"
    ),
    STELLATECH(
            "stellatech_large",
            "stellatech_medium",
            "stellatech_small",
            "stellatech_empty",
            "stellatech_hardcore_large",
            "stellatech_hardcore_medium",
            "stellatech_hardcore_small",
            "stellatech_hardcore_empty"
    ),
    ;

    private static final String PREFIX = "hud/heart/calamos/%s";
    private final ResourceLocation large;
    private final ResourceLocation medium;
    private final ResourceLocation small;
    private final ResourceLocation empty;
    private final ResourceLocation hardcoreLarge;
    private final ResourceLocation hardcoreMedium;
    private final ResourceLocation hardcoreSmall;
    private final ResourceLocation hardcoreEmpty;

    HeartType(String pLarge,
              String pMedium,
              String pSmall,
              String pEmpty,
              String pHardcoreLarge,
              String pHardcoreMedium,
              String pHardcoreSmall,
              String pHardcoreEmpty) {
        this(
                ModUtils.location(PREFIX.formatted(pLarge)),
                ModUtils.location(PREFIX.formatted(pMedium)),
                ModUtils.location(PREFIX.formatted(pSmall)),
                ModUtils.location(PREFIX.formatted(pEmpty)),
                ModUtils.location(PREFIX.formatted(pHardcoreLarge)),
                ModUtils.location(PREFIX.formatted(pHardcoreMedium)),
                ModUtils.location(PREFIX.formatted(pHardcoreSmall)),
                ModUtils.location(PREFIX.formatted(pHardcoreEmpty))
        );
    }

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
     *
     * @param pPlayer the player for which to determine the HeartType.
     * @return the {@link HeartType} based on the player's status effects.
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
