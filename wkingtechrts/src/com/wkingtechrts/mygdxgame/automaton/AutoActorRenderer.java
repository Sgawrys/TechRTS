package com.wkingtechrts.mygdxgame.automaton;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;

public class AutoActorRenderer {

	public static LinkedList<AutoActor> actorList;
	private OrthographicCamera camera;
	
	public AutoActorRenderer(OrthographicCamera cam)
	{
		actorList = new LinkedList<AutoActor>();
		camera = cam;
	}
	
	public void render(SpriteBatch batch)
	{
		int startX = (int)(camera.position.x-((camera.viewportWidth*camera.zoom)/(2.0f)));
		int startY = (int)(camera.position.y-((camera.viewportHeight*camera.zoom)/(2.0f)));
		int endX = startX + (int)((camera.viewportWidth*camera.zoom)+2.0f);
		int endY = startY + (int)((camera.viewportHeight*camera.zoom)+2.0f);
		
		if(startX < 0)
			startX = 0;
		if(startY < 0)
			startY = 0;
		
		if(endX > TerrainGenerator.MAPSIZEX)
			endX = TerrainGenerator.MAPSIZEX;
		if(endY > TerrainGenerator.MAPSIZEY)
			endY = TerrainGenerator.MAPSIZEY;
		
		if(!actorList.isEmpty())
		{

			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			for(AutoActor actor : actorList)
			{
				actor.moveToTile();
				if(actor.onScreen(startX,endX,startY,endY))
					actor.getSprite().draw(batch);
			}
			batch.end();
		}
	}
	
	public void addToRender(AutoActor actor)
	{
		actorList.add(actor);
	}
}
