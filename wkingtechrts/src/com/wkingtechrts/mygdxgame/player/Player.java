package com.wkingtechrts.mygdxgame.player;

import com.wkingtechrts.mygdxgame.automaton.AutoActor;

public class Player {

	/* Resources held */
	private int money;
	private int food;
	private int ore;
	private int wood;
	
	/* Army created */
	private AutoActor[] army;
	
	/*Buildings owned */
	/*should have array or buildings*/
	public boolean buildingMode = false;
	
	
	public Player()
	{
		this.money = 500;
	}
	
	/* Setters and Getters for the instance variables in Player */
	public int getMoney()
	{
		return money;
	}
	
	public void setMoney(int m)
	{
		money = m;
	}
	
	public int getFood()
	{
		return food;
	}
	
	public void setfood(int f)
	{
		food = f;
	}
	
	public int getOre()
	{
		return ore;
	}
	
	public void setOre(int o)
	{
		ore = o;
	}
	
	public int getWood()
	{
		return wood;
	}
	
	public void setWood(int w)
	{
		wood = w;
	}
	
	public void toggleBuilding()
	{
		if(buildingMode)
		{
			buildingMode = false;
		}else{
			buildingMode = true;
		}
	}
	
	public boolean isBuilding()
	{
		return buildingMode;
	}
	
	public void build(int x, int y)
	{
		System.out.println("Built building at ("+x+","+y+")");
	}
}
