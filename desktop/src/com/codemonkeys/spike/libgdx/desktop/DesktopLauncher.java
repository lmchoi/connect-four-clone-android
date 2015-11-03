package com.codemonkeys.spike.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.codemonkeys.spike.libgdx.LibGdxSpike;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new LibGdxSpike(), "LOL face", 640, 480);
	}
}
