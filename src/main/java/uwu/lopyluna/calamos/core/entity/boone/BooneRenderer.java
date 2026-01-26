package uwu.lopyluna.calamos.core.entity.boone;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.lopyluna.calamos.utilities.ModUtils;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BooneRenderer extends MobRenderer<BooneEntity, BooneModel<BooneEntity>> {
    public BooneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BooneModel<>(pContext.bakeLayer(BooneModel.LAYER_LOCATION)), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(BooneEntity pEntity) {
        return ModUtils.location("textures/entity/boone/boone.png");
    }
}
