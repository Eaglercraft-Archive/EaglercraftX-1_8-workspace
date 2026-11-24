package net.lax1dude.eaglercraft.v1_8.pvp.hud;

import java.util.LinkedHashMap;
import java.util.Map;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.pvp.PvpRuntime;
import net.lax1dude.eaglercraft.v1_8.pvp.config.PvpConfig;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.modules.CoordinatesHud;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.modules.CpsHud;
import net.lax1dude.eaglercraft.v1_8.pvp.hud.modules.FpsHud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public final class HudManager {

	private final PvpConfig config;
	private final Minecraft minecraft;
	private final Map<String, HudElement> elements = new LinkedHashMap<>();
	private final PvpContext context;

	public HudManager(PvpConfig config, Minecraft minecraft) {
		this.config = config;
		this.minecraft = minecraft;
		this.context = new PvpContext(minecraft, null, 0.0f, null);
		register(new FpsHud(minecraft));
		register(new CoordinatesHud(minecraft));
		register(new CpsHud(minecraft));
	}

	private void register(HudElement element) {
		elements.put(element.getId(), element);
	}

	public void render(ScaledResolution resolution, float partialTicks, PvpRuntime runtime) {
		context.update(minecraft, resolution, partialTicks, runtime);
		for(HudElement element : elements.values()) {
			HudElementConfig elementConfig = config.getElement(element.getId());
			if(!elementConfig.enabled) {
				continue;
			}

			int width = element.getWidth(context);
			int height = element.getHeight(context);
			int scaledWidth = scaleDimension(width, elementConfig.scale);
			int scaledHeight = scaleDimension(height, elementConfig.scale);
			int x = resolveX(elementConfig, resolution.getScaledWidth(), scaledWidth);
			int y = resolveY(elementConfig, resolution.getScaledHeight(), scaledHeight);

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, 0.0f);
			GlStateManager.scale(elementConfig.scale, elementConfig.scale, 1.0f);
			element.render(context, 0, 0);
			GlStateManager.popMatrix();
		}
	}

	private int resolveX(HudElementConfig config, int screenWidth, int width) {
		switch(config.anchor) {
		case TOP_CENTER:
		case CENTER:
		case BOTTOM_CENTER:
			return (screenWidth - width) / 2 + config.offsetX;
		case TOP_RIGHT:
		case CENTER_RIGHT:
		case BOTTOM_RIGHT:
			return screenWidth - width - config.offsetX;
		default:
			return config.offsetX;
		}
	}

	private int resolveY(HudElementConfig config, int screenHeight, int height) {
		switch(config.anchor) {
		case CENTER_LEFT:
		case CENTER:
		case CENTER_RIGHT:
			return (screenHeight - height) / 2 + config.offsetY;
		case BOTTOM_LEFT:
		case BOTTOM_CENTER:
		case BOTTOM_RIGHT:
			return screenHeight - height - config.offsetY;
		default:
			return config.offsetY;
		}
	}

	private int scaleDimension(int dimension, float scale) {
		return (int) (dimension * scale + 0.5f);
	}

}
