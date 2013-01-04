package com.wkingtechrts.mygdxgame.automaton;

import com.badlogic.gdx.math.Vector2;

public class Node implements Comparable{
	public Vector2 current;
	public Node parent;
	public double cost;
	public double fcost;
	
	public Node(int x, int y, Node p, int g)
	{
		current = new Vector2(x,y);
		parent = p;
		cost = g;
	}
	
	public Node(int x, int y, int g)
	{
		/*No parent*/
		current = new Vector2(x,y);
		cost = g;
	}
	
	public Node(int x, int y)
	{
		current = new Vector2(x,y);
	}
	
	public int x()
	{
		return (int) current.x;
	}
	
	public int y()
	{
		return (int) current.y;
	}
	

	public int compareTo(Object node) {
		Node compare = (Node)node;
		if(this.fcost < compare.fcost)
			return -1;
		return 1;
	}
}
