package net.lax1dude.eaglercraft.v1_8.pvp.hud.modules;

import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElement;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.PvpContext;
import net.minecraft.client.Minecraft;

public final class CoordinatesHud implements HudElement {

	private final Minecraft minecraft;

	public CoordinatesHud(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public String getId() {
		return "coordinates";
	}

	@Override
	public int getWidth(PvpContext context) {
		if(minecraft.thePlayer == null) {
			return 0;
		}
		String text = getText();
		return minecraft.fontRendererObj.getStringWidth(text) + 4;
	}

	@Override
	public int getHeight(PvpContext context) {
		return 11;
	}

	@Override
	public void render(PvpContext context, int x, int y) {
		if(minecraft.thePlayer != null) {
			minecraft.fontRendererObj.drawStringWithShadow(getText(), x + 2, y + 2, 0xFFFFFFFF);
		}
	}

	private String getText() {
		return "XYZ: " + (int) minecraft.thePlayer.posX + " " + (int) minecraft.thePlayer.posY + " "
				+ (int) minecraft.thePlayer.posZ;
	}

}
