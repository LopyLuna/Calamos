package uwu.lopyluna.calamos.elements.entity.wildfire;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class WildfireRenderer extends MobRenderer<WildfireEntity, WildfireModel<WildfireEntity>> {
    public static final float SCALE = 1.6F;
    
    public WildfireRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WildfireModel<>(pContext.bakeLayer(WildfireModel.LAYER_LOCATION)), 0.35F);
    }
    
    
    @Override
    protected int getBlockLightLevel(WildfireEntity wildfire, BlockPos blockPos) {
        return 15;
    }
    
    @Override
    protected void scale(WildfireEntity wildfire, PoseStack poseStack, float f) {
        poseStack.scale(SCALE, SCALE, SCALE);
    }
    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(WildfireEntity entity) {
        return new ResourceLocation("calamos", "textures/entity/wildfire/wildfire.png");
    }
}
