package com.wkingtechrts.mygdxgame.buildings;

import com.badlogic.gdx.Gdx;

public enum BuildingType
{
	CASTLE(5,5,5.0f,500),
	SPAWNERY(5,5,20.0f,250),
	RESOURCE(5,5,2.0f,100),
	MORALE(5,5,50.0f,800),
	DEFENSES(5,5,120.0f,1000);
	
	private final int defense;
	private final int level;
	private final float interval;
	private final int cost;
	
	BuildingType(int defense, int level, float i, int price)
	{
		this.defense = defense;
		this.level = level;
		this.interval = i;
		this.cost = price;
	}
	
	public int getDefense()
	{
		return this.defense;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public float getInterval()
	{
		return this.interval;
	}
	
	public int getCost()
	{
		return this.cost;
	}
}