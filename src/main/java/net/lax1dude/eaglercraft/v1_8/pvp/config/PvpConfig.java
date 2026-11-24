package net.lax1dude.eaglercraft.v1_8.pvp.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import net.lax1dude.eaglercraft.v1_8.pvp.hud.HudElementConfig;

public final class PvpConfig {

	public static final int CONFIG_VERSION = 1;

	public boolean enabled = true;
	public boolean hudEnabled = true;
	public final Map<String, HudElementConfig> elements = new LinkedHashMap<>();

	public PvpConfig() {
		elements.put("fps", new HudElementConfig(true, HudElementConfig.Anchor.TOP_LEFT, 5, 5, 1.0f));
		elements.put("coordinates", new HudElementConfig(true, HudElementConfig.Anchor.TOP_LEFT, 5, 20, 1.0f));
		elements.put("cps", new HudElementConfig(true, HudElementConfig.Anchor.TOP_LEFT, 5, 35, 1.0f));
	}

	public HudElementConfig getElement(String id) {
		HudElementConfig config = elements.get(id);
		if(config == null) {
			config = new HudElementConfig();
			elements.put(id, config);
		}
		return config;
	}

	public static PvpConfig fromJson(String json) {
		PvpConfig config = new PvpConfig();
		JSONObject root = new JSONObject(json);
		config.enabled = root.optBoolean("enabled", true);
		config.hudEnabled = root.optBoolean("hudEnabled", true);
		JSONObject elements = root.optJSONObject("elements");
		if(elements != null) {
			for(Map.Entry<String, HudElementConfig> entry : config.elements.entrySet()) {
				JSONObject element = elements.optJSONObject(entry.getKey());
				if(element != null) {
					entry.setValue(HudElementConfig.fromJson(element, entry.getValue()));
				}
			}
		}
		return config;
	}

	public JSONObject toJson() {
		JSONObject root = new JSONObject();
		root.put("version", CONFIG_VERSION);
		root.put("enabled", enabled);
		root.put("hudEnabled", hudEnabled);
		JSONObject elementsObject = new JSONObject();
		for(Map.Entry<String, HudElementConfig> entry : elements.entrySet()) {
			elementsObject.put(entry.getKey(), entry.getValue().toJson());
		}
		root.put("elements", elementsObject);
		return root;
	}

}
