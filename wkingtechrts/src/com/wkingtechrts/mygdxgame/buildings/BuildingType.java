package com.wkingtechrts.mygdxgame.buildings;

import com.badlogic.gdx.Gdx;

public enum BuildingType
{
	CASTLE(5,5),
	SPAWNERY(5,5),
	RESOURCE(5,5),
	MORALE(5,5),
	DEFENSES(5,5);
	
	private final int defense;
	private final int level;
	
	BuildingType(int defense, int level)
	{
		this.defense = defense;
		this.level = level;
	}
	
	BuildingType(int defense)
	{
		this.defense = defense;
		this.level = 1;
	}
	
	public int getDefense()
	{
		return this.defense;
	}
	
	public int getLevel()
	{
		return this.level;
	}
}