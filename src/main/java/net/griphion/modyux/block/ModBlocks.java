package net.griphion.modyux.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.griphion.modyux.Modyux;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block ORICHALCUM_BLOCK = registerBlock("orichalcum_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),
            ItemGroup.BUILDING_BLOCKS);

    public static final Block ORICHALCUM_ORE = registerBlock("orichalcum_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()),
            ItemGroup.BUILDING_BLOCKS);

    public static final Block RAW_ORICHALCUM_ORE = registerBlock("raw_orichalcum_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(7f).requiresTool()),
            ItemGroup.BUILDING_BLOCKS);

    public static void registerModBlocks() {
        Modyux.LOGGER.info("Registering Blocks for Modyux");
    }

    private static Block registerBlock(String name, Block block, ItemGroup itemGroup) {
        registerBlockItem(name, block, itemGroup);
        return Registry.register(Registry.BLOCK, new Identifier(Modyux.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup itemGroup) {
        Registry.register(Registry.ITEM,
                new Identifier(Modyux.MOD_ID,name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)) );
    }
}
