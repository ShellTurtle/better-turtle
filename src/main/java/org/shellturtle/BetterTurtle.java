package org.shellturtle;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.shellturtle.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterTurtle implements ModInitializer {
	public static final String MOD_ID = "better-turtle";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		LOGGER.info("Better Turtle加载完成！");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
