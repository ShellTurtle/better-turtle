package org.shellturtle.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModEvents {
    private static final long COOLDOWN_TICKS = 20 * 60 * 20; // 20分钟（20tick/秒 * 60秒 * 20分钟）
    private static final int MAX_SCUTES_PER_PERIOD = 2; // 最多掉落10个
    
    private static final Map<UUID, TurtleBrushData> turtleBrushData = new HashMap<>();

    // 海龟刷取数据记录
    private static class TurtleBrushData {
        long startTime; // 开始时间
        int scutesDropped; // 已掉落数量

        TurtleBrushData(long startTime) {
            this.startTime = startTime;
            this.scutesDropped = 0;
        }
    }

    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (world.isClientSide()) {
                return InteractionResult.PASS;
            }

            if (!(entity instanceof Turtle turtle)) {
                return InteractionResult.PASS;
            }

            ItemStack stack = player.getItemInHand(hand);

            // 刷子刷鳞片功能
            if (stack.getItem() != Items.BRUSH) {
                return InteractionResult.PASS;
            }

            if (turtle.isBaby()) {
                return InteractionResult.PASS;
            }

            UUID turtleId = turtle.getUUID();
            long currentTime = world.getGameTime();
            TurtleBrushData data = turtleBrushData.get(turtleId);

            // 检查是否需要重置（超过20分钟）
            if (data != null && currentTime - data.startTime >= COOLDOWN_TICKS) {
                data = null; // 重置数据
            }

            // 如果没有数据，创建新的
            if (data == null) {
                data = new TurtleBrushData(currentTime);
                turtleBrushData.put(turtleId, data);
            }

            // 检查是否已达到最大掉落数量
            if (data.scutesDropped >= MAX_SCUTES_PER_PERIOD) {
                return InteractionResult.PASS;
            }

            Level level = world;
            
            // 掉落1个鳞片
            ItemEntity drop = new ItemEntity(level, turtle.getX(), turtle.getY() + 0.5D, turtle.getZ(), new ItemStack(Items.TURTLE_SCUTE, 1));
            level.addFreshEntity(drop);
            level.playSound(null, new BlockPos((int)turtle.getX(), (int)turtle.getY(), (int)turtle.getZ()), SoundEvents.BRUSH_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);

            // 扣除刷子耐久度（-5）
            if (!player.isCreative()) {
                stack.hurtAndBreak(2, player, hand);
            }

            // 更新掉落数量
            data.scutesDropped++;

            return InteractionResult.SUCCESS;
        });
    }
}
