package com.punchline.NinjaSpacePirate;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.punchline.javalib.utils.LogManager;
import com.punchline.javalib.utils.LogManager.LogType;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "NinjaSpacePirate";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		cfg.resizable = false;
		

		LogManager.init(new LwjglApplication(new NinjaSpacePirate(), cfg), LogType.INFO) ;
	}
}
