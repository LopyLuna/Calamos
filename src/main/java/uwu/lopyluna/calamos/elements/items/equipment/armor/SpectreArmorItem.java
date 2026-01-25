package uwu.lopyluna.calamos.elements.items.equipment.armor;

import com.google.common.collect.Lists;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ModelRegistry;
import uwu.lopyluna.calamos.client.model.item.CalamosArmorModel;
import uwu.lopyluna.calamos.datagen.ModTags;
import uwu.lopyluna.calamos.elements.ModArmorMaterials;
import uwu.lopyluna.calamos.elements.ModAttributes;
import uwu.lopyluna.calamos.elements.entity.orb.LifestealOrb;

import java.util.List;
import java.util.Map;

public class SpectreArmorItem extends CalamosArmorItem {

    public final boolean hood;

    private SpectreArmorItem(Type pType, Properties pProperties, boolean hood) {
        super(ModArmorMaterials.SPECTRE, pType, pProperties.stacksTo(1));
        this.hood = hood;
    }

    public static SpectreArmorItem helmet(Properties pProperties, boolean hood) {
        return new SpectreArmorItem(Type.HELMET, pProperties, hood);
    }

    public static SpectreArmorItem armor(Type pType, Properties pProperties) {
        return new SpectreArmorItem(pType, pProperties, false);
    }

    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        if (this.type == Type.HELMET) {
            if (!hood) {
                builder.add(ModAttributes.MAGIC_CRIT_CHANCE, new AttributeModifier(CalamosMod.asResource("crit_chance.magic.helmet"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HEAD);
                builder.add(ModAttributes.MAGIC_DAMAGE, new AttributeModifier(CalamosMod.asResource("magic_damage.helmet"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HEAD);
                builder.add(ModAttributes.MANA_COST_REDUCTION, new AttributeModifier(CalamosMod.asResource("mana_cost_reduction.helmet"), 0.13, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HEAD);
                builder.add(ModAttributes.MAX_MANA, new AttributeModifier(CalamosMod.asResource("max_mana.helmet"), 60, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD);
            }
        }
        if (this.type == Type.CHESTPLATE) {
            builder.add(ModAttributes.MAGIC_CRIT_CHANCE, new AttributeModifier(CalamosMod.asResource("crit_chance.magic.chestplate"), 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.CHEST);
            builder.add(ModAttributes.MAGIC_DAMAGE, new AttributeModifier(CalamosMod.asResource("magic_damage.chestplate"), 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.CHEST);
        }
        if (this.type == Type.LEGGINGS) {
            builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(CalamosMod.asResource("speed.leggings"), 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.LEGS);
            builder.add(ModAttributes.MAGIC_DAMAGE, new AttributeModifier(CalamosMod.asResource("magic_damage.leggings"), 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.LEGS);
        }
        return builder.build();
    }

    @Override
    public float modifyOutgoingAttackDamage(LivingEntity entity, LivingEntity target, DamageSource source, float baseAmount) {
        float magicBonus = 1;
        if (this.type == Type.HELMET) {
            magicBonus = 1.1f;
        }
        if (this.type == Type.CHESTPLATE) {
            magicBonus = 1.07f;
        }
        if (this.type == Type.LEGGINGS) {
            magicBonus = 1.08f;
        }
        if (source.is(ModTags.modDamageTag("magic"))) {
            return (baseAmount * magicBonus);
        }
        return baseAmount;
    }

    @Override
    public void afterOutgoingAttack(LivingEntity entity, LivingEntity victim, DamageSource source, float amount) {
        if (this.type == Type.HELMET && source.is(ModTags.modDamageTag("magic"))) {
            if (this.hood) {
                float toHeal = amount * 0.2f;
                LifestealOrb orb = new LifestealOrb(entity.level);
                orb.setOwner(entity);
                orb.setPos(victim.getEyePosition());
                orb.setHealingAmount(toHeal);

                entity.level.addFreshEntity(orb);
            }
        }
    }

    @Override
    public Map<EquipmentSlot, List<String>> hiddenLimbs() {
        return Map.of(EquipmentSlot.HEAD, Lists.newArrayList("head"));
    }

    @Override
    public @Nullable CalamosArmorModel getModel() {
        return ModelRegistry.SPECTRE_ARMOR;
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return CalamosMod.asResource("textures/armor/spectre.png");
    }

    public static class Model extends CalamosArmorModel {
        public static ModelLayerLocation LAYER = new ModelLayerLocation(CalamosMod.asResource("spectre_armor"), "main");

        public Model(ModelPart root) {
            super(root);
        }

        @Override
        public void copyFromDefault(HumanoidModel model) {
            super.copyFromDefault(model);
        }

        @Override
        public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
            super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0), 0);
            PartDefinition root = createHumanoidAlias(mesh);

            PartDefinition body = root.getChild("body");

            PartDefinition leggings = root.getChild("leggings");

            PartDefinition right_legging = root.getChild("right_legging");

            PartDefinition right_foot = root.getChild("right_foot");

            PartDefinition right_arm = root.getChild("right_arm");

            PartDefinition left_legging = root.getChild("left_legging");

            PartDefinition left_foot = root.getChild("left_foot");

            PartDefinition left_arm = root.getChild("left_arm");

            PartDefinition head = root.getChild("head");

            PartDefinition shadow = head.addOrReplaceChild("shadow", CubeListBuilder.create().texOffs(44, 12).addBox(-4.0F, -3.9F, -2.75F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

            PartDefinition hood = head.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(0, 16).addBox(6.91F, -9.05F, -5.95F, 0.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(7, 23).addBox(6.89F, -9.05F, -5.95F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 16).addBox(14.89F, -9.05F, -5.95F, 0.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.9F, 0.1F, 8.1F, -0.0873F, 0.0F, 0.0F));

            PartDefinition hood_back = head.addOrReplaceChild("hood_back", CubeListBuilder.create().texOffs(8, 18).addBox(-1.0F, -9.0F, -6.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(1, 1).addBox(-1.0F, -9.0F, -5.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 2.0F, 0.2182F, 0.0F, 0.0F));

            return LayerDefinition.create(mesh, 128, 128);
        }
    }
}
