package net.lax1dude.eaglercraft.v1_8.pvp.hud.modules;

import net.lax1dude.eaglercraft.v1_8.pvp.PvpRuntime;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElement;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.PvpContext;
import net.minecraft.client.Minecraft;

public final class CpsHud implements HudElement {

	private final Minecraft minecraft;

	public CpsHud(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public String getId() {
		return "cps";
	}

	@Override
	public int getWidth(PvpContext context) {
		PvpRuntime runtime = context.runtime;
		String text = "CPS: " + runtime.getLeftCps();
		return minecraft.fontRendererObj.getStringWidth(text) + 4;
	}

	@Override
	public int getHeight(PvpContext context) {
		return 11;
	}

	@Override
	public void render(PvpContext context, int x, int y) {
		PvpRuntime runtime = context.runtime;
		String text = "CPS: " + runtime.getLeftCps();
		minecraft.fontRendererObj.drawStringWithShadow(text, x + 2, y + 2, 0xFFFFFFFF);
	}

}
