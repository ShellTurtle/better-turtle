package org.shellturtle.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.shellturtle.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModEnUsLangProvider extends FabricLanguageProvider {
    public ModEnUsLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.TURTLE_JELLY, "Turtle jelly");
        translationBuilder.add(ModItems.TURTLE_SCUTE_HELMET, "Turtle helmet");
        translationBuilder.add(ModItems.TURTLE_SCUTE_CHESTPLATE, "Turtle chestplate");
        translationBuilder.add(ModItems.TURTLE_SCUTE_LEGGINGS, "Turtle leggings");
        translationBuilder.add(ModItems.TURTLE_SCUTE_BOOTS, "Turtle boots");
    }
}
