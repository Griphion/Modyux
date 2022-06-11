package net.griphion.modyux;

import net.fabricmc.api.ModInitializer;
import net.griphion.modyux.block.ModBlocks;
import net.griphion.modyux.item.ModItems;
import net.griphion.modyux.util.ModRegistries;
import net.griphion.modyux.world.feature.ModOreConfiguredFeatures;
import net.griphion.modyux.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Modyux implements ModInitializer {
	public static final String MOD_ID = "modyux";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Modyux initializing");

		ModOreConfiguredFeatures.registerOreConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModWorldGen.generateModWorldGen();
		ModRegistries.registerExtraModStuff();
	}
}
