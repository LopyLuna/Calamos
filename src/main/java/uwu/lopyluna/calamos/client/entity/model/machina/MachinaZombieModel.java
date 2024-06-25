package uwu.lopyluna.calamos.client.entity.model.machina;

import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;

public class MachinaZombieModel<T extends Zombie> extends AbstractZombieModel<T> {
    public MachinaZombieModel(ModelPart pRoot) {
        super(pRoot);
    }
    
    public boolean isAggressive(T pEntity) {
        return pEntity.isAggressive();
    }
}
