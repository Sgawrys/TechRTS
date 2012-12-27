package com.wkingtechrts.mygdxgame.terrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class TerrainTile {
	
	static final float SIZE = 1.0f;
	
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	Color color = new Color();
	
	public TerrainTile(Vector2 pos, Color type)
	{
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.color = type;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public Color getColor()
	{
		return color;
	}
}
