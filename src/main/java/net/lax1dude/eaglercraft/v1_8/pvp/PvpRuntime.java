package net.lax1dude.eaglercraft.v1_8.pvp;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.Mouse;

public final class PvpRuntime {

	private static final int CLICK_HISTORY_SIZE = 128;
	private static final long CPS_WINDOW_MILLIS = 1000L;

	private final long[] leftClicks = new long[CLICK_HISTORY_SIZE];
	private final long[] rightClicks = new long[CLICK_HISTORY_SIZE];
	private int leftClickIndex;
	private int rightClickIndex;
	private boolean leftButtonDown;
	private boolean rightButtonDown;

	public void tick() {
		boolean leftDown = Mouse.isButtonDown(0);
		if(leftDown && !leftButtonDown) {
			recordClick(leftClicks, leftClickIndex);
			leftClickIndex = (leftClickIndex + 1) % CLICK_HISTORY_SIZE;
		}
		leftButtonDown = leftDown;

		boolean rightDown = Mouse.isButtonDown(1);
		if(rightDown && !rightButtonDown) {
			recordClick(rightClicks, rightClickIndex);
			rightClickIndex = (rightClickIndex + 1) % CLICK_HISTORY_SIZE;
		}
		rightButtonDown = rightDown;
	}

	private void recordClick(long[] clicks, int index) {
		clicks[index] = EagRuntime.steadyTimeMillis();
	}

	public int getLeftCps() {
		return countRecent(leftClicks);
	}

	public int getRightCps() {
		return countRecent(rightClicks);
	}

	private int countRecent(long[] clicks) {
		long now = EagRuntime.steadyTimeMillis();
		int count = 0;
		for(int i = 0; i < clicks.length; ++i) {
			long clickTime = clicks[i];
			if(clickTime != 0L && now - clickTime >= 0L && now - clickTime <= CPS_WINDOW_MILLIS) {
				++count;
			}
		}
		return count;
	}

}
