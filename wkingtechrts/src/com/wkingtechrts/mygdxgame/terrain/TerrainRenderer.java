package com.wkingtechrts.mygdxgame.terrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import java.util.LinkedList;

public class TerrainRenderer {
	private TerrainGenerator generator;
	private OrthographicCamera cam;

	private LinkedList<TerrainTile> tileChanged = new LinkedList<TerrainTile>();
	
	private TerrainTile[][] tileMap;
	/**for debug rendering according to tutorial**/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public TerrainRenderer(TerrainGenerator gen)
	{
		this.generator = gen;
		this.cam = new OrthographicCamera(10,7);
		this.cam.position.set(gen.getMapsizeX()/2.0f,gen.getMapsizeY()/2.0f,0);
		this.cam.zoom = 4.0f;
		this.cam.update();
		
		this.tileMap = generator.getTiles();
		/*
		for(TerrainTile[] tileArray : generator.getTiles())
		{
			for(TerrainTile tile : tileArray)
			{
				tileChanged.add(tile);
			}
		}*/
	}
	
	public void render()
	{
		/*
		if(tileChanged.isEmpty())
		{
			/*No changes were made that have to be re rendered
		}else{
			debugRenderer.setProjectionMatrix(cam.combined);
			debugRenderer.begin(ShapeType.FilledRectangle);
			for(TerrainTile tile : tileChanged)
			{
				Rectangle rect = tile.getBounds();
				float x1 = tile.getPosition().x + rect.x;
				float y1 = tile.getPosition().y + rect.y;
				debugRenderer.setColor(tile.getColor());
				debugRenderer.filledRect(x1,y1,rect.width,rect.height);
			}
			debugRenderer.end();
		}
		*/
		int startX = (int)(cam.position.x-((cam.viewportWidth*cam.zoom)/(2.0f)));
		int startY = (int)(cam.position.y-((cam.viewportHeight*cam.zoom)/(2.0f)));
		int endX = startX + (int)((cam.viewportWidth*cam.zoom)+1.0f);
		int endY = startY + (int)((cam.viewportHeight*cam.zoom)+1.0f);
		
		//System.out.println("("+startX+","+startY+")   --  ("+endX+","+endY+")");
		if(startX < 0)
			startX = 0;
		if(startY < 0)
			startY = 0;
		
		if(endX > tileMap.length)
			endX = tileMap.length;
		if(endY > tileMap[0].length)
			endY = tileMap[0].length;
		
		TerrainTile tile;
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.FilledRectangle);
		for(int x = startX; x < endX; x++)
		{
			for(int y = startY; y < endY; y++)
			{
				tile = tileMap[x][y];
				Rectangle rect = tile.getBounds();
				float x1 = tile.getPosition().x + rect.x;
				float y1 = tile.getPosition().y + rect.y;
				debugRenderer.setColor(tile.getColor());
				debugRenderer.filledRect(x1,y1,rect.width,rect.height);
			}
		}
		debugRenderer.end();
	}
	
	public void zoomOut()
	{
		if(cam.zoom < 4.0f)
		{
			cam.zoom += 1.0f;
			cam.update();
		}
	}
	public void zoomIn()
	{
		cam.zoom -= 1.0f;
		cam.update();
	}
	
	public OrthographicCamera getCamera()
	{
		return cam;
	}
}
