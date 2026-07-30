package org.shellturtle.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumable {
    public static final Consumable TURTLE_JELLY = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(MobEffects.RESISTANCE, 2400, 4),
                            new MobEffectInstance(MobEffects.SLOWNESS, 24000, 9)
                    ), 0.5F
            )).build();


}
