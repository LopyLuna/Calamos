package uwu.lopyluna.calamos.elements.entity.eye;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import uwu.lopyluna.calamos.CalamosMod;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class EyeRenderer extends MobRenderer<EyeEntity, EyeModel<EyeEntity>> {
    public EyeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EyeModel<>(pContext.bakeLayer(EyeModel.LAYER_LOCATION)), 0.25F);
    }

    @Override
    protected int getBlockLightLevel(EyeEntity entity, BlockPos blockPos) {
        return 15;
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(EyeEntity entity) {
        return new ResourceLocation(CalamosMod.MODID, "textures/entity/eye/eye.png");
    }
}
