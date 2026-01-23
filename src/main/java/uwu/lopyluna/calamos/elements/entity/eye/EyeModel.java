package uwu.lopyluna.calamos.elements.entity.eye;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
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
public class EyeModel<T extends EyeEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(CalamosMod.asResource("eye"), "main");
	private final ModelPart root;
	private final ModelPart eye;

	public EyeModel(ModelPart root) {
		this.root = root.getChild("root");

		this.eye = this.root.getChild("eye");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition eye = root.addOrReplaceChild("eye", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = eye.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 39).addBox(-6.5F, -12.5F, -0.5F, 13.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition axis_y = root.addOrReplaceChild("axis_y", CubeListBuilder.create().texOffs(60, 19).addBox(-0.5F, -19.5F, -0.5F, 1.0F, 44.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition axis_x = axis_y.addOrReplaceChild("axis_x", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = axis_x.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 2).addBox(-0.5F, -30.5F, -0.5F, 1.0F, 61.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.5708F));

		PartDefinition north = axis_x.addOrReplaceChild("north", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = north.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-24.5F, -15.5F, -0.5F, 23.0F, 14.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-24.5F, 1.5F, -0.5F, 23.0F, 19.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition south = axis_x.addOrReplaceChild("south", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = south.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-24.5F, -15.5F, 0.5F, 23.0F, 14.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-24.5F, 1.5F, 0.5F, 23.0F, 19.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition axis_z = axis_y.addOrReplaceChild("axis_z", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = axis_z.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 2).addBox(-0.5F, -30.5F, -0.5F, 1.0F, 61.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition west = axis_z.addOrReplaceChild("west", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = west.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 15).addBox(-24.5F, 1.5F, 0.5F, 23.0F, 19.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-24.5F, -15.5F, 0.5F, 23.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition east = axis_z.addOrReplaceChild("east", CubeListBuilder.create().texOffs(0, 0).addBox(-24.5F, -15.5F, 0.0F, 23.0F, 14.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-24.5F, 1.5F, 0.0F, 23.0F, 19.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		this.root.getAllParts().forEach(ModelPart::resetPose);

		this.eye.yRot = headYaw * 0.017453292F;
		this.eye.xRot = headPitch * 0.017453292F;


		this.animate(entity.idleAnimationState, EyeAnimation.axis_x, ageInTicks, 1.0F);
		this.animate(entity.idleAnimationState, EyeAnimation.axis_y, ageInTicks, 1.0F);
		this.animate(entity.idleAnimationState, EyeAnimation.axis_z, ageInTicks, 1.0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
