package uwu.lopyluna.calamos.elements.entity.boone;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import static uwu.lopyluna.calamos.CalamosMod.MODID;

public class BooneModel<T extends BooneEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "boone"), "main");
    private static final String MODEL_PART_RIGHT_LEG = "Right_Leg";
    private static final String MODEL_PART_LEFT_LEG = "Left_Leg";
    private static final String MODEL_PART_RIGHT_FEET = "Right_Feet";
    private static final String MODEL_PART_LEFT_FEET = "Left_Feet";
    private static final String MODEL_PART_HIPS = "Hips";
    private static final String MODEL_PART_BODY = "Body";
    private static final String MODEL_PART_HEAD = "Head";
    private static final String MODEL_PART_JAW = "Jaw";
    private static final String MODEL_PART_LEFT_ARM = "Left_Arm";
    private static final String MODEL_PART_RIGHT_ARM = "Right_Arm";

    private final ModelPart root;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    //private final ModelPart rightFeet;
    //private final ModelPart leftFeet;
    private final ModelPart hips;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public BooneModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;

        this.rightLeg = this.root.getChild(MODEL_PART_RIGHT_LEG);
        //this.rightFeet = this.rightLeg.getChild(MODEL_PART_RIGHT_FEET);
        this.leftLeg = this.root.getChild(MODEL_PART_LEFT_LEG);
        //this.leftFeet = this.rightLeg.getChild(MODEL_PART_LEFT_FEET);

        this.hips = this.root.getChild(MODEL_PART_HIPS);
        this.body = this.hips.getChild(MODEL_PART_BODY);

        this.head = this.body.getChild(MODEL_PART_HEAD);
        this.jaw = this.head.getChild(MODEL_PART_JAW);

        this.rightArm = this.body.getChild(MODEL_PART_RIGHT_ARM);
        this.leftArm = this.body.getChild(MODEL_PART_LEFT_ARM);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 1.0F));

        PartDefinition rightLeg = partdefinition.addOrReplaceChild(MODEL_PART_RIGHT_LEG, CubeListBuilder.create().texOffs(208, 242).addBox(-17.0F, 2.0F, -5.0F, 12.0F, 24.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -10.0F, 9.5F));

        PartDefinition cube_r1 = rightLeg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(256, 242).addBox(-13.5F, 15.0F, -3.0F, 6.0F, 26.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(228, 36).addBox(-19.0F, 24.0F, -10.0F, 16.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(106, 214).addBox(-18.0F, 16.0F, -9.0F, 14.0F, 24.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, -9.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Right_Feet = rightLeg.addOrReplaceChild(MODEL_PART_RIGHT_FEET, CubeListBuilder.create().texOffs(192, 100).addBox(-9.0F, 0.0F, -10.0F, 18.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 26.0F, 0.0F));

        PartDefinition cube_r2 = Right_Feet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(268, 198).addBox(-14.0F, 46.0F, -30.0F, 6.0F, 6.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(240, 168).addBox(-14.0F, 31.0F, -42.0F, 6.0F, 9.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -50.0F, -9.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition leftLeg = partdefinition.addOrReplaceChild(MODEL_PART_LEFT_LEG, CubeListBuilder.create().texOffs(0, 64).addBox(5.0F, 2.0F, -5.0F, 12.0F, 24.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -10.0F, 9.5F));

        PartDefinition cube_r3 = leftLeg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(150, 254).addBox(-14.5F, 15.0F, -3.0F, 6.0F, 26.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(220, 220).addBox(-19.0F, 24.0F, -10.0F, 16.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 212).addBox(-18.0F, 16.0F, -9.0F, 14.0F, 24.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.0F, -24.0F, -9.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Left_Feet = leftLeg.addOrReplaceChild(MODEL_PART_LEFT_FEET, CubeListBuilder.create().texOffs(120, 188).addBox(-9.0F, 0.0F, -11.0F, 18.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 26.0F, 1.0F));

        PartDefinition cube_r4 = Left_Feet.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 266).addBox(-14.0F, 46.0F, -30.0F, 6.0F, 6.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(240, 58).addBox(-14.0F, 31.0F, -42.0F, 6.0F, 9.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -50.0F, -10.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition hips = partdefinition.addOrReplaceChild(MODEL_PART_HIPS, CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 10.0F));

        PartDefinition Body = hips.addOrReplaceChild(MODEL_PART_BODY, CubeListBuilder.create().texOffs(112, 112).addBox(-16.0F, 0.0F, 0.0F, 32.0F, 24.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-15.0F, -1.0F, -1.0F, 4.0F, 26.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(176, 76).addBox(-12.0F, 24.0F, 0.0F, 24.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(152, 48).addBox(-14.0F, 20.0F, -2.0F, 28.0F, 8.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(44, 238).addBox(-19.0F, 18.0F, 16.0F, 16.0F, 16.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(236, 0).addBox(-20.0F, 24.0F, 15.0F, 18.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, -5.0F));

        PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(162, 220).addBox(-8.0F, -2.0F, 7.0F, 16.0F, 21.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -3.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild(MODEL_PART_HEAD, CubeListBuilder.create().texOffs(0, 252).addBox(-6.0F, -6.0F, -11.0F, 12.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(1, 101).addBox(-6.0F, -12.0F, -11.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(253, 127).addBox(-12.0F, -12.0F, -7.0F, 24.0F, 24.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(36, 4).addBox(-2.0F, -6.0F, -12.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(246, 88).addBox(-6.5F, -6.5F, -11.5F, 13.0F, 11.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 9.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 253).addBox(-12.0F, -12.0F, 1.0F, 24.0F, 24.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r7 = Head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(56, 143).addBox(-9.0F, -6.0F, -0.5F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(174, 188).addBox(5.0F, -6.0F, -0.5F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(268, 151).addBox(5.0F, -3.0F, 18.5F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(268, 222).addBox(-7.0F, -3.0F, 18.5F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -5.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r8 = Head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(42, 212).addBox(5.0F, -10.0F, -17.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(58, 224).addBox(-7.0F, -10.0F, -17.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -5.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r9 = Head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(64, 266).addBox(5.0F, -5.0F, -12.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(186, 266).addBox(-7.0F, -5.0F, -12.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(224, 168).addBox(-8.0F, -10.0F, 8.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(256, 18).addBox(4.0F, -10.0F, 8.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -5.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Jaw = Head.addOrReplaceChild(MODEL_PART_JAW, CubeListBuilder.create().texOffs(36, 64).addBox(3.0F, -1.0F, -16.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(112, 152).addBox(-6.0F, 0.0F, -14.0F, 12.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 64).addBox(-5.0F, -1.0F, -16.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 3.0F));

        PartDefinition cube_r10 = Jaw.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(26, 0).addBox(-13.0F, -1.5F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-1.0F, -1.5F, -13.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -8.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition Left_Arm = Body.addOrReplaceChild(MODEL_PART_LEFT_ARM, CubeListBuilder.create().texOffs(176, 0).addBox(0.0F, -7.0F, -10.0F, 20.0F, 16.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(204, 136).addBox(3.0F, 9.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 64).addBox(9.0F, 25.0F, -32.0F, 16.0F, 16.0F, 48.0F, new CubeDeformation(0.0F))
                .texOffs(56, 140).addBox(-3.0F, 25.0F, -22.0F, 12.0F, 12.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(80, 64).addBox(-1.0F, 20.0F, -37.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F))
                .texOffs(60, 184).addBox(6.0F, -8.0F, -11.0F, 8.0F, 18.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(26, 11).addBox(8.0F, 4.0F, -12.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, 7.0F, 4.0F));

        PartDefinition cube_r11 = Left_Arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(98, 184).addBox(-9.0F, -7.0F, 0.5F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(148, 214).addBox(-29.0F, -3.0F, 18.5F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(207, 220).addBox(-9.0F, -3.0F, 18.5F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.0F, 8.0F, -5.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r12 = Left_Arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(56, 128).addBox(-8.0F, -10.0F, 8.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(256, 198).addBox(3.0F, -10.0F, 18.0F, 2.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 143).addBox(12.0F, -10.0F, 8.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 8.0F, -5.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Right_Arm = Body.addOrReplaceChild(MODEL_PART_RIGHT_ARM, CubeListBuilder.create().texOffs(144, 152).addBox(-20.0F, -7.0F, -10.0F, 20.0F, 16.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(192, 188).addBox(-19.0F, 9.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-25.0F, 25.0F, -32.0F, 16.0F, 16.0F, 48.0F, new CubeDeformation(0.0F))
                .texOffs(0, 128).addBox(-9.0F, 25.0F, -22.0F, 12.0F, 12.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(80, 0).addBox(-23.0F, 20.0F, -37.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F))
                .texOffs(0, 172).addBox(-14.0F, -8.0F, -11.0F, 8.0F, 18.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(26, 4).addBox(-12.0F, 4.0F, -12.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-16.0F, 7.0F, 4.0F));

        PartDefinition cube_r13 = Right_Arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(152, 0).addBox(-31.0F, -7.0F, 0.5F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(80, 0).addBox(-29.0F, -3.0F, 18.5F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(80, 64).addBox(-9.0F, -3.0F, 18.5F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 8.0F, -5.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r14 = Right_Arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 128).addBox(-8.0F, -10.0F, 8.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(38, 172).addBox(3.0F, -10.0F, 18.0F, 2.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(128, 48).addBox(12.0F, -10.0F, 8.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, 8.0F, -5.0F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        this.root.getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * 0.017453292F;

        this.animateWalk(BooneAnimation.BOONE_WALK, limbSwing, limbSwingAmount, 2.0F, 2.0F);
        this.animate(entity.idleAnimationState, BooneAnimation.BOONE_IDLE, ageInTicks, 1.0F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        hips.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
