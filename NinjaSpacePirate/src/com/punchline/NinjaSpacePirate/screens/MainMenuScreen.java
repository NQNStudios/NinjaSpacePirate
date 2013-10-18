package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.Game;
import com.punchline.javalib.states.GameScreen;
import com.punchline.javalib.utils.Display;

/**
 * NinjaSpacePirate's main menu screen.
 * @author Natman64
 * @created  Oct 17, 2013
 */
public class MainMenuScreen extends GameScreen {
	
	private static final String TITLE_0 = "NINJA SPACE";
	private static final String TITLE_1 = "PIRATE";
	
	private static final float LINE_PADDING = 4f;
	
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	/**
	 * Constructs the screen.
	 * @param game
	 */
	public MainMenuScreen(Game game) {
		super(game);
		
		spriteBatch = new SpriteBatch();
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, 
				Display.getPreferredWidth(), Display.getPreferredHeight());
		
		font = new BitmapFont(
				Gdx.files.internal("data/8BitWonder.fnt"),
				Gdx.files.internal("data/8BitWonder.png"), false);
	}

	@Override
	public void render(float delta) {
		spriteBatch.begin();
		
		float x = Display.getPreferredWidth() / 2;
		float y = 3 * Display.getPreferredHeight() / 4;
		
		TextBounds bounds = font.getBounds(TITLE_0);
		
		font.draw(spriteBatch, TITLE_0, x - bounds.width / 2, y - bounds.height / 2);
		
		y -= bounds.height;
		y -= LINE_PADDING;
		bounds = font.getBounds(TITLE_1);
		font.draw(spriteBatch, TITLE_1, x - bounds.width / 2, y - bounds.height / 2);
		
		
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		font.dispose();
	}

}
