package com.wkingtechrts.mygdxgame.automaton;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;
import com.wkingtechrts.mygdxgame.terrain.TerrainTile;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AutoActor extends Actor {

	private Texture texture;
	private Sprite sprite;
	private Vector2 position;
	private LinkedList<TerrainTile> path;
	private int currentPathIndex;
	private boolean debug = true;
	/*Cost for moving orthogonal, and cost for moving diagonal*/
	private int costLine = 10;
	private int costDiag = 14;
	
	public AutoActor(float x, float y, Texture tex)
	{
		texture = tex;
		position = new Vector2(x,y);
		
		TextureRegion tr = new TextureRegion(texture,0,0,texture.getWidth(),texture.getHeight());
		sprite = new Sprite(tr);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setSize(2, 2);
		sprite.setRotation(0);
		sprite.setPosition(position.x, position.y);
		super.setX(x);
		super.setY(y);
	}
	
	public void dispose()
	{
		texture.dispose();
	}
	
	public void setTexture(Texture newTex)
	{
		texture = newTex;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public void moveToTile()
	{
		if(path!=null)
		{
			TerrainTile t = path.get(currentPathIndex);
			sprite.setX(t.getPosition().x);
			sprite.setY(t.getPosition().y);
			if(currentPathIndex < path.size()-1)
			{
				currentPathIndex++;
			}else{
				path = null;
				currentPathIndex = 0;
			}
		}
	}
	
	public void generatePath(int x, int y)
	{
		Vector2 goal = new Vector2(x,y);
		Vector2 start = new Vector2((int)sprite.getX(),(int)sprite.getY());
		System.out.println("Running Jump Search");
		JumpSearch js = new JumpSearch();
		path = js.findPath(start, goal, TerrainGenerator.tileMap);
		try{
			
			for(TerrainTile t : path)
			{
				t.setColor(new Color(1.0f,1.0f,0.0f,1.0f));
				System.out.println("Tile at position ("+t.getPosition().x+","+t.getPosition().y+")");
			}
		}catch(NullPointerException npe)
		{
			System.out.println("PATH NOT FOUND.");
		}
		for(TerrainTile[] t: TerrainGenerator.tileMap)
		{
			for(TerrainTile tile : t)
			{
				tile.closed = false;
				tile.opened = false;
				tile.f = 0.0;
				tile.g = 0.0;
				tile.h = 0.0;
				tile.parent = null;
			}
		}
		/*
		Node goal = new Node(x, y);
		Node start = new Node((int)sprite.getX(), (int)sprite.getY(), 0);
		double g = 0;
		double f = heuristicCost(start,goal);
		
		start.fcost = g+f;
		
		PriorityQueue<Node> open = new PriorityQueue<Node>();
		LinkedList<Node> closed = new LinkedList<Node>();
		open.add(start);
		
		
		System.out.println("Running A* Search algorithm");
		
		while(!open.isEmpty())
		{
			Node current = open.poll();
			
			closed.add(current);
			if(current.x() == goal.x() && current.y() == goal.y())
			{
				System.out.println("Found path.");
				for(Node temp : closed)
				{
					System.out.println("Path found: "+temp.x()+","+temp.y());
				}
				path = rebuildPath(closed);
				return;
			}
			
			LinkedList<Node> neighborNodes = getNeighbor(current);
			
			for(Node n : neighborNodes)
			{
				//Node jumpPoint = jump(n,current,goal);
				//if(jumpPoint != null)
				//{
					if(debug)
						TerrainGenerator.tileMap[n.x()][n.y()].setColor(new Color(.968f,.388f,.823f,1.0f));
	
					double est = current.cost + distance(current,n);
					
					Iterator<Node> closeIt = closed.iterator();
					boolean cont = false;
					while(closeIt.hasNext())
					{
						Node temp = closeIt.next();
						if(temp.x() == n.x() && temp.y() == n.y())
						{
							cont = true;
						}
					}
					if(cont)
						continue;
					
					
					Iterator<Node> openIt = open.iterator();
					boolean found = false;
					while(openIt.hasNext())
					{
						Node temp = openIt.next();
						if(temp.x() == n.x() && temp.y() == n.y())
						{
							found = true;
							if(temp.cost >= n.cost)
							{
								n.parent = current;
								n.cost = est;
								n.fcost = n.cost + heuristicCost(n,goal);
							}
						}
					}
					if(!found)
					{
						n.parent = current;
						n.cost = est;
						n.fcost = n.cost + heuristicCost(n,goal);
						open.add(n);
				//	}
				}
			}
			if(debug)
				TerrainGenerator.tileMap[current.x()][current.y()].setColor(new Color(.807f,.2f,.839f,1.0f));
		}*/
	}
	
	public double heuristicCost(Node cur, Node goal)
	{	
		
		int hd = (int) Math.min(Math.abs(cur.x() - goal.x()), Math.abs(cur.y() - goal.y()));
		int hs = (int) (Math.abs(cur.x() - goal.x()) + Math.abs(cur.y() - goal.y()));
		double h = costDiag * hd + costLine*(hs + (2*hd));
		//double h = costDiag * Math.max(Math.abs(cur.x() - goal.x()), Math.abs(cur.y()-goal.y()));
		return h*(1.001);
	}
	
	public double distance(Node dx, Node dy)
	{
		/*Diagonal check*/
		if(Math.abs(dx.x() - dy.x()) == 1 && Math.abs(dx.y() - dy.y()) == 1)
			return costDiag;
		return costLine;
	}
	
	public LinkedList<Node> getNeighbor(Node center)
	{
		LinkedList<Node> nodes = new LinkedList<Node>();
		for(int i = -1; i < 2; i++)
		{
			for(int p = -1; p < 2; p++)
			{
				if(!TerrainGenerator.tileMap[center.x()+i][center.y()+p].isWalkable())
					continue;
				
				if(i == 0 && p == 0)
				{
				}else{
					Node n = new Node(center.x()+i, center.y()+p);
					n.cost = center.cost+distance(center,n);
					n.parent = center;
					nodes.add(n);
				}
			}
		}
		return nodes;
	}
	
	public LinkedList<Node> rebuildPath(LinkedList<Node> list)
	{
		LinkedList<Node> finalPath = new LinkedList<Node>();
		if(debug)
		{
			for(Node n : list)
			{
				TerrainGenerator.tileMap[n.x()][n.y()].setColor(new Color(.701f,.168f,.701f,1.0f));
			}
		}
		Node search = list.removeLast();
		while(search.parent != null)
		{
			if(debug)
				TerrainGenerator.tileMap[search.x()][search.y()].setColor(new Color(1.0f,1.0f,0.0f,1.0f));
			finalPath.addFirst(search);
			search = search.parent;
		}
		return finalPath;
	}
}

