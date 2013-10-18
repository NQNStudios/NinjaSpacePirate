package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.Game;
import com.punchline.javalib.states.InputScreen;
import com.punchline.javalib.utils.Display;

/**
 * Base class for menu screens.
 * @author Natman64
 * @created Oct 17, 2013
 */
public abstract class MenuScreen extends InputScreen {

	/** The number of pixels between lines. */
	protected static final float LINE_PADDING = 4f;
	
	/** The SpriteBatch used for rendering this screen. */
	protected SpriteBatch spriteBatch;
	
	/** The BitmapFont used for rendering this screen. */
	protected BitmapFont font;
	
	/** This menu's buttons. */
	protected Array<MenuButton> buttons = new Array<MenuButton>();
	
	/**
	 * Constructor for MenuScreen
	 * @param game
	 */
	public MenuScreen(Game game) {
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
		Vector2 position = new Vector2(Display.getPreferredWidth() / 2, Display.getPreferredHeight() / 2);
		
		spriteBatch.begin();
		
		for (MenuButton button : buttons) {
			button.draw(spriteBatch, position);
			
			position.y -= button.getBounds().height;
			position.y -= LINE_PADDING;
		}
		
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
		spriteBatch.dispose();
		font.dispose();
	}

}
