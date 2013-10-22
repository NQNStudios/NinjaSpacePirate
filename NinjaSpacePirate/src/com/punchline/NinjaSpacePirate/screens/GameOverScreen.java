package com.punchline.NinjaSpacePirate.screens;

import com.punchline.NinjaSpacePirate.gameplay.GameScore;
import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Display;
import com.punchline.javalib.utils.SoundManager;

public class GameOverScreen extends MenuScreen {

	private static final float TIME_PER_NUMBER = 0.005f;
	
	private static final int PRE_COUNT = -1;
	private static final int METERS = 0;
	private static final int COINS = 1;
	private static final int POTIONS = 2;
	private static final int KILLS = 3;
	
	private float elapsedTime = 0f;
	private int currentCount = 0;
	private int totalCount = 0;
	
	private int currentStat = PRE_COUNT;
	
	private GameScore score;
	
	private MenuButton metersCount;
	private MenuButton coinsCount;
	private MenuButton potionsCount;
	private MenuButton killsCount;
	private MenuButton scoreCount;
	
	public GameOverScreen(Game game, GameScore score) {
		super(game, "Game Over");
		
		this.score = score;
		
		titleY = 7 * Display.getPreferredHeight() / 8;
		buttonY = 2 * Display.getPreferredHeight() / 3;
		buttonScale = 0.6f;
		
		metersCount = new MenuButton(font, "");
		coinsCount = new MenuButton(font, "");;
		potionsCount = new MenuButton(font, "");
		killsCount = new MenuButton(font, "");
		scoreCount = new MenuButton(font, "");
		MenuButton retryButton = new MenuButton(font, "Retry");
		MenuButton backButton = new MenuButton(font, "Back");
		
		retryButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				game.getScreenManager().closeActiveScreen();
				game.getScreenManager().addScreen(new GameplayScreen(game));
			}
			
		};
		
		backButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				game.getScreenManager().closeActiveScreen();
			}
			
		};
		
		buttons.add(metersCount);
		buttons.add(coinsCount);
		buttons.add(potionsCount);
		buttons.add(killsCount);
		buttons.add(scoreCount);
		buttons.add(new MenuButton(font, ""));
		buttons.add(retryButton);
		buttons.add(backButton);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		elapsedTime += delta;
		
		int currentMax;
		
		switch (currentStat) {
		
		case PRE_COUNT:
			currentMax = 0;
			break;
		
		case METERS:
			currentMax = score.meters;
			break;
			
		case COINS:
			currentMax = score.coins;
			break;
		
		case POTIONS:
			currentMax = score.potions;
			break;
			
		case KILLS:
			currentMax = score.kills;
			break;
			
		default:
			return;
			
		}
		
		while (elapsedTime >= TIME_PER_NUMBER) {
			currentCount++;
			totalCount++;
			SoundManager.playSound("Button_Hover");
			
			elapsedTime -= TIME_PER_NUMBER;
		}
		
		if (currentCount >= currentMax) {
			//start the next count
			
		}
		
		scoreCount.setText("Points: " + totalCount);
	}

}
