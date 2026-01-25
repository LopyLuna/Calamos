package uwu.lopyluna.calamos.elements;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import uwu.lopyluna.calamos.CalamosMod;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> BOOMERANG = createDamageType("boomerang");

    public static ResourceKey<DamageType> createDamageType(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, CalamosMod.asResource(name));
    }
}
