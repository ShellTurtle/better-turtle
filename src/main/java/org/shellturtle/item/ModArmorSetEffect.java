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

        // 在水中时给予抗性提升2 水下呼吸 速度
        if (hasFullSet && player.isInWater()) {
            // 抗性提升2
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20, 1, false, false));
            // 水下呼吸
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, false, false));
        }

        // 不在水中时给予抗性提升2 缓慢1
        if (hasFullSet && !player.isInWater()) {
            // 抗性提升2
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20, 1, false, false));
            // 缓慢1
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 0, false, false));
        }

        // 玩家未穿戴全套乌龟护甲时移除乌龟护甲提供的所有buff和debuff
        if (!hasFullSet) {
            player.removeEffect(MobEffects.RESISTANCE);
            player.removeEffect(MobEffects.WATER_BREATHING);
            player.removeEffect(MobEffects.SLOWNESS);
            player.removeEffect(MobEffects.SPEED);
        }
    }

    private static boolean hasFullTurtleArmorSet(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        if (helmet.is(ModItems.TURTLE_SCUTE_HELMET) &&
                chestplate.is(ModItems.TURTLE_SCUTE_CHESTPLATE) &&
                leggings.is(ModItems.TURTLE_SCUTE_LEGGINGS) &&
                boots.is(ModItems.TURTLE_SCUTE_BOOTS)) {
            return true;
        }

        else {
            return false;
        }
    }
}
