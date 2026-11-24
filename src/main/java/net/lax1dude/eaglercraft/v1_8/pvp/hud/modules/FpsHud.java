package net.lax1dude.eaglercraft.v1_8.pvp.hud.modules;

import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElement;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.PvpContext;
import net.minecraft.client.Minecraft;

public final class FpsHud implements HudElement {

	private final Minecraft minecraft;

	public FpsHud(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public String getId() {
		return "fps";
	}

	@Override
	public int getWidth(PvpContext context) {
		return minecraft.fontRendererObj.getStringWidth("FPS: " + Minecraft.getDebugFPS()) + 4;
	}

	@Override
	public int getHeight(PvpContext context) {
		return 11;
	}

	@Override
	public void render(PvpContext context, int x, int y) {
		minecraft.fontRendererObj.drawStringWithShadow("FPS: " + Minecraft.getDebugFPS(), x + 2, y + 2, 0xFFFFFFFF);
	}

}
