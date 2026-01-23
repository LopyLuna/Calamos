package uwu.lopyluna.calamos.client.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.entity.model.WormHead;
import uwu.lopyluna.calamos.elements.entity.Worm;

public class WormHeadRenderer extends MobRenderer<Worm, WormHead<Worm>> {
    public WormHeadRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WormHead<>(pContext.bakeLayer(WormHead.LAYER_LOCATION)), 3.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Worm pEntity) {
        return CalamosMod.asResource("textures/entity/worm_head.png");
    }
}
