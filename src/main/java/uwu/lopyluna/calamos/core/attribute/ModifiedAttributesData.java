package uwu.lopyluna.calamos.core.attribute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.ArrayList;
import java.util.List;

public class ModifiedAttributesData {

    public static Codec<ModifiedAttributesData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Attribute.CODEC.listOf().optionalFieldOf("modified_attributes", List.of()).forGetter(ModifiedAttributesData::getModifiedAttributes)
    ).apply(obj, ModifiedAttributesData::new));

    private List<Holder<Attribute>> modifiedAttributes = new ArrayList<>();

    public ModifiedAttributesData() {
    }

    public ModifiedAttributesData(List<Holder<Attribute>> modifiedAttributes) {
        this.modifiedAttributes = modifiedAttributes;
    }

    public void notifyModified(Holder<Attribute> attribute) {
        modifiedAttributes.add(attribute);
    }

    public boolean isModified(Holder<Attribute> attribute) {
        return modifiedAttributes.contains(attribute);
    }

    public List<Holder<Attribute>> getModifiedAttributes() {
        return modifiedAttributes;
    }
}
