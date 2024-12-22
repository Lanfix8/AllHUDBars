package fr.lanfix.allhudbars.overlay;

import fr.lanfix.allhudbars.AllHudBars;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class AirBar {

    private static final Identifier fullBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/air/full.png");
    private static final Identifier emptyBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/air/empty.png");

    public static void renderAirBar(DrawContext context, PlayerEntity player, int y, int right) {
        int maxAir = player.getMaxAir();
        int air = Math.min(player.getAir(), maxAir);
        int textureWidth = 80;
        if (player.isSubmergedInWater() || air < maxAir) {
            int airWidth = air * textureWidth / maxAir;
            context.drawTexture(RenderLayer::getGuiTextured, emptyBar, right - textureWidth, y, 0, 0, textureWidth - airWidth, 9, textureWidth, 9);
            context.drawTexture(RenderLayer::getGuiTextured, fullBar, right - airWidth, y, textureWidth - airWidth, 0, airWidth, 9, textureWidth, 9);
        }
    }

}
