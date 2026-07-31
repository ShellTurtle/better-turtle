package org.shellturtle.tag;

import org.shellturtle.BetterTurtle;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> TURTLE_SCUTE_ARMOR = bind("turtle_scute_armor");
    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterTurtle.MOD_ID, name));
    }
}