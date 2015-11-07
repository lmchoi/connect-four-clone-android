package com.codemonkeys.spike.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.codemonkeys.spike.libgdx.LibGdxSpike;

public class DesktopLauncher {
	public final static int TARGET_WIDTH = 480;
	public final static int TARGET_HEIGHT = 640;

	public static void main (String[] arg) {
		new LwjglApplication(new LibGdxSpike(TARGET_WIDTH, TARGET_HEIGHT), "LOL face", TARGET_WIDTH, TARGET_HEIGHT);
	}
}
