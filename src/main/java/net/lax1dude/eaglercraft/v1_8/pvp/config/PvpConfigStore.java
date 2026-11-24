package net.lax1dude.eaglercraft.v1_8.pvp.config;

import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public final class PvpConfigStore {

	private static final Logger logger = LogManager.getLogger("PvpConfigStore");
	private static final String STORAGE_KEY = "pvp";
	private static final long SAVE_DELAY_MILLIS = 500L;

	private PvpConfig config;
	private boolean dirty;
	private long saveAt;

	public PvpConfigStore() {
		load();
	}

	public PvpConfig getConfig() {
		return config;
	}

	public void markDirty() {
		dirty = true;
		saveAt = EagRuntime.steadyTimeMillis() + SAVE_DELAY_MILLIS;
	}

	public void tick() {
		if(dirty && EagRuntime.steadyTimeMillis() >= saveAt) {
			saveNow();
		}
	}

	public void load() {
		config = new PvpConfig();
		byte[] data = EagRuntime.getStorage(STORAGE_KEY);
		if(data == null) {
			return;
		}
		try {
			String json = new String(data, StandardCharsets.UTF_8);
			config = PvpConfig.fromJson(json);
		}catch(JSONException ex) {
			logger.warn("Invalid PvP client configuration in storage; using defaults.");
			config = new PvpConfig();
		}catch(Throwable ex) {
			logger.warn("Could not read PvP client configuration; using defaults.");
			config = new PvpConfig();
		}
		dirty = false;
	}

	public void saveNow() {
		if(config == null) {
			return;
		}
		try {
			JSONObject json = config.toJson();
			byte[] data = json.toString().getBytes(StandardCharsets.UTF_8);
			EagRuntime.setStorage(STORAGE_KEY, data);
			dirty = false;
		}catch(Throwable ex) {
			logger.warn("Could not save PvP client configuration.");
		}
	}

}
