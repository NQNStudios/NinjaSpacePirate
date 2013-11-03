package com.natman.NinjaSpacePirate.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.utils.Display;
import com.lostcode.javalib.utils.SoundManager;

/**
 * A text button that is part of a menu.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class MenuButton {

	public interface ButtonCallback {
		public void invoke(Game game);
	}
	
	private static final Color COLOR = Color.WHITE;
	private static final Color SELECTED_COLOR = Color.YELLOW;
	
	private BitmapFont font;
	private String text;
	private boolean selected;
	
	/** Invoked when this button is pressed. */
	public ButtonCallback onTrigger;
	
	/**
	 * Constructs a MenuButton.
	 * @param font
	 * @param text
	 */
	public MenuButton(BitmapFont font, String text) {
		this.font = font;
		this.text = text;
	}
	
	/**
	 * Draws this button.
	 * @param batch
	 * @param position The center of where the text should be drawn.
	 */
	public void draw(SpriteBatch batch, Vector2 position) {
		TextBounds bounds = getBounds();
		
		float x = position.x - bounds.width / 2;
		float y = position.y - bounds.height / 2;
		Rectangle screenBounds = new Rectangle(x, y, bounds.width, bounds.height);
		
		if (Gdx.app.getType() != ApplicationType.Android && onTrigger != null) {
			boolean wasSelected = selected;
			selected = screenBounds.contains(Display.getAdjustedInput(new Vector2(Gdx.input.getX(), Gdx.input.getY())));
			
			if (!wasSelected && selected) {
				SoundManager.playSound("Button_Hover");
			}
		}
		
		if (selected) {
			font.setColor(SELECTED_COLOR);
		}
		
		y = position.y + bounds.height / 2;
		
		font.draw(batch, text, x, y);
		
		font.setColor(COLOR);
	}
	
	/**
	 * @return The bounds of this button's text.
	 */
	public TextBounds getBounds() {
		return font.getBounds(text);
	}
	
	/**
	 * @return Whether this button is selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Sets the text of this button.
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Called on Android when the screen is touched.
	 * @param input
	 * @param position
	 */
	public void touchDown(Vector2 input, Vector2 position) {
		if (onTrigger == null) return;
		
		TextBounds bounds = getBounds();
		
		float x = position.x - bounds.width / 2;
		float y = position.y - bounds.height / 2;
		Rectangle screenBounds = new Rectangle(x, y, bounds.width, bounds.height);
		
		selected = screenBounds.contains(input);
	}
	
	/**
	 * Called on Android when a screen touch is released
	 * @param input
	 * @param position
	 * @param game
	 */
	public void touchUp(Vector2 input, Vector2 position, Game game) {
		if (onTrigger == null) return;
		
		TextBounds bounds = getBounds();
		
		float x = position.x - bounds.width / 2;
		float y = position.y - bounds.height / 2;
		Rectangle screenBounds = new Rectangle(x, y, bounds.width, bounds.height);
		
		if (selected && screenBounds.contains(input)) {
			if (onTrigger != null) {
				onTrigger.invoke(game);
				SoundManager.playSound("Button_Press");
			}
		}
		
		selected = false;
	}
	
}
