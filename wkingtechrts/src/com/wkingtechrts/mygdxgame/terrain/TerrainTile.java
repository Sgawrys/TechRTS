package com.wkingtechrts.mygdxgame.terrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class TerrainTile {
	
	static final float SIZE = 1.0f;
	
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	Color color = new Color();
	
	private boolean isChanged;
	private int walkable;
	
	public TerrainTile(Vector2 pos, Color type, int walk)
	{
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.color = type;
		this.walkable = walk;
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
	
	public void setColor(Color c)
	{
		color = c;
	}
	
	public boolean getChange()
	{
		return isChanged;
	}
	
	public void setChange(boolean b)
	{
		isChanged = b;
	}
	
	public int isWalkable()
	{
		return walkable;
	}
}
