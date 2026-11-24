package net.lax1dude.eaglercraft.v1_8.pvp;

import net.lax1dude.eaglercraft.v1_8.pvp.config.PvpConfigStore;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public final class PvpClient {

	private static PvpClient instance;

	private final Minecraft minecraft;
	private final PvpConfigStore configStore;
	private final PvpRuntime runtime;
	private final HudManager hudManager;

	private PvpClient(Minecraft minecraft) {
		this.minecraft = minecraft;
		this.configStore = new PvpConfigStore();
		this.runtime = new PvpRuntime();
		this.hudManager = new HudManager(configStore.getConfig(), minecraft);
	}

	public static PvpClient initialize(Minecraft minecraft) {
		if(instance == null) {
			instance = new PvpClient(minecraft);
		}
		return instance;
	}

	public static PvpClient getInstance() {
		return instance;
	}

	public void tick() {
		runtime.tick();
		configStore.tick();
	}

	public void recordMouseClick(int button) {
		runtime.recordMouseClick(button);
	}

	public void renderHud(ScaledResolution resolution, float partialTicks) {
		if(configStore.getConfig().enabled && configStore.getConfig().hudEnabled
				&& minecraft.theWorld != null && minecraft.thePlayer != null
				&& minecraft.currentScreen == null) {
			hudManager.render(resolution, partialTicks, runtime);
		}
	}

	public void shutdown() {
		configStore.saveNow();
	}

	public PvpConfigStore getConfigStore() {
		return configStore;
	}

	public HudManager getHudManager() {
		return hudManager;
	}

	public PvpRuntime getRuntime() {
		return runtime;
	}

}
