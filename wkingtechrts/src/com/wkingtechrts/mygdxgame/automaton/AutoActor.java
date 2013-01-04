package com.wkingtechrts.mygdxgame.automaton;

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
	private LinkedList<Node> path;
	private int currentPathIndex;
	
	/*Cost for moving orthogonal, and cost for moving diagonal*/
	private int d = 10;
	private int dx = 14;
	
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
			Node t = path.get(currentPathIndex);
			sprite.setX(t.x());
			sprite.setY(t.y());
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
		Node goal = new Node(x, y);
		Node start = new Node((int)sprite.getX(), (int)sprite.getY(), 0);
		double g = 0;
		double f = heuristicCost(start,goal);
		
		start.fcost = g+f;
		
		LinkedList<Node> open = new LinkedList<Node>();
		LinkedList<Node> closed = new LinkedList<Node>();
		open.add(start);
		
		
		System.out.println("Running A* Search algorithm");
		
		while(!open.isEmpty())
		{
			Node current = null;
			for(Node n : open)
			{
				if(current == null)
					current = n;
				
				if(n.fcost < current.fcost)
					current = n;
			}
			open.remove(current);
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
				}
			}
		}
	}
	
	public double heuristicCost(Node cur, Node goal)
	{
		int hd = (int) Math.min(Math.abs(cur.x() - goal.x()), Math.abs(cur.y() - goal.y()));
		int hs = (int) (Math.abs(cur.x() - goal.x()) + Math.abs(cur.y() - goal.y()));
		return dx * hd + d*(hs + (2*hd));
	}
	
	public int distance(Node dx, Node dy)
	{
		/*Diagonal check*/
		if(Math.abs(dx.x() - dy.x()) == 1 && Math.abs(dx.y() - dy.y()) == 1)
			return 14;
		return 10;
	}
	
	public LinkedList<Node> getNeighbor(Node center)
	{
		LinkedList<Node> nodes = new LinkedList<Node>();
		for(int i = -1; i < 2; i++)
		{
			for(int p = -1; p < 2; p++)
			{
				if(TerrainGenerator.tileMap[center.x()+i][center.y()+p].isWalkable() == 0)
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
		Node search = list.removeLast();
		while(search.parent != null)
		{
			finalPath.addFirst(search);
			search = search.parent;
		}
		return finalPath;
	}
}

