package uwu.lopyluna.calamos.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import uwu.lopyluna.calamos.client.entity.layer.PestisCapeLayer;
import uwu.lopyluna.calamos.client.entity.layer.PestisPlayerItemInHandLayer;
import uwu.lopyluna.calamos.client.entity.model.PestisPlayer;
import uwu.lopyluna.calamos.core.entity.machina.pestis_infection.PestisPlayerEntity;
@OnlyIn(Dist.CLIENT)
public class PestisPlayerRenderer extends LivingEntityRenderer<PestisPlayerEntity, PestisPlayer<PestisPlayerEntity>> {
    public PestisPlayerRenderer(EntityRendererProvider.Context pContext, boolean pUseSlimModel) {
        super(pContext, new PestisPlayer<>(pContext.bakeLayer(pUseSlimModel ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), pUseSlimModel), 0.5F);
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        new HumanoidArmorModel<>(pContext.bakeLayer(pUseSlimModel ? ModelLayers.PLAYER_SLIM_INNER_ARMOR : ModelLayers.PLAYER_INNER_ARMOR)),
                        new HumanoidArmorModel<>(pContext.bakeLayer(pUseSlimModel ? ModelLayers.PLAYER_SLIM_OUTER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR)),
                        pContext.getModelManager()
                )
        );
        this.addLayer(new PestisPlayerItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, pContext.getModelSet()));
        this.addLayer(new PestisCapeLayer(this));
    }

    public void render(PestisPlayerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.setModelProperties(pEntity);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    private void setModelProperties(PestisPlayerEntity pClientPlayer) {
        PlayerModel<PestisPlayerEntity> playermodel = this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.hat.visible = pClientPlayer.isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = pClientPlayer.isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            playermodel.crouching = pClientPlayer.isCrouching();
            HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(pClientPlayer, InteractionHand.MAIN_HAND);
            HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(pClientPlayer, InteractionHand.OFF_HAND);
            if (humanoidmodel$armpose.isTwoHanded()) {
                humanoidmodel$armpose1 = pClientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (pClientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = humanoidmodel$armpose;
                playermodel.leftArmPose = humanoidmodel$armpose1;
            } else {
                playermodel.rightArmPose = humanoidmodel$armpose1;
                playermodel.leftArmPose = humanoidmodel$armpose;
            }
        }
    }

    private static HumanoidModel.ArmPose getArmPose(PestisPlayerEntity pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (pPlayer.getUsedItemHand() == pHand && pPlayer.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && pHand == pPlayer.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }

                if (useanim == UseAnim.TOOT_HORN) {
                    return HumanoidModel.ArmPose.TOOT_HORN;
                }

                if (useanim == UseAnim.BRUSH) {
                    return HumanoidModel.ArmPose.BRUSH;
                }
            } else if (!pPlayer.swinging && itemstack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }
            HumanoidModel.ArmPose forgeArmPose = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(itemstack).getArmPose(pPlayer, pHand, itemstack);
            if (forgeArmPose != null) return forgeArmPose;

            return HumanoidModel.ArmPose.ITEM;
        }
    }
    
    @Override
    public ResourceLocation getTextureLocation(PestisPlayerEntity pEntity) {
        return pEntity.getSkin().texture();
    }

    protected void scale(PestisPlayerEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        float f = 0.9375F;
        pPoseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }

    protected void setupRotations(PestisPlayerEntity pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks, float p_320072_) {
        float f = pEntityLiving.getSwimAmount(pPartialTicks);
        float f1 = pEntityLiving.getViewXRot(pPartialTicks);
        if (pEntityLiving.isFallFlying()) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks, p_320072_);
            float f2 = (float)pEntityLiving.getFallFlyingTicks() + pPartialTicks;
            float f3 = Mth.clamp(f2 * f2 / 100.0F, 0.0F, 1.0F);
            if (!pEntityLiving.isAutoSpinAttack()) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(f3 * (-90.0F - f1)));
            }

            Vec3 vec3 = pEntityLiving.getViewVector(pPartialTicks);
            Vec3 vec31 = pEntityLiving.getDeltaMovementLerped(pPartialTicks);
            double d0 = vec31.horizontalDistanceSqr();
            double d1 = vec3.horizontalDistanceSqr();
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                pPoseStack.mulPose(Axis.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks, p_320072_);
            float f4 = pEntityLiving.isInWater() || pEntityLiving.isInFluidType((fluidType, height) -> pEntityLiving.canSwimInFluidType(fluidType)) ? -90.0F - pEntityLiving.getXRot() : -90.0F;
            float f5 = Mth.lerp(f, 0.0F, f4);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(f5));
            if (pEntityLiving.isVisuallySwimming()) {
                pPoseStack.translate(0.0F, -1.0F, 0.3F);
            }
        } else {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks, p_320072_);
        }
    }
}
