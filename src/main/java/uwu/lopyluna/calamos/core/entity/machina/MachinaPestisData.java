package uwu.lopyluna.calamos.core.entity.machina;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class MachinaPestisData {

    public static Codec<MachinaPestisData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            UUIDUtil.CODEC.optionalFieldOf("linked_clone").forGetter(MachinaPestisData::getLinkedClone)
    ).apply(obj, MachinaPestisData::new));

    private Optional<UUID> linkedPestisClone;

    public MachinaPestisData() {
        this.linkedPestisClone = Optional.empty();
    }

    public MachinaPestisData(Optional<UUID> linkedPestisClone) {
        this.linkedPestisClone = linkedPestisClone;
    }

    public void linkClone(UUID linkedPestisClone) {
        this.linkedPestisClone = Optional.ofNullable(linkedPestisClone);
    }

    public void unlink() {
        this.linkedPestisClone = Optional.empty();
    }

    public Optional<UUID> getLinkedClone() {
        return linkedPestisClone;
    }

    public boolean isLinked() {
        return linkedPestisClone.isPresent();
    }
}
