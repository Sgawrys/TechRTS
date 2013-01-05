package com.wkingtechrts.mygdxgame.automaton;

import java.util.LinkedList;
import java.util.PriorityQueue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;
import com.wkingtechrts.mygdxgame.terrain.TerrainTile;

public class JumpSearch {

	private  TerrainTile[][] grid;
	private  TerrainTile goalNode;
	private  TerrainTile startNode;
	private  PriorityQueue<TerrainTile> openList;
	
	public  LinkedList<TerrainTile> findPath(Vector2 start, Vector2 goal, TerrainTile[][] map)
	{
		grid = map;
		
		openList = new PriorityQueue<TerrainTile>();
		
		startNode = grid[(int) start.x][(int) start.y];
		goalNode = grid[(int)goal.x][(int)goal.y];
		
		startNode.g = 0;
		startNode.f = 0;
		
		openList.add(startNode);
		startNode.opened = true;
		
		while(!openList.isEmpty())
		{
			TerrainTile node = openList.poll();
			node.closed = true;
			if(openList.size() > 2)
				System.out.println(node.f + " as opposed to " + openList.peek().f);
			if(node.getPosition().x == goalNode.getPosition().x && node.getPosition().y == goalNode.getPosition().y)
			{
				System.out.println("Found goal.");
				/*Create the path we are done*/
				return rebuildPath(node);
			}
			
			identifySuccessors(node);
		}
		/*Path could not be found*/
		System.out.println("Path could not be found");
		return null;
	}
	
	public LinkedList<TerrainTile> rebuildPath(TerrainTile goal)
	{
		LinkedList<TerrainTile> finalPath = new LinkedList<TerrainTile>();
		while(goal.parent != null)
		{
			finalPath.addFirst(goal);
			goal = goal.parent;
		}
		return finalPath;
	}
	
	public void identifySuccessors(TerrainTile t)
	{
		LinkedList<TerrainTile> neighbors = findNeighbors(t);
		int x = (int) t.getPosition().x;
		int y = (int) t.getPosition().y;
		
		System.out.println("Found this many neighbors."+neighbors.size());
		
		for(TerrainTile neighbor : neighbors)
		{
			TerrainTile jumpPoint = jump(neighbor, t);
			if(jumpPoint.getPosition().x != -1 && jumpPoint.getPosition().y != -1)
			{

				System.out.println("The jumppoint found has " + jumpPoint.getPosition().x + " , " + jumpPoint.getPosition().y );
				int jx = (int) jumpPoint.getPosition().x;
				int jy = (int) jumpPoint.getPosition().y;
				
				TerrainTile jumpNode = grid[jx][jy];
				jumpNode.setColor(new Color(0.878f,0.107f,0.698f,1.0f));
				
				if(jumpNode.closed)
				{
					continue;
				}
				
				double d = euclidean(Math.abs(jx-x), Math.abs(jy-y));
				double ng = t.g + d;
				
				if(!jumpNode.opened || ng < jumpNode.g)
				{
					jumpNode.g = ng;
					jumpNode.h = heuristic(jumpNode,goalNode);
					jumpNode.f = jumpNode.g + jumpNode.h;
					jumpNode.parent = t;
					
					if(!jumpNode.opened)
					{
						openList.add(jumpNode);
						jumpNode.opened = true;
					}
				}
			}
		}
	}
	
