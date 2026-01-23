package uwu.lopyluna.calamos.client.model.item;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class CalamosArmorModel extends HumanoidModel<LivingEntity> {
    public EquipmentSlot slot;
    public ModelPart root, head, body, leftArm, rightArm, leggings, leftLegging, rightLegging, leftFoot, rightFoot;

    public CalamosArmorModel(ModelPart root) {
        super(root);
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leggings = root.getChild("leggings");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLegging = root.getChild("left_legging");
        this.rightLegging = root.getChild("right_legging");
        this.leftFoot = root.getChild("left_foot");
        this.rightFoot = root.getChild("right_foot");
    }

    public static PartDefinition createHumanoidAlias(MeshDefinition mesh) {
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("leggings", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("head", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_legging", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_foot", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_legging", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_foot", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", new CubeListBuilder(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", new CubeListBuilder(), PartPose.ZERO);
        return root;
    }

    public static LayerDefinition createArmorModel(ICalamosArmorModelBuilder modelBuilder) {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0), 0);
        PartDefinition root = createHumanoidAlias(mesh);
        PartDefinition body = root.getChild("body");
        PartDefinition leggings = root.getChild("leggings");
        PartDefinition right_legging = root.getChild("right_legging");
        PartDefinition left_legging = root.getChild("left_legging");
        PartDefinition right_foot = root.getChild("right_foot");
        PartDefinition left_foot = root.getChild("left_foot");
        PartDefinition right_arm = root.getChild("right_arm");
        PartDefinition left_arm = root.getChild("left_arm");
        PartDefinition head = root.getChild("head");
        return modelBuilder.createArmorLayer(mesh, root, body, leggings, right_legging, left_legging, right_foot, left_foot, right_arm, left_arm, head);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return slot == EquipmentSlot.HEAD ? ImmutableList.of(head) : ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        if (slot == EquipmentSlot.CHEST) {
            return ImmutableList.of(body, leftArm, rightArm);
        } else if (slot == EquipmentSlot.LEGS) {
            return ImmutableList.of(leftLegging, rightLegging, leggings);
        } else if (slot == EquipmentSlot.FEET) {
            return ImmutableList.of(leftFoot, rightFoot);
        } else return ImmutableList.of();
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int p_350361_) {
        if (slot == EquipmentSlot.LEGS) {  //I don't know why this is needed, but it is.
            this.leggings.copyFrom(this.body);
            this.leftLegging.copyFrom(this.leftLeg);
            this.rightLegging.copyFrom(this.rightLeg);
        }
        super.renderToBuffer(matrixStack, vertexConsumer, packedLight, packedOverlay, p_350361_);
    }

    public void copyFromDefault(HumanoidModel model) {
        leggings.copyFrom(model.body);
        body.copyFrom(model.body);
        head.copyFrom(model.head);
        leftArm.copyFrom(model.leftArm);
        rightArm.copyFrom(model.rightArm);
        leftLegging.copyFrom(leftLeg);
        rightLegging.copyFrom(rightLeg);
        leftFoot.copyFrom(leftLeg);
        rightFoot.copyFrom(rightLeg);
    }

    public interface ICalamosArmorModelBuilder {
        LayerDefinition createArmorLayer(MeshDefinition mesh, PartDefinition root, PartDefinition body, PartDefinition leggings, PartDefinition right_legging, PartDefinition left_legging, PartDefinition right_foot, PartDefinition left_foot, PartDefinition right_arm, PartDefinition left_arm, PartDefinition head);
    }
}
