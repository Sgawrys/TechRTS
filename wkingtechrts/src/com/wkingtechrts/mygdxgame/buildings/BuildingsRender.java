package com.wkingtechrts.mygdxgame.buildings;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;

public class BuildingsRender {

	public static LinkedList<Buildings> buildingList;
	private OrthographicCamera camera;
	
	public BuildingsRender(OrthographicCamera cam)
	{
		buildingList = new LinkedList<Buildings>();
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
		
		if(!buildingList.isEmpty())
		{

			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			for(Buildings build : buildingList)
			{
				if(build.onScreen(startX,endX,startY,endY))
					build.getSprite().draw(batch);
			}
			batch.end();
		}
	}
	
	public void addToRender(Buildings building)
	{
		buildingList.add(building);
	}
}
