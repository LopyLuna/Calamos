package uwu.lopyluna.calamos.client.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import uwu.lopyluna.calamos.elements.entity.machina.pestis_infection.PestisPlayerEntity;

public class PestisPlayerItemInHandLayer<T extends PestisPlayerEntity, M extends EntityModel<T> & ArmedModel & HeadedModel> extends ItemInHandLayer<T, M> {
    private final ItemInHandRenderer itemInHandRenderer;
    private static final float X_ROT_MIN = (float) (-Math.PI / 6);
    private static final float X_ROT_MAX = (float) (Math.PI / 2);
    
    public PestisPlayerItemInHandLayer(RenderLayerParent<T, M> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer, pItemInHandRenderer);
        this.itemInHandRenderer = pItemInHandRenderer;
    }
    
    @Override
    protected void renderArmWithItem(
            LivingEntity pLivingEntity,
            ItemStack pItemStack,
            ItemDisplayContext pDisplayContext,
            HumanoidArm pArm,
            PoseStack pPoseStack,
            MultiBufferSource pBuffer,
            int pPackedLight
    ) {
        if (pItemStack.is(Items.SPYGLASS) && pLivingEntity.getUseItem() == pItemStack && pLivingEntity.swingTime == 0) {
            this.renderArmWithSpyglass(pLivingEntity, pItemStack, pArm, pPoseStack, pBuffer, pPackedLight);
        } else {
            super.renderArmWithItem(pLivingEntity, pItemStack, pDisplayContext, pArm, pPoseStack, pBuffer, pPackedLight);
        }
    }
    
    private void renderArmWithSpyglass(
            LivingEntity pEntity, ItemStack pStack, HumanoidArm pArm, PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight
    ) {
        pPoseStack.pushPose();
        ModelPart modelpart = this.getParentModel().getHead();
        float f = modelpart.xRot;
        modelpart.xRot = Mth.clamp(modelpart.xRot, (float) (-Math.PI / 6), (float) (Math.PI / 2));
        modelpart.translateAndRotate(pPoseStack);
        modelpart.xRot = f;
        CustomHeadLayer.translateToHead(pPoseStack, false);
        boolean flag = pArm == HumanoidArm.LEFT;
        pPoseStack.translate((flag ? -2.5F : 2.5F) / 16.0F, -0.0625F, 0.0F);
        this.itemInHandRenderer.renderItem(pEntity, pStack, ItemDisplayContext.HEAD, false, pPoseStack, pBuffer, pCombinedLight);
        pPoseStack.popPose();
    }
}
