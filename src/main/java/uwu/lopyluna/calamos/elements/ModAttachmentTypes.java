package uwu.lopyluna.calamos.elements;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uwu.lopyluna.calamos.core.attribute.ModifiedAttributesData;
import uwu.lopyluna.calamos.core.entity.machina.MachinaPestisData;
import uwu.lopyluna.calamos.core.items.equipment.wings.WingsData;
import uwu.lopyluna.calamos.core.systems.health.HeartData;
import uwu.lopyluna.calamos.core.systems.mana.ManaData;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.function.Supplier;

public class ModAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = ModUtils.createRegister(NeoForgeRegistries.Keys.ATTACHMENT_TYPES);

    public static final Supplier<AttachmentType<Double>> DAMAGE_MULTIPLIER = ATTACHMENT_TYPES.register("total_damage_multiplier", () -> AttachmentType.builder(() -> 1.0).serialize(Codec.DOUBLE).build());
    public static final Supplier<AttachmentType<Double>> CRIT_CHANCE = ATTACHMENT_TYPES.register("critical_strike_chance_multiplier", () -> AttachmentType.builder(() -> 0.0).serialize(Codec.DOUBLE).build());

    public static final Supplier<AttachmentType<ManaData>> MANA = ATTACHMENT_TYPES.register("mana", () -> AttachmentType.builder(ManaData::new).serialize(ManaData.CODEC).sync(ManaData.STREAM_CODEC).build());
    public static final Supplier<AttachmentType<WingsData>> WINGS = ATTACHMENT_TYPES.register("wings", () -> AttachmentType.builder(WingsData::new).serialize(WingsData.CODEC).sync(WingsData.STREAM_CODEC).build());
    public static final Supplier<AttachmentType<HeartData>> HEARTS = ATTACHMENT_TYPES.register("hearts", () -> AttachmentType.builder(HeartData::new).serialize(HeartData.CODEC).sync(HeartData.STREAM_CODEC).build());


    public static final Supplier<AttachmentType<ModifiedAttributesData>> MODIFIED_ATTRIBUTES = ATTACHMENT_TYPES.register("modified_attributes", () -> AttachmentType.builder(() -> new ModifiedAttributesData()).serialize(ModifiedAttributesData.CODEC).build());
    public static final Supplier<AttachmentType<MachinaPestisData>> MACHINA_PESTIS = ATTACHMENT_TYPES.register("machina_pestis", () -> AttachmentType.builder(() -> new MachinaPestisData()).serialize(MachinaPestisData.CODEC).build());

    public static void staticInit() {}
}
