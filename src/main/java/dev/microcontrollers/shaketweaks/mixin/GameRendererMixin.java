package dev.microcontrollers.shaketweaks.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.microcontrollers.shaketweaks.config.ShakeTweaksConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;bobView(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    public boolean disableScreenBobbing(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        return !ShakeTweaksConfig.disableScreenBobbing;
    }

    @WrapWithCondition(method = "renderHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;bobView(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    public boolean disableHandBobbing(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        if (!ShakeTweaksConfig.disableMapBobbing) return !ShakeTweaksConfig.disableHandBobbing;
        ClientPlayerEntity entity = MinecraftClient.getInstance().player;
        if (entity != null) {
            ItemStack mainHand = entity.getMainHandStack();
            ItemStack offHand = entity.getOffHandStack();
            if (mainHand != null && mainHand.getItem() instanceof FilledMapItem ) return false;
            return offHand == null || !(offHand.getItem() instanceof FilledMapItem);
        }
        return true;
    }

    @WrapWithCondition(method = "renderHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;tiltViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    public boolean disableHandDamageTilt(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        return !ShakeTweaksConfig.disableHandDamage;
    }

    @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;tiltViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    public boolean disableScreenDamageTilt(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        return !ShakeTweaksConfig.disableScreenDamage;
    }

}
