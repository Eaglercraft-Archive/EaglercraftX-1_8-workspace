package net.lax1dude.eaglercraft.v1_8.pvp;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
public final class PvpRuntime {

	private static final int CLICK_HISTORY_SIZE = 128;
	private static final long CPS_WINDOW_MILLIS = 1000L;

	private final long[] leftClicks = new long[CLICK_HISTORY_SIZE];
	private final long[] rightClicks = new long[CLICK_HISTORY_SIZE];
	private int leftClickIndex;
	private int rightClickIndex;

	public void tick() {
	}

	public void recordMouseClick(int button) {
		if(button == 0) {
			recordClick(leftClicks, leftClickIndex);
			leftClickIndex = (leftClickIndex + 1) % CLICK_HISTORY_SIZE;
		}else if(button == 1) {
			recordClick(rightClicks, rightClickIndex);
			rightClickIndex = (rightClickIndex + 1) % CLICK_HISTORY_SIZE;
		}
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
