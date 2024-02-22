package uwu.lopyluna.calamos.client.entity.model;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PestisPlayer<T extends LivingEntity> extends PlayerModel<T> {
    public PestisPlayer(ModelPart pRoot, boolean pSlim) {
        super(pRoot, pSlim);
    }
    
}
