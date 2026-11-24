package net.lax1dude.eaglercraft.v1_8.pvp.hud.modules;

import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElement;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.PvpContext;
import net.minecraft.client.Minecraft;

public final class FpsHud implements HudElement {

	private final Minecraft minecraft;
	private int cachedFps = -1;
	private String cachedText = "";
	private int cachedWidth;

	public FpsHud(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public String getId() {
		return "fps";
	}

	@Override
	public int getWidth(PvpContext context) {
		refresh(context);
		return cachedWidth;
	}

	@Override
	public int getHeight(PvpContext context) {
		return 11;
	}

	@Override
	public void render(PvpContext context, int x, int y) {
		refresh(context);
		minecraft.fontRendererObj.drawStringWithShadow(cachedText, x + 2, y + 2, 0xFFFFFFFF);
	}

	private void refresh(PvpContext context) {
		int fps = Minecraft.getDebugFPS();
		if(fps != cachedFps) {
			cachedFps = fps;
			cachedText = "FPS: " + fps;
			cachedWidth = minecraft.fontRendererObj.getStringWidth(cachedText) + 4;
		}
	}

}
