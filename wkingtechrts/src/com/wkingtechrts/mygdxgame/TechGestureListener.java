package com.wkingtechrts.mygdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.wkingtechrts.mygdxgame.player.Player;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;

public class TechGestureListener implements GestureListener {

	private OrthographicCamera cam;
	private Player player;
	
	public TechGestureListener(OrthographicCamera camera, Player user)
	{
		this.cam = camera;
		this.player = user;
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		int startX = (int)(cam.position.x-((cam.viewportWidth*cam.zoom)/(2.0f)));
		int startY = (int)(cam.position.y-((cam.viewportHeight*cam.zoom)/(2.0f)));
		int endX = startX + (int)((cam.viewportWidth*cam.zoom)+1.0f);
		int endY = startY + (int)((cam.viewportHeight*cam.zoom)+1.0f);
		
		int oX = (int) (x/width * ((cam.viewportWidth*cam.zoom)+1.0f));
		int oY = (int) Math.abs((y-height)/height * (endY-startY));
		System.out.println("Corner : (" + startX + "," + startY + ") End : (" + endX + "," + endY + ") Offset: ("+oX+","+oY+")  Actual: ("+x+","+y+")");
		System.out.println("Height: "+height+" Width: "+width);
		
		if(player.getMoney() >= 20)
		{
			if(TerrainGenerator.tileMap != null)
				TerrainGenerator.tileMap[startX+oX][startY+oY].setColor(new Color(1,0,0,1));
			player.setMoney(player.getMoney()-20);
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		cam.position.x -= deltaX/(cam.viewportWidth);
		cam.position.y += deltaY/(cam.viewportHeight);
		System.out.println("("+x+","+y+") DeltaX:"+deltaX+","+deltaY);
		cam.update();
		Gdx.graphics.requestRendering();
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		float scalar = ((initialDistance-distance)/initialDistance);
		System.out.println(scalar);
		cam.zoom -= scalar;
		if(cam.zoom <= 1.0f)
			cam.zoom = 1.0f;
		if(cam.zoom >= 16.0f)
			cam.zoom = 16.0f;
		cam.update();
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
