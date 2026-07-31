package org.shellturtle.datagen;

import net.minecraft.tags.ItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.shellturtle.item.ModItems;
import org.shellturtle.tag.ModItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.references.ItemIds;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(ModItemTags.TURTLE_SCUTE_ARMOR).add(ItemIds.TURTLE_SCUTE);

        builder(ItemTags.HEAD_ARMOR).add(keyOf(ModItems.TURTLE_SCUTE_HELMET));
        builder(ItemTags.CHEST_ARMOR).add(keyOf(ModItems.TURTLE_SCUTE_CHESTPLATE));
        builder(ItemTags.LEG_ARMOR).add(keyOf(ModItems.TURTLE_SCUTE_LEGGINGS));
        builder(ItemTags.FOOT_ARMOR).add(keyOf(ModItems.TURTLE_SCUTE_BOOTS));
    }

    private static ResourceKey<Item> keyOf(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
    }
}
