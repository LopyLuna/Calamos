package uwu.lopyluna.calamos.client.wings;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.ModArmorLayers;
import uwu.lopyluna.calamos.elements.items.wings.WingsItem;

import java.util.List;


public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    
    private static final List<String> devNames = List.of("LopyLuna", "_Pouffy_");


    private static ResourceLocation textureLocation(String name) {
        if (!devNames.contains(name))
            return new ResourceLocation(CalamosMod.MODID, "textures/entity/wings/default.png");
        return switch (name) {
            case "LopyLuna" -> new ResourceLocation(CalamosMod.MODID, "textures/entity/wings/luna.png");
            case "_Pouffy_" -> new ResourceLocation(CalamosMod.MODID, "textures/entity/wings/pouffy.png");
            default -> new ResourceLocation(CalamosMod.MODID, "textures/entity/wings/default.png");
        };
    }
    private final WingsModel<T> wingsModel;
    public WingsLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.wingsModel = new WingsModel<>(pModelSet.bakeLayer(ModArmorLayers.WINGS));
    }
    
    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount,
                       float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.getPose() == Pose.SLEEPING)
            return;

        WingsItem item = WingsItem.getWornBy(pLivingEntity);
        if (item == null)
            return;
        
        M entityModel = getParentModel();
        
        if (!(entityModel instanceof HumanoidModel))
            return;
        
        HumanoidModel<?> model = (HumanoidModel<?>) entityModel;

        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlot.CHEST);


        ResourceLocation resourcelocation;
        resourcelocation = getTexture(pLivingEntity);
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, -0.325F, 0.125F);
        this.wingsModel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(
                pBuffer, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil()
        );
        this.wingsModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        
    }
    
    /**
     * Gets the texture to use with this WingsLayer.
     * This assumes the default Wings model.
     *
     * @param entity The entity being rendered.
     * @return The texture.
     */
    public ResourceLocation getTexture(T entity) {
        if (entity instanceof Player player)
            return textureLocation(player.getScoreboardName());
        return textureLocation("default");
    }
    public static void registerOnAll(EntityRenderDispatcher renderManager, EntityModelSet modelSet) {
        for (EntityRenderer<? extends Player> renderer : renderManager.getSkinMap().values())
            registerOn(renderer, modelSet);
        for (EntityRenderer<?> renderer : renderManager.renderers.values())
            registerOn(renderer, modelSet);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void registerOn(EntityRenderer<?> entityRenderer, EntityModelSet modelSet) {
        if (!(entityRenderer instanceof LivingEntityRenderer<?, ?> livingRenderer))
            return;
        if (!(livingRenderer.getModel() instanceof HumanoidModel))
            return;
        WingsLayer<?, ?> layer = new WingsLayer<>(livingRenderer, modelSet);
        livingRenderer.addLayer((WingsLayer) layer);
    }
}
