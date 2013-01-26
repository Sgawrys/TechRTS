package com.wkingtechrts.mygdxgame.automaton;

import com.badlogic.gdx.utils.Timer;

public class AutoTask extends Timer.Task {
	
	private AutoActor actor;
	
	public AutoTask(AutoActor a)
	{
		actor = a;
	}
	
	public void run()
	{
		/*
		 * Could give the actor different behaviors here and define them as an enum , then check to see what behavior has been
		 * selected by the player and perform actions based on that.
		 */
		actor.moveToTile();
	}
}
