package uwu.lopyluna.calamos.core.entity.wildfire;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.CalamosMod;

/**
 * Made with Blockbench 4.9.4
 * Exported for Minecraft version 1.17 or later with Mojang mappings
 * Paste this class into your mod and generate all required imports
 * @author lopyluna
 */
@SuppressWarnings({"unused","all"})
public class WildfireModel<T extends WildfireEntity> extends HierarchicalModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(CalamosMod.asResource("wildfire"), "main");
	private final ModelPart root;

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart shields;

	private final ModelPart[] shieldParts;

	public WildfireModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.root = root;

		this.head = this.root.getChild("head");
		this.body = this.root.getChild("body");
		this.shields = this.root.getChild("shields");

		this.shieldParts = new ModelPart[]{
				this.shields.getChild("axis_z").getChild("north_shield"),
				this.shields.getChild("axis_x").getChild("east_shield"),
				this.shields.getChild("axis_z").getChild("south_shield"),
				this.shields.getChild("axis_x").getChild("west_shield"),
		};
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		//GROUPS
		PartDefinition shields = partdefinition.addOrReplaceChild("shields", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition axis_x = shields.addOrReplaceChild("axis_x", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition west_shield = axis_x.addOrReplaceChild("west_shield", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition east_shield = axis_x.addOrReplaceChild("east_shield", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition axis_z = shields.addOrReplaceChild("axis_z", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition south_shield = axis_z.addOrReplaceChild("south_shield", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition north_shield = axis_z.addOrReplaceChild("north_shield", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		//CUBES
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, -20.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition north_shield_cube = north_shield.addOrReplaceChild("north_shield_cube", CubeListBuilder.create().texOffs(0, 0).addBox(11.5596F, 3.8935F, -8.0F, 2.0F, 24.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 1.4835F, -1.5708F));
		PartDefinition east_shield_cube = east_shield.addOrReplaceChild("east_shield_cube", CubeListBuilder.create().texOffs(40, 0).addBox(11.5596F, 3.8935F, -8.0F, 2.0F, 24.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -3.0543F));
		PartDefinition south_shield_cube = south_shield.addOrReplaceChild("south_shield_cube", CubeListBuilder.create().texOffs(20, 24).addBox(11.5596F, 3.8935F, -8.0F, 2.0F, 24.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, -1.4835F, -1.5708F));
		PartDefinition west_shield_cube = west_shield.addOrReplaceChild("west_shield_cube", CubeListBuilder.create().texOffs(0, 48).addBox(11.5596F, 3.8935F, -8.0F, 2.0F, 24.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T wildfire, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.root.getAllParts().forEach(ModelPart::resetPose);

		this.head.yRot = headYaw * 0.017453292F;
		this.head.xRot = headPitch * 0.017453292F;

		this.animateWalk(WildfireAnimations.walk, limbAngle, limbDistance, 2.0F, 2.0F);
		this.animate(wildfire.idleAnimationState, WildfireAnimations.Idle, animationProgress, 1.0F);
		this.animate(wildfire.idleShieldAnimationState, WildfireAnimations.idle_shield, animationProgress, 1.0F);
		this.animate(wildfire.enclosedShieldAnimationState, WildfireAnimations.enclosed_shields, animationProgress, 1.0F);
		this.animate(wildfire.attackAnimationState, WildfireAnimations.attack, animationProgress, 1.0F);
		this.animate(wildfire.shockwaveAnimationState, WildfireAnimations.shockwave, animationProgress, 1.0F);

		int activeshieldsCount = wildfire.getActiveShieldsCount();

		for (int i = 0; i < WildfireEntity.defaultActiveShieldsCount; ++i) {
			if (i + 1 > activeshieldsCount) {
				this.shieldParts[i].visible = false;
				continue;
			} else {
				this.shieldParts[i].visible = true;
			}
		}
	}


	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		shields.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}

	@Override
	public @NotNull ModelPart root() {
		return this.root;
	}
}
