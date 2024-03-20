package uwu.lopyluna.calamos.elements.entity.dynamite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import uwu.lopyluna.calamos.utilities.ModUtils;

public class DynamiteRenderer extends EntityRenderer<DynamiteEntity> {
    public DynamiteRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(DynamiteEntity pEntity) {
        return ModUtils.location("textures/entity/dynamite.png");
    }

    @Override
    public boolean shouldRender(DynamiteEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }

    @Override
    public void render(DynamiteEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        pPoseStack.translate(0.0F, 0.0F, 0.0F);
        pPoseStack.scale(0.1F, 0.1F, 0.1F);

        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pEntity)));
        PoseStack.Pose pose = pPoseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();

        //North face
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, 8, -2, 0.0F, 0.125F, 0, -1, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, 8, -2, 0.125F, 0.125F, 0, -1, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, -8, -2, 0.125F, 0.5625F, 0, -1, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, -8, -2, 0.0F, 0.5625F, 0, -1, 0, pPackedLight);
        //South face
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, -8, 2, 0.0F, 0.5625F, 0, 1, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, -8, 2, 0.125F, 0.5625F, 0, 1, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, 8, 2, 0.125F, 0.125F, 0, 1, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, 8, 2, 0.0F, 0.125F, 0, 1, 0, pPackedLight);
        //West face
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, 8, -2, 0.0F, 0.125F, -1, 0, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, -8, -2, 0.0F, 0.5625F, -1, 0, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, -8, 2, 0.125F, 0.5625F, -1, 0, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, 8, 2, 0.125F, 0.125F, -1, 0, 0, pPackedLight);
        //East face
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, -8, -2, 0.0F, 0.5625F, 1, 0, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, 8, -2, 0.0F, 0.125F, 1, 0, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, 8, 2, 0.125F, 0.125F, 1, 0, 0, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, -8, 2, 0.125F, 0.5625F, 1, 0, 0, pPackedLight);
        //Top face
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, 8, 2, 0.125F, 0.0F, 0, 0, 1, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, 8, 2, 0.25F, 0.0F, 0, 0, 1, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, 8, -2, 0.25F, 0.125F, 0, 0, 1, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, 8, -2, 0.125F, 0.125F, 0, 0, 1, pPackedLight);
        //Bottom face
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, -8, -2, 0.25F, 0.0F, 0, 0, -1, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, -8, -2, 0.375F, 0.0F, 0, 0, -1, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, 2, -8, 2, 0.375F, 0.125F, 0, 0, -1, pPackedLight);
        this.vertex(matrix4f, matrix3f, vertexconsumer, -2, -8, 2, 0.25F, 0.125F, 0, 0, -1, pPackedLight);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    public void vertex(
            Matrix4f pMatrix,
            Matrix3f pNormal,
            VertexConsumer pConsumer,
            int pX,
            int pY,
            int pZ,
            float pU,
            float pV,
            int pNormalX,
            int pNormalZ,
            int pNormalY,
            int pPackedLight
    ) {
        pConsumer.vertex(pMatrix, (float)pX, (float)pY, (float)pZ)
                .color(255, 255, 255, 255)
                .uv(pU, pV)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(pPackedLight)
                .normal(pNormal, (float)pNormalX, (float)pNormalY, (float)pNormalZ)
                .endVertex();
    }
}
