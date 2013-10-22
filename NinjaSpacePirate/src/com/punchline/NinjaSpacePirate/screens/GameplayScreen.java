package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.punchline.NinjaSpacePirate.gameplay.StealthGameOverInfo;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.javalib.Game;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.states.InputScreen;

/**
 * The NinjaSpacePirate gameplay screen.
 * @author Natman64
 *
 */
public class GameplayScreen extends InputScreen {

	private StealthWorld world;
	private Camera camera;
	
	/**
	 * Makes a GameplayScreen.
	 * @param game
	 */
	public GameplayScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera(56, 96);
		world = new StealthWorld(game.getInput(), camera);
	}

	@Override
	public void render(float delta) {
		world.process();
		
		if (world.isGameOver()) {
			StealthGameOverInfo info = (StealthGameOverInfo) world.getGameOverInfo();
			
			exit();
			game.getScreenManager().addScreen(new GameOverScreen(game, info.score));
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void show() {
		super.show();
		
		world.resume(); 
	}

	@Override
	public void hide() {
		super.hide();
		
		world.pause();
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

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			Entity player = world.getPlayer();
			Health health = player.getComponent(Health.class);
				
			if (!health.isEmpty()) {
				game.getScreenManager().addScreen(new PauseScreen(game));
			}
		}
		
		return true;
	}
	
	

}
