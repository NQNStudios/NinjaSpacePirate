package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
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
	protected static final float LINE_PADDING = 8f;
	
	/** The title of the menu */
	protected String title;
	
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
	public MenuScreen(Game game, String title) {
		super(game);
		
		this.title = title;
		
		spriteBatch = new SpriteBatch();
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, 
				Display.getPreferredWidth(), Display.getPreferredHeight());
		
		font = new BitmapFont(
				Gdx.files.internal("data/8BitWonder.fnt"),
				Gdx.files.internal("data/8BitWonder.png"), false);
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
	}
	
	@Override
	public void render(float delta) {
		Vector2 position = new Vector2(Display.getPreferredWidth() / 2, Display.getPreferredHeight() / 2);
		
		spriteBatch.begin();
		
		float x = Display.getPreferredWidth() / 2;
		float y = 3 * Display.getPreferredHeight() / 4;
		
		TextBounds bounds = font.getBounds(title);
		
		font.draw(spriteBatch, title, x - bounds.width / 2, y - bounds.height / 2);
		
		for (MenuButton button : buttons) {
			button.draw(spriteBatch, position);
			
			position.y -= button.getBounds().height;
			position.y -= LINE_PADDING;
		}
		
		spriteBatch.end();
	}
	
	@Override
	public void resize(int width, int height) { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 pos = Display.getAdjustedInput(new Vector2(screenX, screenY));
		
		if (Gdx.app.getType() == ApplicationType.Android) {
			Vector2 position = new Vector2(Display.getPreferredWidth() / 2, Display.getPreferredHeight() / 2);
			
			for (MenuButton menuButton : buttons) {
				menuButton.touchDown(pos, position);
				
				position.y -= menuButton.getBounds().height;
				position.y -= LINE_PADDING;
			}
		} else {
			for (MenuButton menuButton : buttons) {
				if (menuButton.isSelected()) {
					if (menuButton.onTrigger != null) {
						menuButton.onTrigger.invoke(game);
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 pos = Display.getAdjustedInput(new Vector2(screenX, screenY));
		
		if (Gdx.app.getType() == ApplicationType.Android) {
			Vector2 position = new Vector2(Display.getPreferredWidth() / 2, Display.getPreferredHeight() / 2);
			
			for (MenuButton menuButton : buttons) {
				menuButton.touchUp(pos, position, game);
				
				position.y -= menuButton.getBounds().height;
				position.y -= LINE_PADDING;
			}
		}
		
		return true;
	}

}
