package com.wkingtechrts.mygdxgame.buildings;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Buildings {

	public int x;
	public int y;
	public boolean isSelected = false;
	public BuildingType type;
	
	private Sprite sprite;
	
	public Buildings(int posx, int posy, BuildingType bt)
	{
		x = posx;
		y = posy;
		type = bt;
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
}
