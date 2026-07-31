package org.shellturtle.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.shellturtle.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModZhCnLangProvider extends FabricLanguageProvider {
    public ModZhCnLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.TURTLE_JELLY, "龟苓膏");
        translationBuilder.add(ModItems.TURTLE_SCUTE_HELMET, "乌龟头盔");
        translationBuilder.add(ModItems.TURTLE_SCUTE_CHESTPLATE, "乌龟胸甲");
        translationBuilder.add(ModItems.TURTLE_SCUTE_LEGGINGS, "乌龟护腿");
        translationBuilder.add(ModItems.TURTLE_SCUTE_BOOTS, "乌龟靴子");
    }
}
