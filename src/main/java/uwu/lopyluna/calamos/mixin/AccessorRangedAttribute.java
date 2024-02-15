package uwu.lopyluna.calamos.mixin;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RangedAttribute.class)
public interface AccessorRangedAttribute {
    @Accessor("minValue")
    @Mutable
    void calamos$setMinValue(double minValue);
    
    @Accessor("maxValue")
    @Mutable
    void calamos$setMaxValue(double maxValue);
    
    @Accessor("minValue")
    double calamos$getMinValue();
    @Accessor("maxValue")
    double calamos$getMaxValue();
}
