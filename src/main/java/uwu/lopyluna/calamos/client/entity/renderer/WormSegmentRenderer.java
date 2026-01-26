package uwu.lopyluna.calamos.client.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.entity.model.WormSegment;
import uwu.lopyluna.calamos.core.entity.WormPart;

public class WormSegmentRenderer extends LivingEntityRenderer<WormPart, WormSegment<WormPart>> {
    public WormSegmentRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WormSegment<>(pContext.bakeLayer(WormSegment.LAYER_LOCATION)), 3.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(WormPart pEntity) {
        return CalamosMod.asResource("textures/entity/worm_segment.png");
    }

}
