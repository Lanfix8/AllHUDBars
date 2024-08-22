package fr.lanfix.allhudbars.overlay;

import fr.lanfix.allhudbars.AllHudBars;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

public class FoodBar {

    private static final ResourceLocation fullBar = ResourceLocation.fromNamespaceAndPath(AllHudBars.MOD_ID, "textures/gui/bars/food/full.png");
    private static final ResourceLocation emptyBar = ResourceLocation.fromNamespaceAndPath(AllHudBars.MOD_ID, "textures/gui/bars/food/empty.png");

    public static void renderFoodBar(GuiGraphics guiGraphics, Player player, int y, int right) {
        FoodData foodData = player.getFoodData();
        int foodLevel = foodData.getFoodLevel();
        int textureWidth = 80;

        int foodWidth = foodLevel * textureWidth / 20;

        guiGraphics.blit(emptyBar, right - textureWidth, y, 0, 0, textureWidth - foodWidth, 8, textureWidth, 8);
        guiGraphics.blit(fullBar, right - foodWidth, y, textureWidth - foodWidth, 0, foodWidth, 8, textureWidth, 8);
    }

}
