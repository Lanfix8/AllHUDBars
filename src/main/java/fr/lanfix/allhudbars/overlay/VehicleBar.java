package fr.lanfix.allhudbars.overlay;

import fr.lanfix.allhudbars.AllHudBars;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class VehicleBar {

    private static final Identifier fullHealthBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/vehicle/full.png");
    private static final Identifier emptyHealthBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/vehicle/empty.png");

    public static void render(TextRenderer textRenderer, DrawContext context, LivingEntity entity, int x, int y) {
        renderHealthBar(context, x, y, entity);
        renderHealthValue(textRenderer, context, x, y, entity);
    }

    private static void renderHealthValue(TextRenderer textRenderer, DrawContext context, int x, int y, LivingEntity entity) {
        double health = Math.ceil(entity.getHealth() * 10) / 10;
        float maxHealth = entity.getMaxHealth();
        String text = health + "/" + maxHealth;
        text = text.replace(".0", "");

        // Offset for text
        int offX = 4;
        int offY = -4;

        // Draw health value + 4px outline
        context.drawText(textRenderer, text, x + offX + 1, y + offY, 0x000000, false);
        context.drawText(textRenderer, text, x + offX - 1, y + offY, 0x000000, false);
        context.drawText(textRenderer, text, x + offX, y + offY + 1, 0x000000, false);
        context.drawText(textRenderer, text, x + offX, y + offY - 1, 0x000000, false);
        context.drawText(textRenderer, text, x + offX, y + offY, 0xffffff, false);

    }

    private static void renderHealthBar(DrawContext context, int x, int y, LivingEntity entity) {
        float health = entity.getHealth();
        float maxHealth = entity.getMaxHealth();

        // Calculate bar proportions
        int textureWidth = 80;
        int healthWidth = (int) Math.min(textureWidth * health / maxHealth, textureWidth);

        // Display full part
        context.drawTexture(fullHealthBar,
                x, y,
                0, 0,
                healthWidth, 9,
                textureWidth, 9);

        // Display empty part
        context.drawTexture(emptyHealthBar,
                x + healthWidth, y,
                healthWidth, 0,
                textureWidth - healthWidth, 9,
                textureWidth, 9);

    }

}
