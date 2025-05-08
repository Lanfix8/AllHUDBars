package fr.lanfix.allhudbars.overlay;

import fr.lanfix.allhudbars.AllHudBars;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class FoodBar {

    private static final Identifier fullBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/food/full.png");
    private static final Identifier emptyBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/food/empty.png");

    public static void renderFoodBar(DrawContext context, PlayerEntity player, int y, int right) {
        HungerManager hungerManager = player.getHungerManager();
        int foodLevel = hungerManager.getFoodLevel();
        int textureWidth = 80;

        int foodWidth = foodLevel * textureWidth / 20;

        context.drawTexture(RenderLayer::getGuiTextured, emptyBar, right - textureWidth, y, 0, 0, textureWidth - foodWidth, 8, textureWidth, 8);
        context.drawTexture(RenderLayer::getGuiTextured, fullBar, right - foodWidth, y, textureWidth - foodWidth, 0, foodWidth, 8, textureWidth, 8);
    }

}
