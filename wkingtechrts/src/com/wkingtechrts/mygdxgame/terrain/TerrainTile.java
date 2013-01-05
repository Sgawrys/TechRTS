package com.wkingtechrts.mygdxgame.terrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class TerrainTile implements Comparable {
	
	static final float SIZE = 1.0f;
	
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	Color color = new Color();
	
	private boolean isChanged;
	private boolean walkable;
	
	
	/*Costs associated with tiles*/
	public double f; /*Total cost */
	public double g; /*Cost*/
	public double h; /*Heuristic cost*/
	
	public boolean opened;
	public boolean closed;
	
	public TerrainTile parent;
	
	public TerrainTile(Vector2 pos, Color type, boolean walk)
	{
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.color = type;
		this.walkable = walk;
	}
	
	public TerrainTile(Vector2 pos, boolean walk)
	{
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
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
	
	public boolean isWalkable()
	{
		return walkable;
	}

	public int compareTo(Object node) {
		TerrainTile compare = (TerrainTile)node;
		if(this.f < compare.f)
			return -1;
		return 1;
	}
}
