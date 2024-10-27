package fr.lanfix.allhudbars.overlay;

import fr.lanfix.allhudbars.AllHudBars;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.HungerManager;
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

            context.drawTexture(emptyBar, right - textureWidth, y, 0, 0, textureWidth - airWidth, 8, textureWidth, 8);
            context.drawTexture(fullBar, right - airWidth, y, textureWidth - airWidth, 0, airWidth, 8, textureWidth, 8);
        }
    }

}
