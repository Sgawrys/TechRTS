package com.wkingtechrts.mygdxgame.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Buildings {

	public int x;
	public int y;
	public boolean isSelected = false;
	public BuildingType type;
	private Sprite sprite;
	private Texture texture;
	public Rectangle boundingBox;
	
	public Buildings(int posx, int posy, BuildingType bt)
	{
		x = posx;
		y = posy;
		type = bt;
		
		switch(bt)
		{
			case CASTLE:texture = new Texture(Gdx.files.internal("data/debug/buildings/castle.png"));break;
			case RESOURCE:texture = new Texture(Gdx.files.internal("data/debug/buildings/farm.png"));break;
			case SPAWNERY:texture = new Texture(Gdx.files.internal("data/debug/buildings/archer.png"));break;
			default:break;
		}
		TextureRegion tr = new TextureRegion(texture,0,0,texture.getWidth(),texture.getHeight());
		sprite = new Sprite(tr);
		sprite.setOrigin(0,0);
		sprite.setSize(2.0f, 2.0f);
		sprite.setPosition(x, y);
		
		boundingBox = sprite.getBoundingRectangle();
	}
	
	public boolean isSelected()
	{
		return isSelected;
	}
	
	public void toggleSelect()
	{
		if(isSelected)
		{
			isSelected = false;
		}else{
			isSelected = true;
		}
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public boolean onScreen(int sx, int ex, int sy, int ey)
	{
		if(this.sprite.getX() >= sx && this.sprite.getX() <= ex)
			if(this.sprite.getY() >= sy && this.sprite.getY() <= ey)
				return true;
		return false;
	}
	
	public boolean contains(int x, int y)
	{
		System.out.println("New box coords ("+x+","+y+") Bounding Box : ("+this.boundingBox.x+","+this.boundingBox.y+") + Size:"+this.boundingBox.width + " "+ this.boundingBox.height);

		
		if(this.boundingBox.x-1 <= x && x <= this.boundingBox.x + this.boundingBox.width)
			if(this.boundingBox.y-1 <= y && y <= this.boundingBox.y + this.boundingBox.height)
				return true;
		return false;
		
		//Have to test for overlap better
	}
}
