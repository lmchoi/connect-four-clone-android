package com.codemonkeys.spike.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.codemonkeys.spike.libgdx.ConnectFourApp;

public class DesktopLauncher {
	public final static int TARGET_WIDTH = 720;
	public final static int TARGET_HEIGHT = 1280;

	public static void main (String[] arg) {
		new LwjglApplication(new ConnectFourApp(), "LOL face", TARGET_WIDTH, TARGET_HEIGHT);
	}
}
