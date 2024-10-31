package fr.lanfix.allhudbars.mixin;

import fr.lanfix.allhudbars.overlay.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public abstract class HudBarsMixins {

    // TODO Separate texture and text display (like in SHB)

    @Unique
    private static final HealthBar healthBar = new HealthBar();

    @Final
    @Shadow private  MinecraftClient client;

    @Shadow @Nullable protected abstract LivingEntity getRiddenEntity();

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
            int armorYOffset = player.getAbsorptionAmount() > 0 ? -20 : -10;
            ArmorBar.renderArmorBar(context, player, y + armorYOffset, left);
            this.client.getProfiler().swap("health");
            healthBar.render(context, player, left, y, tickDelta);
            LivingEntity riddenEntity = this.getRiddenEntity();
            if (riddenEntity == null) {
                this.client.getProfiler().swap("food");
                FoodBar.renderFoodBar(context, player, y, right);
            }

            this.client.getProfiler().swap("air");
            AirBar.renderAirBar(context, player, y - 10, right);

            this.client.getProfiler().pop();
        }
    }

    @Redirect(method = "renderMainHud", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void renderMountHealth(InGameHud instance, DrawContext context) {
        LivingEntity livingEntity = this.getRiddenEntity();
        if (livingEntity != null) {
            int x = context.getScaledWindowWidth() / 2 + 11;
            int y = context.getScaledWindowHeight() - 39;
            VehicleBar.render(context, livingEntity, x, y);
        }
    }

}
