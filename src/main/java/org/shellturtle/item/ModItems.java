package org.shellturtle.item;

import org.shellturtle.BetterTurtle;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;
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
    public static final Item TURTLE_JELLY = registerItem("turtle_jelly",Item::new , new Item.Properties()
            .food(ModFoods.TURTLE_JELLY, ModConsumable.TURTLE_JELLY).rarity(Rarity.UNCOMMON));

    public static final Item TURTLE_SCUTE_HELMET= registerItem("turtle_scute_helmet", Item::new, new Item.Properties()
            .humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR, ArmorType.HELMET).rarity(Rarity.EPIC));

    public static final Item TURTLE_SCUTE_CHESTPLATE= registerItem("turtle_scute_chestplate", Item::new, new Item.Properties()
            .humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR, ArmorType.CHESTPLATE).rarity(Rarity.EPIC));

    public static final Item TURTLE_SCUTE_LEGGINGS= registerItem("turtle_scute_leggings", Item::new, new Item.Properties()
            .humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR, ArmorType.LEGGINGS).rarity(Rarity.EPIC));

    public static final Item TURTLE_SCUTE_BOOTS= registerItem("turtle_scute_boots", Item::new, new Item.Properties()
            .humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR, ArmorType.BOOTS).rarity(Rarity.EPIC));

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
        BetterTurtle.LOGGER.info("Registering Mod Items for " + BetterTurtle.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(fabricCreativeModeTabOutput -> {fabricCreativeModeTabOutput.accept(TURTLE_JELLY);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register(fabricCreativeModeTabOutput -> {
                    fabricCreativeModeTabOutput.accept(TURTLE_SCUTE_HELMET);
                    fabricCreativeModeTabOutput.accept(TURTLE_SCUTE_CHESTPLATE);
                    fabricCreativeModeTabOutput.accept(TURTLE_SCUTE_LEGGINGS);
                    fabricCreativeModeTabOutput.accept(TURTLE_SCUTE_BOOTS);
                });
    }
}
