package uwu.lopyluna.calamos.client.render.hook;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.client.model.hook.HookModel;
import uwu.lopyluna.calamos.core.entity.hook.AbstractHookEntity;

public abstract class AbstractHookRenderer<T extends AbstractHookEntity> extends EntityRenderer<T> {
    protected final EntityModel<? extends AbstractHookEntity> model;
    protected final BlockRenderDispatcher dispatcher;

    public AbstractHookRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new HookModel(pContext.bakeLayer(HookModel.LAYER_LOCATION));
        this.dispatcher = pContext.getBlockRenderDispatcher();
    }

    public AbstractHookRenderer(EntityRendererProvider.Context pContext, EntityModel<? extends AbstractHookEntity> model) {
        super(pContext);
        this.model = model;
        this.dispatcher = pContext.getBlockRenderDispatcher();
    }

    public abstract BakedModel getChain(T entity);

    public abstract BakedModel getHook(T entity);

    public boolean shouldRender(@NotNull T entity, @NotNull Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        if (super.shouldRender(entity, pCamera, pCamX, pCamY, pCamZ)) {
            return true;
        } else {
            Entity owner = entity.getOwner();
            if (owner != null) {
                Vec3 vec3 = owner.position().add(0.0, owner.getBbHeight() * 0.65, 0.0);
                Vec3 vec31 = entity.position().add(0.0, 0.25, 0.0);
                return pCamera.isVisible(new AABB(vec31.x, vec31.y, vec31.z, vec3.x, vec3.y, vec3.z));
            }
        }
        return false;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int packedLight) {
        int skyLight = LightTexture.pack(10, LightTexture.sky(packedLight));
        renderHook(entity, poseStack, multiBufferSource, skyLight);
        Entity owner = entity.getOwner();
        if (owner != null) {
            poseStack.pushPose();
            Vec3 vec3 = getPosition(owner, owner.getBbHeight() * 0.65, partialTick);
            Vec3 vec31 = getPosition(entity, 0.25, partialTick);
            Vec3 vec32 = vec3.subtract(vec31).normalize();
            poseStack.mulPose(Axis.YP.rotation(Mth.HALF_PI - (float) Math.atan2(vec32.z, vec32.x)));
            poseStack.mulPose(Axis.XP.rotation((float) Math.acos(vec32.y)));
            poseStack.translate(-0.5, 0.0, -0.75);
            float distance = entity.distanceTo(owner);
            int floor = (int) distance;
            BakedModel chain = getChain(entity);
            for (int i = 0; i < floor; i++) {
                //Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, chain, 1, 1, 1, skyLight, OverlayTexture.NO_OVERLAY);
                poseStack.translate(0.0, 1.0, 0.0);
            }
            float delta = distance - floor;
            if (entity.lastDelta - delta > 0.5F || entity.lastDelta == 0.0) entity.lastDelta = delta;
            delta = Mth.lerp(partialTick, entity.lastDelta, delta);
            poseStack.scale(1.0F, delta, 1.0F);
            entity.lastDelta = delta;
            //Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, chain, 1, 1, 1, skyLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }

    protected void renderHook(T entity, PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int skyLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot() - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getXRot()));
        BakedModel hook = getHook(entity);
        //Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.cutoutBlockSheet()), null, hook, 1, 1, 1, skyLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    public static Vec3 getPosition(Entity entity, double pYOffset, float pPartialTick) {
        double x = Mth.lerp(pPartialTick, entity.xOld, entity.getX());
        double y = Mth.lerp(pPartialTick, entity.yOld, entity.getY()) + pYOffset;
        double z = Mth.lerp(pPartialTick, entity.zOld, entity.getZ());
        return new Vec3(x, y, z);
    }
}
