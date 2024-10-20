package uwu.lopyluna.calamos.elements.items.equipment.tool.arrow.irradiated;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.CalamosMod;

public class IrradiatedArrowRenderer extends ArrowRenderer<IrradiatedArrow> {
    public static final ResourceLocation IRRADIATED_ARROW_LOCATION = CalamosMod.asResource("textures/entity/projectiles/irradiated_arrow.png");

    public IrradiatedArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public @NotNull ResourceLocation getTextureLocation(@NotNull IrradiatedArrow pEntity) {
        return IRRADIATED_ARROW_LOCATION;
    }
}
