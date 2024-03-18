package uwu.lopyluna.calamos.elements.entity.wildfire;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.data.ModelData;

import static uwu.lopyluna.calamos.CalamosMod.MODID;

public class WildfireModel<T extends WildfireEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "wildfire"), "main");
    private static final String MODEL_PART_HEAD = "head";
    private static final String MODEL_PART_HELMET = "helmet";
    private static final String MODEL_PART_BODY = "body";
    private static final String MODEL_PART_FRONT_SHIELD = "frontShield";
    private static final String MODEL_PART_RIGHT_SHIELD = "rightShield";
    private static final String MODEL_PART_BACK_SHIELD = "BackShield";
    private static final String MODEL_PART_LEFT_SHIELD = "LeftShield";
    
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart helmet;
    private final ModelPart body;
    private final ModelPart frontShield;
    private final ModelPart rightShield;
    private final ModelPart backShield;
    private final ModelPart leftShield;
    
    private final ModelPart[] shields;
    public WildfireModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
        this.head = this.root.getChild(MODEL_PART_HEAD);
        this.helmet = this.head.getChild(MODEL_PART_HELMET);
        this.body = this.root.getChild(MODEL_PART_BODY);
        this.frontShield = this.root.getChild(MODEL_PART_FRONT_SHIELD);
        this.rightShield = this.root.getChild(MODEL_PART_RIGHT_SHIELD);
        this.backShield = this.root.getChild(MODEL_PART_BACK_SHIELD);
        this.leftShield = this.root.getChild(MODEL_PART_LEFT_SHIELD);
        
        this.shields = new ModelPart[]{
                this.frontShield,
                this.rightShield,
                this.backShield,
                this.leftShield,
        };
    }
    
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        
        partdefinition.addOrReplaceChild(MODEL_PART_HEAD, CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 3.0F, 0.0F));
        
        PartDefinition head = partdefinition.getChild(MODEL_PART_HEAD);
        head.addOrReplaceChild(MODEL_PART_HELMET, CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.rotation(0.0F, 0.0F, 0.0F));
        
        partdefinition.addOrReplaceChild(MODEL_PART_BODY, CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 21.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 3.0F, 0.0F));
        
        partdefinition.addOrReplaceChild(MODEL_PART_FRONT_SHIELD, CubeListBuilder.create().texOffs(17, 0).addBox(-5.0F, 1.0F, -10.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild(MODEL_PART_RIGHT_SHIELD, CubeListBuilder.create().texOffs(17, 0).addBox(-5.0F, 1.0F, -10.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.2618F, -1.5708F, 0.0F));
        partdefinition.addOrReplaceChild(MODEL_PART_BACK_SHIELD, CubeListBuilder.create().texOffs(17, 0).addBox(-5.0F, 1.0F, -10.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.2618F, 3.1416F, 0.0F));
        partdefinition.addOrReplaceChild(MODEL_PART_LEFT_SHIELD, CubeListBuilder.create().texOffs(17, 0).addBox(-5.0F, 1.0F, -10.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.2618F, 1.5708F, 0.0F));
        
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    
    @Override
    public void setupAnim(T wildfire, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        int activeShieldsCount = wildfire.getActiveShieldsCount();
        float rotationSpeedMultiplier = Math.max(1, WildfireEntity.defaultActiveShieldsCount - activeShieldsCount);
        float baseRotationUnit = (2.0F * (float) Math.PI) / activeShieldsCount;
        // I don't know why, but this is just choppy and laggy in the game
        //float bodyCounterRotation = (float) Math.toRadians((wildfire.prevBodyYaw * -1.0F));
        float bodyCounterRotation = 0.0F;
        float additionalShieldRotation = (animationProgress * 0.1F * rotationSpeedMultiplier) % (2.0F * (float) Math.PI);
        
        for (int i = 0; i < WildfireEntity.defaultActiveShieldsCount; ++i) {
            if (i > activeShieldsCount) {
                this.shields[i].visible = false;
                continue;
            } else {
                this.shields[i].visible = true;
            }
            
            this.shields[i].yRot = (bodyCounterRotation + (baseRotationUnit * i)) + additionalShieldRotation;
        }
        
        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * 0.017453292F;
    }
    
    @Override
    public ModelPart root() {
        return this.root;
    }
}
