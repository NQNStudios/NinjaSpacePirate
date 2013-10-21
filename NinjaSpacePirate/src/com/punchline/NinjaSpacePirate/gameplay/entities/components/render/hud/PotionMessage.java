package com.punchline.NinjaSpacePirate.gameplay.entities.components.render.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.Display;

/**
 * Message that appears at the bottom of the screen informing the player about a potion effect.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class PotionMessage extends Sprite {

	private static final float DIALOG_SCALE = 1f;
	private static final float DIALOG_Y = Convert.metersToPixels(-5.3f);
	
	private static final float TEXT_SCALE = 0.6f;
	private static final float TEXT_Y = 57;
	
	private static final float TIME_SCALE = 1.5f;
	private static final float TIME_X = 400;
	private static final float TIME_Y = 90;
	
	private EntityWorld world;
	private String message;
	private float duration;
	
	private BitmapFont font;
	private Matrix4 tempProjectionMatrix;
	
	/**
	 * Constructs a PotionMessage.
	 * @param message
	 * @param duration
	 */
	public PotionMessage(EntityWorld world, String message, float duration) {
		super(world.getSpriteSheet(), "PotionDialog");
		
		this.world = world;
		this.message = message;
		this.duration = duration;
		
		font = new BitmapFont(
				Gdx.files.internal("data/Fonts/8BitWonder.fnt"), 
				Gdx.files.internal("data/Fonts/8BitWonder.png"), false);
		
		tempProjectionMatrix = new Matrix4();
		tempProjectionMatrix.setToOrtho2D(0, 0, Display.getPreferredWidth(), Display.getPreferredHeight());
		
		setPosition(new Vector2(0, 0));
		setScale(DIALOG_SCALE, DIALOG_SCALE);
		setLayer(200);
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float deltaSeconds) {
		Vector2 pos = getPosition();
		setPosition(new Vector2(pos.x, world.getCamera().position.cpy().y + DIALOG_Y));
		
		super.draw(spriteBatch, deltaSeconds);
		
		Matrix4 projectionMatrix = spriteBatch.getProjectionMatrix();
		spriteBatch.setProjectionMatrix(tempProjectionMatrix); //temporarily change the projection matrix
		
		font.setScale(TEXT_SCALE);
		TextBounds bounds = font.getBounds(message);
		float x = Display.getPreferredWidth() / 2 - bounds.width / 2;
		font.draw(spriteBatch, message, x, TEXT_Y);
		
		font.setScale(TIME_SCALE);
		font.draw(spriteBatch, "" + (int) duration, TIME_X - font.getBounds("" + (int) duration).width / 2, TIME_Y - font.getBounds("" + (int) duration).height / 2);
		
		spriteBatch.setProjectionMatrix(projectionMatrix); //return the SpriteBatch to normal
		
		duration -= deltaSeconds;
	}

	@Override
	public void onRemove(ComponentManager container) {
		font.dispose();
	}
	
}
