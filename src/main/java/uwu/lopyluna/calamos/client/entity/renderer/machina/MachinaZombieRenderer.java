package uwu.lopyluna.calamos.client.entity.renderer.machina;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.lopyluna.calamos.client.entity.model.machina.MachinaZombieModel;
import uwu.lopyluna.calamos.elements.entity.machina.infected.MachinaZombie;

public class MachinaZombieRenderer extends HumanoidMobRenderer<MachinaZombie, MachinaZombieModel<MachinaZombie>> {
    
    public MachinaZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MachinaZombieModel<>(pContext.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(MachinaZombie pEntity) {
        return ResourceLocation.withDefaultNamespace("textures/entity/zombie/zombie.png");
    }
}
