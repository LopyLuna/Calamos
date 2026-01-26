package uwu.lopyluna.calamos.core.items.equipment.wings;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.model.item.CurioModel;
import uwu.lopyluna.calamos.client.model.item.IRenderableCurio;
import uwu.lopyluna.calamos.elements.ModAttributes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WingsItem extends Item implements Equipable, IRenderableCurio {
    public WingsItem() {
        super(new Item.Properties().stacksTo(1));
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }
    @Override
    public Holder<SoundEvent> getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_ELYTRA;
    }
    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return this.swapWithEquipmentSlot(this, pLevel, pPlayer, pHand);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attributes = LinkedHashMultimap.create();
        attributes.put(ModAttributes.MAX_FLIGHT, new AttributeModifier(CalamosMod.asResource("wings.max_flight.curio"), 30, AttributeModifier.Operation.ADD_VALUE));
        return attributes;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        AttributeModifier flight = new AttributeModifier(CalamosMod.asResource("wings.max_flight.curio"), 30, AttributeModifier.Operation.ADD_VALUE);
        tooltipComponents.add(Component.translatable("calamos.tooltip.condition.equipped").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(CommonComponents.space().append(ModAttributes.MAX_FLIGHT.value().toComponent(flight, tooltipFlag).withStyle(ChatFormatting.BLUE)));
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(ModAttributes.MAX_FLIGHT, new AttributeModifier(CalamosMod.asResource("wings.max_flight.chestplate"), 30, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);
        return builder.build();
    }

    private static final Map<UUID, String> devTextures = Map.of(
            UUID.fromString("ab49cc7b-53e9-424e-8fa1-778186ffae33"), "luna",
            UUID.fromString("37b8527a-8e9b-4b23-9d3b-6196c9d70551"), "pouffy"
    );

    public ResourceLocation getTexture(Entity entity, ItemStack stack) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String type = "default";
        if (entity instanceof Player player && devTextures.containsKey(player.getUUID())) {
            type = devTextures.get(player.getUUID());
        }
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/models/items/"+id.getPath()+"/" + type + ".png");
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

        matrixStack.translate(0.0D, -0.8D, 0.0D);

        if (entity.isCrouching()) {
            //matrixStack.mulPose(Axis.XP.rotation(0.5f));
            matrixStack.translate(0.0D, -0.1D, -0.35D);
        }

        ICurioRenderer.followBodyRotations(entity, model);

        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(getTexture(entity, stack)), stack.hasFoil());

        model.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);

        matrixStack.scale(2F, 2F, 2F);

        matrixStack.popPose();
    }

    @Override
    public LayerDefinition constructLayerDefinition() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.4F), 0.0F);
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.getChild("body");

        PartDefinition left = body.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.1345F, 0.0F));

        PartDefinition right = body.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition rightWing_r1 = right.addOrReplaceChild("rightWing_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, 0.0F, 0.0F, 22.0F, 39.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition rightWing_r2 = right.addOrReplaceChild("rightWing_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, 0.0F, 0.0F, 22.0F, 39.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 1.5708F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public List<String> bodyParts() {
        return Lists.newArrayList("body");
    }
}
