package com.wkingtechrts.mygdxgame.automaton;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;
import com.wkingtechrts.mygdxgame.terrain.TerrainTile;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AutoActor extends Actor {

	private Texture texture;
	private Sprite sprite;
	private Vector2 position;
	private LinkedList<TerrainTile> path;
	private int currentPathIndex;
	private boolean isSelected;
	
	
	public AutoActor(float x, float y, Texture tex)
	{
		texture = tex;
		position = new Vector2(x,y);
		
		TextureRegion tr = new TextureRegion(texture,0,0,texture.getWidth(),texture.getHeight());
		sprite = new Sprite(tr);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setSize(2, 2);
		sprite.setRotation(0);
		sprite.setPosition(position.x, position.y);
		super.setX(x);
		super.setY(y);
	}
	
	public void dispose()
	{
		texture.dispose();
	}
	
	public void setTexture(Texture newTex)
	{
		texture = newTex;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public void moveToTile()
	{
		if(path!=null)
		{
			TerrainTile t = path.get(currentPathIndex);
			sprite.setX(t.getPosition().x);
			sprite.setY(t.getPosition().y);
			if(currentPathIndex < path.size()-1)
			{
				currentPathIndex++;
			}else{
				path = null;
				currentPathIndex = 0;
			}
		}
	}
	
	public void generatePath(int x, int y)
	{
		Vector2 goal = new Vector2(x,y);
		Vector2 start = new Vector2((int)sprite.getX(),(int)sprite.getY());
		System.out.println("Running Jump Search");
		JumpSearch js = new JumpSearch();
		path = js.findPath(start, goal, TerrainGenerator.tileMap);
		try{
			
			for(TerrainTile t : path)
			{
				t.setColor(new Color(1.0f,1.0f,0.0f,1.0f));
				System.out.println("Tile at position ("+t.getPosition().x+","+t.getPosition().y+")");
			}
		}catch(NullPointerException npe)
		{
			System.out.println("PATH NOT FOUND.");
		}
		for(TerrainTile[] t: TerrainGenerator.tileMap)
		{
			for(TerrainTile tile : t)
			{
				tile.closed = false;
				tile.opened = false;
				tile.f = 0.0;
				tile.g = 0.0;
				tile.h = 0.0;
				tile.parent = null;
			}
		}
	}
	
	public void select()
	{
		isSelected = true;
	}
	
	public void deselect()
	{
		isSelected = false;
	}
	
	public boolean onScreen(int sx, int ex, int sy, int ey)
	{
		if(this.sprite.getX() >= sx && this.sprite.getX() <= ex)
			if(this.sprite.getY() >= sy && this.sprite.getY() <= ey)
				return true;
		return false;
	}

}

