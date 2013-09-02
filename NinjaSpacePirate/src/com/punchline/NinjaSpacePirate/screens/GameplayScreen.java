package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.punchline.NinjaSpacePirate.gameplay.StealthGameOverInfo;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.javalib.Game;
import com.punchline.javalib.states.GameScreen;

/**
 * The NinjaSpacePirate gameplay screen.
 * @author Natman64
 *
 */
public class GameplayScreen extends GameScreen {

	private StealthWorld world;
	private Camera camera;
	
	/**
	 * Makes a GameplayScreen.
	 * @param game
	 */
	public GameplayScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera(480, 800);
		world = new StealthWorld(game.getInput(), camera);
	}

	@Override
	public void render(float delta) {
		world.process();
		
		if (world.isGameOver()) {
			StealthGameOverInfo info = (StealthGameOverInfo) world.getGameOverInfo();
			
			//TODO Process the game over
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		world.pause();
	}

	@Override
	public void hide() {
		world.resume();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		world.dispose();
	}

}
