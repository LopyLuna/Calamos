package uwu.lopyluna.calamos.client.render.hook;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.calamos.CalamosMod;
import uwu.lopyluna.calamos.client.CalamosReloadableModels;
import uwu.lopyluna.calamos.elements.entity.hook.HookEntity;

public class HookRenderer extends AbstractHookRenderer<HookEntity> {

    public HookRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull HookEntity pEntity) {
        return CalamosMod.asResource("textures/entity/hook/"+pEntity.getVariant().getSerializedName()+".png");
    }

    @Override
    public BakedModel getChain(HookEntity entity) {
        String type = entity.getVariant().getSerializedName();
        return switch (type) {
            case "grappling" -> CalamosReloadableModels.INSTANCE.chain_grappling;
            case "amethyst" -> CalamosReloadableModels.INSTANCE.chain_amethyst;
            case "topaz" -> CalamosReloadableModels.INSTANCE.chain_topaz;
            case "sapphire" -> CalamosReloadableModels.INSTANCE.chain_sapphire;
            case "emerald" -> CalamosReloadableModels.INSTANCE.chain_emerald;
            case "ruby" -> CalamosReloadableModels.INSTANCE.chain_ruby;
            case "diamond" -> CalamosReloadableModels.INSTANCE.chain_diamond;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    @Override
    public BakedModel getHook(HookEntity entity) {
        String type = entity.getVariant().getSerializedName();
        return switch (type) {
            case "grappling" -> CalamosReloadableModels.INSTANCE.hook_grappling;
            case "amethyst" -> CalamosReloadableModels.INSTANCE.hook_amethyst;
            case "topaz" -> CalamosReloadableModels.INSTANCE.hook_topaz;
            case "sapphire" -> CalamosReloadableModels.INSTANCE.hook_sapphire;
            case "emerald" -> CalamosReloadableModels.INSTANCE.hook_emerald;
            case "ruby" -> CalamosReloadableModels.INSTANCE.hook_ruby;
            case "diamond" -> CalamosReloadableModels.INSTANCE.hook_diamond;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
