package org.shellturtle.mixin;

import net.minecraft.world.entity.animal.turtle.Turtle;
import org.shellturtle.ModEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Turtle.class)
public class TurtleMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void onRegisterGoals(CallbackInfo ci) {
        Turtle self = (Turtle) (Object) this;
        
        // 如果海龟已绝育，移除繁殖目标
        if (ModEvents.isTurtleSterilized(self.getUUID())) {
            // 这里无法直接移除目标，需要使用其他方法
        }
    }
}
