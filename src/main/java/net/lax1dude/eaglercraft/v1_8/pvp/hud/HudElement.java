package net.lax1dude.eaglercraft.v1_8.pvp.hud;

public interface HudElement {

	String getId();

	int getWidth(PvpContext context);

	int getHeight(PvpContext context);

	void render(PvpContext context, int x, int y);

}
