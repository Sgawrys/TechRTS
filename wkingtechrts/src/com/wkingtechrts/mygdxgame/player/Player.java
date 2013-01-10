package com.wkingtechrts.mygdxgame.player;

import com.wkingtechrts.mygdxgame.automaton.AutoActor;
import com.wkingtechrts.mygdxgame.automaton.AutoActorRenderer;
import com.wkingtechrts.mygdxgame.buildings.*;
public class Player {

	/* Resources held */
	private int money;
	private int food;
	private int ore;
	private int wood;
	
	/* Army created */
	private AutoActorRenderer actorRender;
	
	/*Buildings owned */
	/*should have array or buildings*/
	private BuildingsRender buildingRender;
	public boolean buildingMode = false;
	
	

	private BuildingType currentBuild;
	
	public Player(BuildingsRender br, AutoActorRenderer aar)
	{
		buildingRender = br;
		actorRender = aar;
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
	
	public void buildSet(BuildingType bt)
	{
		currentBuild = bt;
	}
	
	public BuildingType currentBuild()
	{
		return currentBuild;
	}
	
	public void build(int x, int y)
	{
		System.out.println("Built building at ("+x+","+y+")");
		if(currentBuild != null)
		{
			Buildings build = new Buildings(x,y,currentBuild);
			buildingRender.addToRender(build);
		}
	}
}
