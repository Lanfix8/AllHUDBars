package fr.lanfix.allhudbars.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.lanfix.allhudbars.overlay.FoodBar;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Gui.class)
public abstract class HealthBarMixins {


    @Shadow @Final private Minecraft minecraft;

    @Redirect(method = "renderHotbarAndDecorations", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    public void renderPlayerHealth(Gui instance, GuiGraphics guiGraphics) {
        // Based on a copy of the original code
        if (this.minecraft.getCameraEntity() instanceof Player player) {

            int left = guiGraphics.guiWidth() / 2 - 91;
            int right = guiGraphics.guiWidth() / 2 + 91;
            int y = guiGraphics.guiHeight() - 39;
//            float displayHealth = Math.max((float) player.getAttributeValue(Attributes.MAX_HEALTH), (float) Math.max(this.displayHealth, health));
//            int absorption = Mth.ceil(player.getAbsorptionAmount());
//            int p = Mth.ceil((displayHealth + (float) absorption) / 2.0F / 10.0F);
//            int q = Math.max(10 - (p - 2), 3);
//            int r = y - 10;
//            int s = -1;
//            if (player.hasEffect(MobEffects.REGENERATION)) {
//                s = this.tickCount % Mth.ceil(displayHealth + 5.0F);
//            }

            this.minecraft.getProfiler().push("armor");
            // TODO renderArmor(guiGraphics, player, y, p, q, left);
            this.minecraft.getProfiler().popPush("health");
            // TODO this.renderHearts(guiGraphics, player, left, y, q, s, displayHealth, health, this.displayHealth, absorption, bl);
//            LivingEntity livingEntity = this.getPlayerVehicleWithHealth();
//            int t = this.getVehicleMaxHearts(livingEntity);
//            if (t == 0) {
            this.minecraft.getProfiler().popPush("food");
            FoodBar.renderFoodBar(guiGraphics, player, y, right);
//                r -= 10;
//            }

            this.minecraft.getProfiler().popPush("air");
            int maxAirSupply = player.getMaxAirSupply();
            int airSupply = Math.min(player.getAirSupply(), maxAirSupply);
//            if (player.isEyeInFluid(FluidTags.WATER) || airSupply < maxAirSupply) {
//                int w = this.getVisibleVehicleHeartRows(t) - 1;
//                r -= w * 10;
//                int x = Mth.ceil((double) (airSupply - 2) * 10.0 / (double) maxAirSupply);
//                int y = Mth.ceil((double) airSupply * 10.0 / (double) maxAirSupply) - x;
//                RenderSystem.enableBlend();
//
//                for (int z = 0; z < x + y; z++) {
//                    if (z < x) {
//                        guiGraphics.blitSprite(AIR_SPRITE, right - z * 8 - 9, r, 9, 9);
//                    } else {
//                        guiGraphics.blitSprite(AIR_BURSTING_SPRITE, right - z * 8 - 9, r, 9, 9);
//                    }
//                }
//
//                RenderSystem.disableBlend();
//            }

            this.minecraft.getProfiler().pop();
        }
    }


}
