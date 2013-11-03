package com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn;

import com.lostcode.javalib.entities.EntityWorld;

/** 
 * Contains the information needed to construct a tile, minus its position. 
 */
public class TileArgs {
	
	/** The key of the sprite's TextureRegion in the game's SpriteSheet. */
	public String spriteKey;
	
	/** Whether this tile is a physical obstacle. */
	public boolean blocked;
	
	/** Constructs TileArgs.
	 * @param spriteKey The key of the tile's sprite.
	 * @param blocked Whether this tile is a physical obstacle. */
	public TileArgs(String spriteKey, boolean blocked) {
		this.spriteKey = spriteKey;
		this.blocked = blocked;
	}
	
	/**
	 * Event for performing extra processing when created, such as creating an entity to go with the tile.
	 * @param world Reference to the EntityWorld.
	 * @param x The x coordinate of the tile.
	 * @param y The y coordinate of the tile.
	 */
	public void onCreated(EntityWorld world, float x, float y) {
		
	}
	
}