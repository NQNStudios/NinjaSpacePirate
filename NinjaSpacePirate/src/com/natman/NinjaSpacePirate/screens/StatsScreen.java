package com.natman.NinjaSpacePirate.screens;

import java.util.Iterator;
import java.util.Map.Entry;

import com.lostcode.javalib.Game;
import com.lostcode.javalib.utils.Display;
import com.natman.NinjaSpacePirate.gameplay.Stat;
import com.natman.NinjaSpacePirate.gameplay.Stats;
import com.natman.NinjaSpacePirate.screens.MenuButton.ButtonCallback;

public class StatsScreen extends MenuScreen {
	
	@SuppressWarnings("rawtypes")
	public StatsScreen(Game game) {
		super(game, "STATS");
		
		titleY = 7 * Display.getPreferredHeight() / 8;
		buttonY = 2 * Display.getPreferredHeight() / 3;
		buttonScale = 0.6f;
		
		Stats.loadStats();
		
		Iterator<Entry<String, Stat>> it = Stats.getRecords().entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Stat> entry = it.next();
			
			MenuButton button = new MenuButton(font, entry.getKey() + ":" + entry.getValue().formatValue());
			buttons.add(button);
		}
		
		buttons.add(new MenuButton(font, ""));
		
		it = Stats.getStats().entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Stat> entry = it.next();
			
			MenuButton button = new MenuButton(font, entry.getKey() + ":" + entry.getValue().formatValue());
			buttons.add(button);
		}
		
		buttons.add(new MenuButton(font, ""));
		
		MenuButton backButton = new MenuButton(font, "Back");
		backButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(backButton);
	}
	
}