package org.shellturtle.item;

import org.shellturtle.BetterTurtle;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item TURTLE_JELLY = registerItem("turtle_jelly",Item::new , new Item.Properties().food(ModFoods.TURTLE_JELLY, ModConsumable.TURTLE_JELLY));

    private static Item registerItem(final String name, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterTurtle.MOD_ID, name));
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static Item registerItem(final String name, final Function<Item.Properties, Item> ItemFactory) {
        return  registerItem(name, ItemFactory, new Item.Properties());
    }

    private static Item registerItem(final String name) {
        return  registerItem(name, Item::new, new Item.Properties());
    }

    public static void register() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(TURTLE_JELLY);
        });
    }
}
