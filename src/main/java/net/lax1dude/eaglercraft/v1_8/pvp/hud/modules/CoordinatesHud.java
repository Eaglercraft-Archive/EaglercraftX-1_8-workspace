package net.lax1dude.eaglercraft.v1_8.pvp.hud.modules;

import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElement;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.PvpContext;
import net.minecraft.client.Minecraft;

public final class CoordinatesHud implements HudElement {

	private final Minecraft minecraft;
	private int cachedX = Integer.MIN_VALUE;
	private int cachedY = Integer.MIN_VALUE;
	private int cachedZ = Integer.MIN_VALUE;
	private String cachedText = "";
	private int cachedWidth;

	public CoordinatesHud(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public String getId() {
		return "coordinates";
	}

	@Override
	public int getWidth(PvpContext context) {
		refresh();
		return cachedWidth;
	}

	@Override
	public int getHeight(PvpContext context) {
		return 11;
	}

	@Override
	public void render(PvpContext context, int x, int y) {
		if(minecraft.thePlayer != null) {
			refresh();
			minecraft.fontRendererObj.drawStringWithShadow(cachedText, x + 2, y + 2, 0xFFFFFFFF);
		}
	}

	private void refresh() {
		if(minecraft.thePlayer == null) {
			cachedWidth = 0;
			cachedText = "";
			cachedX = Integer.MIN_VALUE;
			cachedY = Integer.MIN_VALUE;
			cachedZ = Integer.MIN_VALUE;
			return;
		}
		int x = (int) minecraft.thePlayer.posX;
		int y = (int) minecraft.thePlayer.posY;
		int z = (int) minecraft.thePlayer.posZ;
		if(x != cachedX || y != cachedY || z != cachedZ) {
			cachedX = x;
			cachedY = y;
			cachedZ = z;
			cachedText = "XYZ: " + x + " " + y + " " + z;
			cachedWidth = minecraft.fontRendererObj.getStringWidth(cachedText) + 4;
		}
	}

}
