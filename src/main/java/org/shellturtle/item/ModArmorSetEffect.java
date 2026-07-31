package org.shellturtle.item;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModArmorSetEffect {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (Player player : PlayerLookup.all(server)) {
                checkAndApplyArmorSetEffect(player);
            }
        });
    }

    private static void checkAndApplyArmorSetEffect(Player player) {
        boolean hasFullSet = hasFullTurtleArmorSet(player);

        if (hasFullSet) {
            // 抗性提升2
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20, 1, false, false));
            // 水下呼吸
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, false, false));
            // 速度 (在水中时)
            if (player.isUnderWater()) {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 20, 2, false, false));
            }
            // 缓慢1
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 0, false, false));
        }
    }

    private static boolean hasFullTurtleArmorSet(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        return helmet.is(ModItems.TURTLE_SCUTE_HELMET) &&
               chestplate.is(ModItems.TURTLE_SCUTE_CHESTPLATE) &&
               leggings.is(ModItems.TURTLE_SCUTE_LEGGINGS) &&
               boots.is(ModItems.TURTLE_SCUTE_BOOTS);
    }
}
