package fr.lanfix.allhudbars.mixin;

import fr.lanfix.allhudbars.overlay.AirBar;
import fr.lanfix.allhudbars.overlay.FoodBar;
import fr.lanfix.allhudbars.overlay.HealthBar;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public abstract class HealthBarMixins {

    @Unique
    private static final HealthBar healthBar = new HealthBar();

    @Final
    @Shadow private  MinecraftClient client;

    @Unique private int lastTicks;

    @Redirect(method = "renderMainHud", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void renderStatusBars(InGameHud instance, DrawContext context) {
        // Based on a copy of the original code
        if (this.client.getCameraEntity() instanceof PlayerEntity player) {

            int left = context.getScaledWindowWidth() / 2 - 91;
            int right = context.getScaledWindowWidth() / 2 + 91;
            int y = context.getScaledWindowHeight() - 39;

            int tickDelta = instance.getTicks() - lastTicks;
            lastTicks = instance.getTicks();

            this.client.getProfiler().push("armor");
            // TODO renderArmor(guiGraphics, player, y, p, q, left);
            this.client.getProfiler().swap("health");
            healthBar.render(context, player, left, y, tickDelta);
            // TODO Mount health display
//            LivingEntity livingEntity = this.getPlayerVehicleWithHealth();
//            int t = this.getVehicleMaxHearts(livingEntity);
//            if (t == 0) {
            this.client.getProfiler().swap("food");
            FoodBar.renderFoodBar(context, player, y, right);

            this.client.getProfiler().swap("air");
            AirBar.renderAirBar(context, player, y - 10, right);

            this.client.getProfiler().pop();
        }
    }


}
