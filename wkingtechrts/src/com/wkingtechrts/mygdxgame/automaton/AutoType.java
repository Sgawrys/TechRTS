package com.wkingtechrts.mygdxgame.automaton;

public enum AutoType {
	
	/*Attack, Defense, Range, Hitpoints, Speed*/
	
	WARRIOR(5,5,2,10,10),
	RANGER(8,3,10,8,20);
	
	private final float attack;
	private final float defense;
	private final float range;
	private final float hitpoint;
	private final float speed;
	
	AutoType(float a, float d, float r, float h, float s)
	{
		attack = a;
		defense = d;
		range = r;
		hitpoint = h;
		speed = s;
	}
	
	public float getAttack()
	{
		return attack;
	}
	
	public float getDefense()
	{
		return defense;
	}
	
	public float getRange()
	{
		return range;
	}
	
	public float getHitpoints()
	{
		return hitpoint;
	}
	
	public float getInterval()
	{
		return speed;
	}
}
