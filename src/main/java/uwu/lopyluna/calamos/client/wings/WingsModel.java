package uwu.lopyluna.calamos.client.wings;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import uwu.lopyluna.calamos.CalamosMod;

@OnlyIn(Dist.CLIENT)
public class WingsModel<T extends LivingEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CalamosMod.MODID, "wings"), "main");
    private final ModelPart left;
    private final ModelPart right;
    
    public WingsModel(ModelPart root) {
        this.left = root.getChild("left");
        this.right = root.getChild("right");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        
        PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.1345F, 0.0F));
        
        PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        
        PartDefinition rightWing_r1 = right.addOrReplaceChild("rightWing_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, 0.0F, 0.0F, 22.0F, 39.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 1.5708F));
        
        PartDefinition rightWing_r2 = right.addOrReplaceChild("rightWing_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, 0.0F, 0.0F, 22.0F, 39.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 1.5708F));
        
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    
    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        //float f = (float) (Math.PI / 12);
        //float f1 = (float) (-Math.PI / 12);
        //float f2 = 0.0F;
        //float f3 = 0.0F;
        //if (pEntity.isFallFlying()) {
        //    float f4 = 1.0F;
        //    Vec3 vec3 = pEntity.getDeltaMovement();
        //    if (vec3.y < 0.0) {
        //        Vec3 vec31 = vec3.normalize();
        //        f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5);
        //    }
        //
        //    f = f4 * (float) (Math.PI / 9) + (1.0F - f4) * f;
        //    f1 = f4 * (float) (-Math.PI / 2) + (1.0F - f4) * f1;
        //} else if (pEntity.isCrouching()) {
        //    f = (float) (Math.PI * 2.0 / 9.0);
        //    f1 = (float) (-Math.PI / 4);
        //    f2 = 3.0F;
        //    f3 = 0.08726646F;
        //}
        //
        //this.left.y = f2;
        //if (pEntity instanceof AbstractClientPlayer abstractclientplayer) {
        //    abstractclientplayer.elytraRotX += (f - abstractclientplayer.elytraRotX) * 0.1F;
        //    abstractclientplayer.elytraRotY += (f3 - abstractclientplayer.elytraRotY) * 0.1F;
        //    abstractclientplayer.elytraRotZ += (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
        //    this.left.xRot = abstractclientplayer.elytraRotX;
        //    this.left.yRot = abstractclientplayer.elytraRotY;
        //    this.left.zRot = abstractclientplayer.elytraRotZ;
        //} else {
        //    this.left.xRot = f;
        //    this.left.zRot = f1;
        //    this.left.yRot = f3;
        //}
        //
        //this.right.yRot = -this.left.yRot;
        //this.right.y = this.left.y;
        //this.right.xRot = this.left.xRot;
        //this.right.zRot = -this.left.zRot;
    }
}
