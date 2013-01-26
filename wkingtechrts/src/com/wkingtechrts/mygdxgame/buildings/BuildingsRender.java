package com.wkingtechrts.mygdxgame.buildings;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;

public class BuildingsRender {

	public static LinkedList<Buildings> buildingList;
	private OrthographicCamera camera;
	private ShapeRenderer render;
	
	public BuildingsRender(OrthographicCamera cam)
	{
		buildingList = new LinkedList<Buildings>();
		camera = cam;
		render = new ShapeRenderer();
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
			
			for(Buildings build : buildingList)
			{
				if(build.isSelected())
				{
					render.setProjectionMatrix(camera.combined);
					render.begin(ShapeType.Rectangle);
					render.setColor(1.0f, 0.0f, 0.0f, 1.0f);
					render.rect(build.boundingBox.x,build.boundingBox.y, build.boundingBox.width, build.boundingBox.height);
					render.end();
				}
			}
		}
	}
	
	public void addToRender(Buildings building)
	{
		buildingList.add(building);
	}
}
