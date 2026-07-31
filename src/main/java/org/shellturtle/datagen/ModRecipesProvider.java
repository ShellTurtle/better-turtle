package org.shellturtle.datagen;

import org.shellturtle.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        return new RecipeProvider(provider, output) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.FOOD, ModItems.TURTLE_JELLY)
                        .requires(Items.TURTLE_SCUTE)
                        .requires(Items.SUGAR)
                        .requires(Items.HONEY_BOTTLE)
                        .requires(Items.BOWL)
                        .unlockedBy("has_item", has(Items.TURTLE_SCUTE))
                        .save(output);

            }
        };
    }

    @Override
    public String getName() {
        return "recipe gen";
    }
}
