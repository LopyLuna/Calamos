package uwu.lopyluna.calamos.elements;

import net.minecraft.client.particle.SpellParticle;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.lopyluna.calamos.elements.block.HallowWorkbenchBlock;
import uwu.lopyluna.calamos.elements.block.SawmillBlock;
import uwu.lopyluna.calamos.elements.block.tnt.StableUltimitaTNT;
import uwu.lopyluna.calamos.elements.block.tnt.UnstableUltimitaTNT;
import uwu.lopyluna.calamos.elements.items.ParticleSpewingBlockItem;
import uwu.lopyluna.calamos.elements.items.annotations.NoTab;
import uwu.lopyluna.calamos.utilities.ModUtils;

import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = ModUtils.createRegister(DeferredRegister::createBlocks);
    public static Collection<DeferredHolder<Block, ? extends Block>> decorativeBlocks;
    //Gem Blocks
    public static final DeferredBlock<Block> GARNET_BLOCK = register("garnet_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> JADE_BLOCK = register("jade_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> KUNZITE_BLOCK = register("kunzite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> MOONSTONE_BLOCK = register("moonstone_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> OPAL_BLOCK = register("opal_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> RUBY_BLOCK = register("ruby_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = register("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> SPINEL_BLOCK = register("spinel_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> SUNSTONE_BLOCK = register("sunstone_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> TANZANITE_BLOCK = register("tanzanite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> TOPAZ_BLOCK = register("topaz_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)),
            new Item.Properties());
    public static final DeferredBlock<Block> URANIUM_BLOCK = registerNoItem("uranium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> RAW_URANIUM_BLOCK = registerNoItem("raw_uranium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
    public static final DeferredBlock<Block> PALLADIUM_BLOCK = register("palladium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
            ), new Item.Properties());
    public static final DeferredBlock<Block> RAW_PALLADIUM_BLOCK = register("raw_palladium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)
            ), new Item.Properties());

    public static final DeferredBlock<Block> STONE = register("stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());
    public static final DeferredBlock<Block> PURRASITE = register("purrasite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());
    public static final DeferredBlock<Block> UMBRALITE = register("umbralite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.STONE)
                    .strength(3.5F)
                    .explosionResistance(7.0F)
            ), new Item.Properties()
                    .fireResistant());

    //Menu Type Blocks
    public static final DeferredBlock<HallowWorkbenchBlock> HALLOW_WORKBENCH = register("hallow_workbench",
            () -> new HallowWorkbenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)),
            new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> METAL_GRINDER = register("metal_grinder",
            () -> new StonecutterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONECUTTER)),
            new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> SAWMILL = register("sawmill",
            () -> new SawmillBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONECUTTER)),
            new Item.Properties().fireResistant());


    //Ores
    public static final DeferredBlock<Block> COPPER_ORE = register("copper_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> IRON_ORE = register("iron_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> GOLD_ORE = register("gold_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> METEORITE_ORE = register("meteorite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> GARNET_ORE = register("garnet_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> JADE_ORE = register("jade_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> KUNZITE_ORE = register("kunzite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> MOONSTONE_ORE = register("moonstone_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> OPAL_ORE = register("opal_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> RUBY_ORE = register("ruby_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SAPPHIRE_ORE = register("sapphire_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SPINEL_ORE = register("spinel_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> SUNSTONE_ORE = register("sunstone_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TANZANITE_ORE = register("tanzanite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> TOPAZ_ORE = register("topaz_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> URANIUM_ORE = register("uranium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());
    public static final DeferredBlock<Block> PALLADIUM_ORE = register("palladium_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            ), new Item.Properties());


    public static final DeferredBlock<Block> UNSTABLE_ULTIMITA_TNT = register("unstable_ultimita_tnt",
            () -> new UnstableUltimitaTNT(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)
            ), new Item.Properties());

    public static final DeferredBlock<Block> ULTIMITA_TNT = register("ultimita_tnt",
            () -> new StableUltimitaTNT(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)
            ), new Item.Properties());



    //Extra Cool shit :3
    private static <T extends Block> DeferredBlock<T> register(String id, Supplier<T> block, Item.Properties pIProp) {
        DeferredBlock<T> toReturn = BLOCKS.register(id.toLowerCase(), block);
        makeBlockItem(toReturn, pIProp);
        return toReturn;
    }
    private static <T extends Block> DeferredBlock<T> registerNoItem(String id, Supplier<T> block) {
        return BLOCKS.register(id.toLowerCase(), block);
    }
    private static <T extends Block> void makeBlockItem(DeferredBlock<T> block, Item.Properties pIProp) {
        ModItems.registerBlockItem(block, pIProp);
    }
    private static <T extends Block> void makeSpecialBlockItem(DeferredBlock<T> block, Item.Properties pIProp, Supplier<? extends BlockItem> sup) {
        ModItems.registerSpecialBlockItem(block, sup);
    }
    
    public static void staticInit() {
    }

    public static Collection<DeferredHolder<Block, ? extends Block>> getBlocks() {
        return BLOCKS.getEntries();
    }
}
