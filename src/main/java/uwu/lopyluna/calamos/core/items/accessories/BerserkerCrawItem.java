package uwu.lopyluna.calamos.core.items.accessories;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.model.item.CurioModel;
import uwu.lopyluna.calamos.client.model.item.IRenderableCurio;

import java.util.List;

@SuppressWarnings({"all"})
public class BerserkerCrawItem extends Item implements IRenderableCurio {

    public BerserkerCrawItem() {
        super(new Item.Properties().stacksTo(1));
    }

    ResourceLocation CRAW_DAMAGE_BONUS = CalamosMod.asResource("attack_damage.berserker_craw");
    ResourceLocation CRAW_DEFENCE_BONUS = CalamosMod.asResource("defence.berserker_craw");


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() != null) {
            LivingEntity livingEntity = slotContext.entity();
            CuriosApi.getCuriosInventory(livingEntity).ifPresent(handler -> {
                AttributeInstance damage = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
                AttributeInstance defence = livingEntity.getAttribute(Attributes.ARMOR);
                float healthPercentage = livingEntity.getHealth() / livingEntity.getMaxHealth();

                if (damage != null && defence != null) {
                    if (healthPercentage <= 0.25f) {
                        damage.removeModifier(CRAW_DAMAGE_BONUS);
                        damage.addTransientModifier(new AttributeModifier(
                                CRAW_DAMAGE_BONUS,
                                1.1,
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
                    }
                    if (healthPercentage > 0.25f) {
                        defence.removeModifier(CRAW_DEFENCE_BONUS);
                        defence.addTransientModifier(new AttributeModifier(
                                CRAW_DEFENCE_BONUS,
                                1.1,
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
                    }
                }
            });
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        AttributeModifier damage = new AttributeModifier(CRAW_DAMAGE_BONUS, 1.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        AttributeModifier defence = new AttributeModifier(CRAW_DEFENCE_BONUS, 1.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        tooltipComponents.add(Component.translatable("calamos.tooltip.condition.below_health_percent", 25).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(CommonComponents.space().append(Attributes.ATTACK_DAMAGE.value().toComponent(damage, tooltipFlag).withStyle(ChatFormatting.BLUE)));
        tooltipComponents.add(Component.translatable("calamos.tooltip.condition.above_health_percent", 25).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(CommonComponents.space().append(Attributes.ARMOR.value().toComponent(defence, tooltipFlag).withStyle(ChatFormatting.BLUE)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        CurioModel model = getModel(stack);

        matrixStack.pushPose();

        LivingEntity entity = slotContext.entity();

        model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        ICurioRenderer.translateIfSneaking(matrixStack, entity);

        if (entity.isCrouching()) {
            matrixStack.translate(0.0D, -0.2D, 0.0D);
        }

        ICurioRenderer.followBodyRotations(entity, model);

        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(getTexture(stack)), stack.hasFoil());

        model.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);

        matrixStack.scale(2F, 2F, 2F);

        matrixStack.popPose();
    }

    @Override
    public LayerDefinition constructLayerDefinition() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.4F), 0.0F);
        PartDefinition root = mesh.getRoot();

        PartDefinition Craw = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition beak = Craw.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(6, 16).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition eyesocket = Craw.addOrReplaceChild("eyesocket", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -4.0F, 1.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 27).addBox(-1.0F, -4.0F, 0.0F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 26).addBox(-1.0F, -2.0F, 0.0F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, -4.0F));

        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public List<String> headParts() {
        return Lists.newArrayList("head");
    }

    @Override
    public List<String> hiddenLimbs() {
        return Lists.newArrayList("head");
    }

}
