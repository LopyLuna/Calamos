package uwu.lopyluna.calamos.core.entity.dynamite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import uwu.lopyluna.calamos.CalamosMod;

/**
 * Made with Blockbench 4.9.4
 * Exported for Minecraft version 1.17 or later with Mojang mappings
 * Paste this class into your mod and generate all required imports
 * @author lopyluna
 */
public class DynamiteModel<T extends DynamiteEntity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(CalamosMod.asResource("dynamite"), "main");
	private final ModelPart dynamite;

	public DynamiteModel(ModelPart root) {
		this.dynamite = root.getChild("dynamite");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		
		PartDefinition dynamite = partdefinition.addOrReplaceChild("dynamite", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		dynamite.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}
}
