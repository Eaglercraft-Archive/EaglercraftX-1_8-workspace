package net.lax1dude.eaglercraft.v1_8.pvp.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.v1_8.pvp.PvpRuntime;

public final class PvpContext {

	public final Minecraft minecraft;
	public final ScaledResolution resolution;
	public final float partialTicks;
	public final PvpRuntime runtime;

	public PvpContext(Minecraft minecraft, ScaledResolution resolution, float partialTicks, PvpRuntime runtime) {
		this.minecraft = minecraft;
		this.resolution = resolution;
		this.partialTicks = partialTicks;
		this.runtime = runtime;
	}

	public int getScaledWidth() {
		return resolution.getScaledWidth();
	}

	public int getScaledHeight() {
		return resolution.getScaledHeight();
	}

}
