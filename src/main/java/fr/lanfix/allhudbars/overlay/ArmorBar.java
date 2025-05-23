package fr.lanfix.allhudbars.overlay;

import fr.lanfix.allhudbars.AllHudBars;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class ArmorBar {

    private static final Identifier fullBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/armor/full.png");
    private static final Identifier emptyBar = Identifier.of(AllHudBars.MOD_ID, "textures/gui/bars/armor/empty.png");

    private static final Identifier armorIcon = Identifier.of("textures/gui/sprites/hud/armor_full.png");

    // TODO Change armor texture depending on current equipped armor

    public static void renderArmorBar(TextRenderer textRenderer, DrawContext context, PlayerEntity player, int y, int left) {
        int armor = player.getArmor();
        if (armor > 0) {
            int textureWidth = 80;
            int armorWidth = Math.min(armor * textureWidth / 20, textureWidth);

            context.drawTexture(RenderLayer::getGuiTextured, emptyBar, left + armorWidth, y, armorWidth, 0, textureWidth - armorWidth, 9, textureWidth, 9);
            context.drawTexture(RenderLayer::getGuiTextured, fullBar, left, y, 0, 0, armorWidth, 9, textureWidth, 9);

            int offX = 1;
            int offY = -5;

            context.drawTexture(RenderLayer::getGuiTextured, armorIcon, left + offX, y + offY, 0, 0, 9, 9, 9, 9);

            // TODO Idea: Instead of this Icon, display the armor set with player.getArmorItems()

            offX += 11;
            offY += 1;

            context.drawText(textRenderer, String.valueOf(armor), left + offX + 1, y + offY, 0x000000, false);
            context.drawText(textRenderer, String.valueOf(armor), left + offX - 1, y + offY, 0x000000, false);
            context.drawText(textRenderer, String.valueOf(armor), left + offX, y + offY + 1, 0x000000, false);
            context.drawText(textRenderer, String.valueOf(armor), left + offX, y + offY - 1, 0x000000, false);
            context.drawText(textRenderer, String.valueOf(armor), left + offX, y + offY, 0xB7B8C2, false);
//            context.drawItem(new ItemStack(Items.IRON_CHESTPLATE), left + 15, y - 1);
        }
    }

}
