package com.wkingtechrts.mygdxgame.automaton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;
import com.wkingtechrts.mygdxgame.terrain.TerrainTile;

public class AStar {
	
	private TerrainTile[][] grid;
	private TerrainTile goalNode;
	private TerrainTile startNode;
	private PriorityQueue<TerrainTile> openList;
	private LinkedList<TerrainTile> closedList;	
	
	public  LinkedList<TerrainTile> findPath(Vector2 start, Vector2 goal, TerrainTile[][] map)
	{
		grid = map;
		
		openList = new PriorityQueue<TerrainTile>();
		closedList = new LinkedList<TerrainTile>();
		
		startNode = grid[(int) start.x][(int) start.y];
		goalNode = grid[(int)goal.x][(int)goal.y];
		
		startNode.g = 0;
		startNode.f = heuristic(startNode,goalNode);
		
		openList.add(startNode);
		
		while(!openList.isEmpty())
		{
			TerrainTile current = openList.poll();
			closedList.add(current);
			
			if(current.getPosition().x == goalNode.getPosition().x && current.getPosition().y == goalNode.getPosition().y)
			{
				System.out.println("Found path.");
				return rebuildPath(closedList);
			}
			
			LinkedList<TerrainTile> neighbors = getNeighbors(current);
			
			for(TerrainTile n : neighbors)
			{
				double est = current.g + distance(current,n);
				
				Iterator<TerrainTile> closeIt = closedList.iterator();
				boolean cont = false;
				while(closeIt.hasNext())
				{
					TerrainTile temp = closeIt.next();
					if(temp.getPosition().x == n.getPosition().x && temp.getPosition().y == n.getPosition().y)
					{
						cont = true;
					}
				}
				if(cont)
					continue;
				
				Iterator<TerrainTile> openIt = openList.iterator();
				boolean found = false;
				while(openIt.hasNext())
				{
					TerrainTile temp = openIt.next();
					if(temp.getPosition().x == n.getPosition().x && temp.getPosition().x == n.getPosition().y)
					{
						found = true;
						if(temp.g >= n.g)
						{
							n.parent = current;
							n.g = est;
							n.f = n.g + heuristic(n,goalNode);
						}
					}
				}
				if(!found)
				{
					n.parent = current;
					n.g = est;
					n.f = n.g + heuristic(n,goalNode);
					openList.add(n);
				}
			}
		}
		
		return null;
	}
	
	public double heuristic(TerrainTile cur, TerrainTile goal)
	{	
		int x = (int) cur.getPosition().x;
		int y = (int) cur.getPosition().y;
		int gx = (int) goal.getPosition().x;
		int gy = (int) goal.getPosition().y;
		
		double h = Math.max(Math.abs(x - gx), Math.abs(y-gy));
		return h;
	}
	public LinkedList<TerrainTile> getNeighbors(TerrainTile t)
	{
		LinkedList<TerrainTile> neighbors = new LinkedList<TerrainTile>();
		int x = (int) t.getPosition().x;
		int y = (int) t.getPosition().y;
		
		for(int i = -1; i < 2; i++)
		{
			for(int p = -1; p < 2; p++)
			{
				if(i == 0 && p == 0)
				{
					
				}else{
					neighbors.add(grid[x+i][y+p]);
				}
			}
		}
		return neighbors;
	}
	
	public double distance(TerrainTile dx, TerrainTile dy)
	{
		/*Diagonal check*/
		if(Math.abs(dx.getPosition().x - dy.getPosition().x) == 1 && Math.abs(dx.getPosition().y - dy.getPosition().y) == 1)
			return 14;
		return 10;
	}
	
	public LinkedList<TerrainTile> rebuildPath(LinkedList<TerrainTile> list)
	{
		LinkedList<TerrainTile> finalPath = new LinkedList<TerrainTile>();
		TerrainTile search = list.removeLast();
		while(search.parent != null)
		{
			finalPath.addFirst(search);
			search = search.parent;
		}
		return finalPath;
	}
}
