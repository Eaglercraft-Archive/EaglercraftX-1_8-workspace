package net.lax1dude.eaglercraft.v1_8.pvp.hud.modules;

import net.lax1dude.eaglercraft.v1_8.pvp.PvpRuntime;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElement;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.PvpContext;
import net.minecraft.client.Minecraft;

public final class CpsHud implements HudElement {

	private final Minecraft minecraft;
	private int cachedLeftCps = -1;
	private int cachedRightCps = -1;
	private String cachedText = "";
	private int cachedWidth;

	public CpsHud(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public String getId() {
		return "cps";
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
		PvpRuntime runtime = context.runtime;
		int leftCps = runtime != null ? runtime.getLeftCps() : 0;
		int rightCps = runtime != null ? runtime.getRightCps() : 0;
		if(leftCps != cachedLeftCps || rightCps != cachedRightCps) {
			cachedLeftCps = leftCps;
			cachedRightCps = rightCps;
			cachedText = "CPS: L " + leftCps + " R " + rightCps;
			cachedWidth = minecraft.fontRendererObj.getStringWidth(cachedText) + 4;
		}
	}

}
