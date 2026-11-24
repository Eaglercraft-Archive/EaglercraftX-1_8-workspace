package net.lax1dude.eaglercraft.v1_8.pvp.hud;

import org.json.JSONObject;

public final class HudElementConfig {

	public enum Anchor {
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		CENTER_LEFT,
		CENTER,
		CENTER_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT
	}

	public boolean enabled;
	public Anchor anchor;
	public int offsetX;
	public int offsetY;
	public float scale;

	public HudElementConfig() {
		this(true, Anchor.TOP_LEFT, 0, 0, 1.0f);
	}

	public HudElementConfig(boolean enabled, Anchor anchor, int offsetX, int offsetY, float scale) {
		this.enabled = enabled;
		this.anchor = anchor;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.scale = scale;
	}

	public static HudElementConfig fromJson(JSONObject json, HudElementConfig defaults) {
		HudElementConfig result = new HudElementConfig(defaults.enabled, defaults.anchor, defaults.offsetX,
				defaults.offsetY, defaults.scale);
		result.enabled = json.optBoolean("enabled", defaults.enabled);
		String anchorName = json.optString("anchor", defaults.anchor.name());
		try {
			result.anchor = Anchor.valueOf(anchorName);
		}catch(Throwable ex) {
			result.anchor = defaults.anchor;
		}
		result.offsetX = json.optInt("offsetX", defaults.offsetX);
		result.offsetY = json.optInt("offsetY", defaults.offsetY);
		result.scale = (float) json.optDouble("scale", defaults.scale);
		if(result.scale < 0.5f) {
			result.scale = 0.5f;
		}else if(result.scale > 3.0f) {
			result.scale = 3.0f;
		}
		return result;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("enabled", enabled);
		json.put("anchor", anchor.name());
		json.put("offsetX", offsetX);
		json.put("offsetY", offsetY);
		json.put("scale", scale);
		return json;
	}

}
