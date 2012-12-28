package com.wkingtechrts.mygdxgame.terrain;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class TerrainRenderer {
	private TerrainGenerator generator;
	private OrthographicCamera cam;
	
	/**for debug rendering according to tutorial**/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public TerrainRenderer(TerrainGenerator gen)
	{
		this.generator = gen;
		this.cam = new OrthographicCamera(10,7);
		this.cam.position.set(5,3.5f,0);
		this.cam.update();
	}
	
	public void render()
	{
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.FilledRectangle);
		for(TerrainTile[] tileArray : generator.getTiles())
		{
			for(TerrainTile tile : tileArray)
			{
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
		cam.zoom += 0.02f;
		cam.update();
	}
	public void zoomIn()
	{
		cam.zoom -= 0.05f;
		cam.update();
	}
}
