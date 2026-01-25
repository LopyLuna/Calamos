package uwu.lopyluna.calamos.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import uwu.lopyluna.calamos.elements.entity.orb.LifestealOrb;

@OnlyIn(Dist.CLIENT)
public class LifestealOrbRenderer extends EntityRenderer<LifestealOrb> {

    public LifestealOrbRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(LifestealOrb pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {}

    @Override
    public ResourceLocation getTextureLocation(LifestealOrb pEntity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
