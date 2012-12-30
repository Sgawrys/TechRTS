package com.wkingtechrts.mygdxgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

public class TechGestureListener implements GestureListener {

	private OrthographicCamera cam;
	
	public TechGestureListener(OrthographicCamera camera)
	{
		this.cam = camera;
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
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
		cam.position.x += deltaX/(cam.viewportWidth * cam.zoom);
		cam.position.y -= deltaY/(cam.viewportHeight * cam.zoom);
		System.out.println("("+x+","+y+") DeltaX:"+deltaX+","+deltaY);
		cam.update();
		Gdx.graphics.requestRendering();
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
