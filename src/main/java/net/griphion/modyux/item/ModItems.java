package net.griphion.modyux.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.griphion.modyux.Modyux;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    // Registro de items

    public static final Item ORICHALCUM_INGOT = registerItem("orichalcum_ingot", new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item ORICHALCUM_NUGGET = registerItem("orichalcum_nugget", new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static void registerModItems() {
        Modyux.LOGGER.info("Registering Items for Modyux");
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Modyux.MOD_ID,name), item);
    }
}
