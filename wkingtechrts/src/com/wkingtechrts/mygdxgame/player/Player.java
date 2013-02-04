package com.wkingtechrts.mygdxgame.player;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.wkingtechrts.mygdxgame.automaton.AutoActor;
import com.wkingtechrts.mygdxgame.automaton.AutoActorRenderer;
import com.wkingtechrts.mygdxgame.buildings.*;
public class Player {

	/* Resources held */
	private int money;
	private int food;
	private int ore;
	private int wood;
	private int population;
	
	/* Army created */
	private AutoActorRenderer actorRender;
	
	/*Buildings owned */
	/*should have array or buildings*/
	private BuildingsRender buildingRender;
	public boolean buildingMode = false;
	public boolean selectingMode = false;
	

	private BuildingType currentBuild;
	
	public Player(BuildingsRender br, AutoActorRenderer aar)
	{
		buildingRender = br;
		actorRender = aar;
		this.money = 100000;
	}
	
	/* Setters and Getters for the instance variables in Player */
	public int getMoney()
	{
		return money;
	}
	
	public void setMoney(int m)
	{
		money += m;
	}
	
	public int getFood()
	{
		return food;
	}
	
	public void setfood(int f)
	{
		food += f;
	}
	
	public int getOre()
	{
		return ore;
	}
	
	public void setOre(int o)
	{
		ore += o;
	}
	
	public int getWood()
	{
		return wood;
	}
	
	public void setWood(int w)
	{
		wood += w;
	}
	
	public int getPopulation()
	{
		return population;
	}
	
	public void setPopulation(int p)
	{
		population += p;
	}
	
	public void toggleSelecting()
	{
		if(selectingMode)
		{
			selectingMode = false;
		}else{
			selectingMode = true;
		}
		
		System.out.println("Selecting system on?: "+selectingMode);
	}
	
	public boolean isSelecting()
	{
		return selectingMode;
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
		if(currentBuild.getCost() > money)
			return;

		for(Buildings b : BuildingsRender.buildingList)
		{
			if(b.contains(x, y))
			{
				return;
			}
		}
		System.out.println("Built building at ("+x+","+y+")");
		if(currentBuild != null)
		{
			Buildings build = new Buildings(x,y,currentBuild);
			Timer.schedule(new BuildingTask(currentBuild, this), 0.0f, currentBuild.getInterval());
			buildingRender.addToRender(build);
			this.money -= currentBuild.getCost();
		}
	}
	
	public void select(int x, int y)
	{
		System.out.println("Inside the selection method.");
		for(Buildings b : BuildingsRender.buildingList)
		{
			System.out.println("Trying to select at ("+x+","+y+") when top corner is ("+b.boundingBox.x+","+b.boundingBox.y+")"+b.boundingBox.width + " " + b.boundingBox.height);
			if(b.contains(x,y))
			{
				System.out.println("Selecting building at("+x+","+y+")");
				b.toggleSelect();
			}
		}
	}
}
