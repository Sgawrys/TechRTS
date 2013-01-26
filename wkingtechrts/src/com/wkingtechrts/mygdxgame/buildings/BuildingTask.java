package com.wkingtechrts.mygdxgame.buildings;

import com.badlogic.gdx.utils.Timer;
import com.wkingtechrts.mygdxgame.player.Player;

public class BuildingTask extends Timer.Task{

	private BuildingType type;
	private Player player;
	
	public BuildingTask(BuildingType bt, Player p)
	{
		type = bt;
		player = p;
	}
	
	public void run()
	{
		/*Switch based on the BuildingType*/
		switch(type)
		{
			case CASTLE:player.setMoney(20);break;
			case SPAWNERY:System.out.println("Spawnery task being run right now.");break;
			case RESOURCE:System.out.println("Resource task being run right now.");break;
			case MORALE:System.out.println("Morale task being run right now.");break;
		}
		/*Each time this run method is called, different types of buildings accumulate different types of resources*/
		
		
		/*Castle generates gold for the player*/
		/*Spawnery generates a population for the player so that they can build units*/
		/*Resource generates ore, wood, or food*/
		/*Morale affects morale for the population*/
		/*Defenses should deteriorate with time?*/
		
	}

	
}
