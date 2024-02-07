package uwu.lopyluna.calamos.utilities;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum HeartType {
    CONTAINER(
            new ResourceLocation("hud/heart/container"),
            new ResourceLocation("hud/heart/container_blinking"),
            new ResourceLocation("hud/heart/container"),
            new ResourceLocation("hud/heart/container_blinking"),
            new ResourceLocation("hud/heart/container_hardcore"),
            new ResourceLocation("hud/heart/container_hardcore_blinking"),
            new ResourceLocation("hud/heart/container_hardcore"),
            new ResourceLocation("hud/heart/container_hardcore_blinking")
    ),
    NORMAL(
            new ResourceLocation("hud/heart/full"),
            new ResourceLocation("hud/heart/full_blinking"),
            new ResourceLocation("hud/heart/half"),
            new ResourceLocation("hud/heart/half_blinking"),
            new ResourceLocation("hud/heart/hardcore_full"),
            new ResourceLocation("hud/heart/hardcore_full_blinking"),
            new ResourceLocation("hud/heart/hardcore_half"),
            new ResourceLocation("hud/heart/hardcore_half_blinking")
    ),
    POISIONED(
            new ResourceLocation("hud/heart/poisoned_full"),
            new ResourceLocation("hud/heart/poisoned_full_blinking"),
            new ResourceLocation("hud/heart/poisoned_half"),
            new ResourceLocation("hud/heart/poisoned_half_blinking"),
            new ResourceLocation("hud/heart/poisoned_hardcore_full"),
            new ResourceLocation("hud/heart/poisoned_hardcore_full_blinking"),
            new ResourceLocation("hud/heart/poisoned_hardcore_half"),
            new ResourceLocation("hud/heart/poisoned_hardcore_half_blinking")
    ),
    WITHERED(
            new ResourceLocation("hud/heart/withered_full"),
            new ResourceLocation("hud/heart/withered_full_blinking"),
            new ResourceLocation("hud/heart/withered_half"),
            new ResourceLocation("hud/heart/withered_half_blinking"),
            new ResourceLocation("hud/heart/withered_hardcore_full"),
            new ResourceLocation("hud/heart/withered_hardcore_full_blinking"),
            new ResourceLocation("hud/heart/withered_hardcore_half"),
            new ResourceLocation("hud/heart/withered_hardcore_half_blinking")
    ),
    ABSORBING(
            new ResourceLocation("hud/heart/absorbing_full"),
            new ResourceLocation("hud/heart/absorbing_full_blinking"),
            new ResourceLocation("hud/heart/absorbing_half"),
            new ResourceLocation("hud/heart/absorbing_half_blinking"),
            new ResourceLocation("hud/heart/absorbing_hardcore_full"),
            new ResourceLocation("hud/heart/absorbing_hardcore_full_blinking"),
            new ResourceLocation("hud/heart/absorbing_hardcore_half"),
            new ResourceLocation("hud/heart/absorbing_hardcore_half_blinking")
    ),
    FROZEN(
            new ResourceLocation("hud/heart/frozen_full"),
            new ResourceLocation("hud/heart/frozen_full_blinking"),
            new ResourceLocation("hud/heart/frozen_half"),
            new ResourceLocation("hud/heart/frozen_half_blinking"),
            new ResourceLocation("hud/heart/frozen_hardcore_full"),
            new ResourceLocation("hud/heart/frozen_hardcore_full_blinking"),
            new ResourceLocation("hud/heart/frozen_hardcore_half"),
            new ResourceLocation("hud/heart/frozen_hardcore_half_blinking")
    ),
    GOLDEN(
            new ResourceLocation("hud/heart/calamos/golden_large"),
            new ResourceLocation("hud/heart/calamos/golden_medium"),
            new ResourceLocation("hud/heart/calamos/golden_small"),
            new ResourceLocation("hud/heart/calamos/golden_empty"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_full"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_full_blinking"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_half"),
            new ResourceLocation("hud/heart/calamos/golden_hardcore_half_blinking")
    );
    private final ResourceLocation full;
    private final ResourceLocation fullBlinking;
    private final ResourceLocation half;
    private final ResourceLocation halfBlinking;
    private final ResourceLocation hardcoreFull;
    private final ResourceLocation hardcoreFullBlinking;
    private final ResourceLocation hardcoreHalf;
    private final ResourceLocation hardcoreHalfBlinking;
    
    private HeartType(
            ResourceLocation pFull,
            ResourceLocation pFullBlinking,
            ResourceLocation pHalf,
            ResourceLocation pHalfBlinking,
            ResourceLocation pHardcoreFull,
            ResourceLocation pHardcoreBlinking,
            ResourceLocation pHardcoreHalf,
            ResourceLocation pHardcoreHalfBlinking
    ) {
        this.full = pFull;
        this.fullBlinking = pFullBlinking;
        this.half = pHalf;
        this.halfBlinking = pHalfBlinking;
        this.hardcoreFull = pHardcoreFull;
        this.hardcoreFullBlinking = pHardcoreBlinking;
        this.hardcoreHalf = pHardcoreHalf;
        this.hardcoreHalfBlinking = pHardcoreHalfBlinking;
    }
    
    public ResourceLocation getSprite(boolean pHardcore, boolean pHalfHeart, boolean pBlinking) {
        if (!pHardcore) {
            if (pHalfHeart) {
                return pBlinking ? this.halfBlinking : this.half;
            } else {
                return pBlinking ? this.fullBlinking : this.full;
            }
        } else if (pHalfHeart) {
            return pBlinking ? this.hardcoreHalfBlinking : this.hardcoreHalf;
        } else {
            return pBlinking ? this.hardcoreFullBlinking : this.hardcoreFull;
        }
    }
    
    /**
     * Returns the {@link HeartType} based on the player's status effects.
     * <p>
     * @return the {@link HeartType} based on the player's status effects.
     *
     * @param pPlayer the player for which to determine the HeartType.
     */
    public static HeartType forPlayer(Player pPlayer) {
        HeartType gui$hearttype;
        if (pPlayer.hasEffect(MobEffects.POISON)) {
            gui$hearttype = POISIONED;
        } else if (pPlayer.hasEffect(MobEffects.WITHER)) {
            gui$hearttype = WITHERED;
        } else if (pPlayer.isFullyFrozen()) {
            gui$hearttype = FROZEN;
        } else {
            gui$hearttype = NORMAL;
        }
        
        return gui$hearttype;
    }
    
    public static void renderHeart(GuiGraphics pGuiGraphics, HeartType pHeartType, int pX, int pY, boolean pHardcore, boolean pHalfHeart, boolean pBlinking) {
        pGuiGraphics.blitSprite(pHeartType.getSprite(pHardcore, pBlinking, pHalfHeart), pX, pY, 11, 11);
    }
}