	public double euclidean(double dx, double dy)
	{
		return Math.sqrt(Math.pow(dx, 2)+Math.pow(dy,2));
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
	
	public TerrainTile jump(TerrainTile start, TerrainTile goal)
	{
		int x = (int) start.getPosition().x;
		int y = (int) start.getPosition().y;
		int px = (int) goal.getPosition().x;
		int py = (int) goal.getPosition().y;
		
		int dx = x-px / Math.max(Math.abs(x-px), 1);
		int dy = y-py / Math.max(Math.abs(y-py), 1);
		try{
			
			if(!grid[x][y].isWalkable())
			{
				System.out.println("Tile at ("+x+","+y+") is walkable?"+grid[x][y].isWalkable());
				TerrainGenerator.tileMap[x][y].setColor(new Color(0.0f,0.0f,0.0f,1.0f));
				System.out.println("Nulling your ass II Electric Boogaloo.");
				return new TerrainTile(new Vector2(-1,-1),false);
			}
			if(x == goalNode.getPosition().x && y == goalNode.getPosition().y)
			{
				return start;
			}
			
			
			if(dx != 0 && dy != 0)
			{
				if((grid[x-dx][y+dy].isWalkable() && !grid[x-dx][y].isWalkable()) || (grid[x+dx][y-dy].isWalkable() && !grid[x][y-dy].isWalkable()))
				{
					return start;
				}
			}else{
				if(dx != 0)
				{
					if((grid[x+dx][y+1].isWalkable() && !grid[x][y+1].isWalkable()) || (grid[x+dx][y-1].isWalkable() && !grid[x][y-1].isWalkable()))
					{
						return start;
					}
				}else{
					if((grid[x+1][y+dy].isWalkable() && !grid[x+1][y].isWalkable()) || (grid[x-1][y+dy].isWalkable() && !grid[x-1][y].isWalkable()))
					{
						return start;
					}
				}
			}
			
			
			if(dx != 0 && dy != 0)
			{
				TerrainTile jx = jump(grid[x+dx][y], start);
				TerrainTile jy = jump(grid[x][y+dy], start);
				if(jx.getPosition().x != -1 || jy.getPosition().y != -1)
				{
					return start;
				}
			}
			
			if(grid[x+dx][y].isWalkable() || grid[x][y+dy].isWalkable())
			{
				return jump(grid[x+dx][y+dy],start);
			}else{
				System.out.println("Nulling your ass.");
				return new TerrainTile(new Vector2(-1,-1),false);
			}
		}catch(ArrayIndexOutOfBoundsException aiofe)
		{
			return new TerrainTile(new Vector2(-1,-1),false);
		}
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
	
	
	public LinkedList<TerrainTile> findNeighbors(TerrainTile t)
	{
		TerrainTile parent = t.parent;
		LinkedList<TerrainTile> neighbors = new LinkedList<TerrainTile>();
		int x = (int) t.getPosition().x;
		int y = (int) t.getPosition().y;
		
		if(parent != null)
		{
			int px = (int)parent.getPosition().x;
			int py = (int)parent.getPosition().y;
			
			int dx = (x-px)/Math.max(Math.abs(x-px), 1);
			int dy = (y-py)/Math.max(Math.abs(y-py), 1);
			
			if(dx != 0 && dy != 0)
			{
				if(grid[x][y+dy].isWalkable())
				{
					neighbors.push(grid[x][y+dy]);
				}
				if(grid[x+dx][y].isWalkable())
				{
					neighbors.push(grid[x+dx][y]);
				}
				if(grid[x][y+dy].isWalkable() || grid[x+dx][y].isWalkable())
				{
					neighbors.push(grid[x+dx][y+dy]);
				}
				if(!grid[x-dx][y].isWalkable() && grid[x][y+dy].isWalkable())
				{
					neighbors.push(grid[x-dx][y+dy]);
				}
				if(!grid[x][y-dy].isWalkable() && grid[x+dx][y].isWalkable())
				{
					neighbors.push(grid[x+dx][y-dy]);
				}
			}else{
				if(dx == 0)
				{
					if(grid[x][y+dy].isWalkable())
					{
						if(grid[x][y+dy].isWalkable())
						{
							neighbors.push(grid[x][y+dy]);
						}
						if(!grid[x+1][y].isWalkable())
						{
							neighbors.push(grid[x+1][y+dy]);
						}
						if(!grid[x-1][y].isWalkable())
						{
							neighbors.push(grid[x-1][y+dy]);
						}
					}
				}else{
					if(grid[x+dx][y].isWalkable())
					{
						if(grid[x+dx][y].isWalkable())
						{
							neighbors.push(grid[x+dx][y]);
						}
						if(!grid[x][y+1].isWalkable())
						{
							neighbors.push(grid[x+dx][y+1]);
						}
						if(!grid[x][y-1].isWalkable())
						{
							neighbors.push(grid[x+dx][y-1]);
						}
					}
				}
				
			}
		}else{
			neighbors = getNeighbors(t);
		}
		return neighbors;
	}
	
}
