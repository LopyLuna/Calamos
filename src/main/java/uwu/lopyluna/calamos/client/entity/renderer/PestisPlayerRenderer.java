package uwu.lopyluna.calamos.client.entity.renderer;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import uwu.lopyluna.calamos.client.entity.layer.PestisCapeLayer;
import uwu.lopyluna.calamos.client.entity.layer.PestisPlayerItemInHandLayer;
import uwu.lopyluna.calamos.client.entity.model.PestisPlayer;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;
@OnlyIn(Dist.CLIENT)
public class PestisPlayerRenderer extends LivingEntityRenderer<PestisPlayerEntity, PestisPlayer<PestisPlayerEntity>> {
    public PestisPlayerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PestisPlayer<>(pContext.bakeLayer(PestisPlayerEntity.shouldUseSlimModel() ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), PestisPlayerEntity.shouldUseSlimModel()), 0.5F);
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        new HumanoidArmorModel(pContext.bakeLayer(PestisPlayerEntity.shouldUseSlimModel() ? ModelLayers.PLAYER_SLIM_INNER_ARMOR : ModelLayers.PLAYER_INNER_ARMOR)),
                        new HumanoidArmorModel(pContext.bakeLayer(PestisPlayerEntity.shouldUseSlimModel() ? ModelLayers.PLAYER_SLIM_OUTER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR)),
                        pContext.getModelManager()
                )
        );
        this.addLayer(new PestisPlayerItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, pContext.getModelSet()));
        this.addLayer(new PestisCapeLayer(this));
    }
    
    @Override
    public ResourceLocation getTextureLocation(PestisPlayerEntity pEntity) {
        return pEntity.getSkin().texture();
    }
}
