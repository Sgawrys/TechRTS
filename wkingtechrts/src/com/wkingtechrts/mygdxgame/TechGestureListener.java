package com.wkingtechrts.mygdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import com.wkingtechrts.mygdxgame.automaton.AutoActor;
import com.wkingtechrts.mygdxgame.automaton.AutoActorRenderer;
import com.wkingtechrts.mygdxgame.menu.MenuRenderer;
import com.wkingtechrts.mygdxgame.player.Player;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;

public class TechGestureListener implements GestureListener {

	private OrthographicCamera cam;
	private Player player;
	private MenuRenderer menu;
	
	public TechGestureListener(OrthographicCamera camera, Player user, MenuRenderer m)
	{
		this.cam = camera;
		this.player = user;
		this.menu = m;
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		
		
		Vector3 worldCoords = new Vector3(x,y,0);
		cam.unproject(worldCoords);
		
		int tx = (int)worldCoords.x;
		int ty = (int)worldCoords.y;
		
		/*int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		int startX = (int)(cam.position.x-((cam.viewportWidth*cam.zoom)/(2.0f)));
		int startY = (int)(cam.position.y-((cam.viewportHeight*cam.zoom)/(2.0f)));
		int endX = startX + (int)((cam.viewportWidth*cam.zoom)+1.0f);
		int endY = startY + (int)((cam.viewportHeight*cam.zoom)+1.0f);
		
		int oX = (int) (x/width * ((cam.viewportWidth*cam.zoom)+1.0f));
		int oY = (int) Math.abs((y-height)/height * (endY-startY));
		System.out.println("Corner : (" + startX + "," + startY + ") End : (" + endX + "," + endY + ") Offset: ("+oX+","+oY+")  Actual: ("+x+","+y+")");
		System.out.println("Height: "+height+" Width: "+width);*/
		
		/* Check if menus are visible */
			/*If visible, select option and close menu */
			/*Else check what is under the tap x,y coordinates for selecting units, placing buildings, etc...*/
		
		
		
		if(menu.isVisible())
			if(menu.button.contains(x,y))
				player.toggleBuilding();
		if(player.isBuilding())
			player.build(tx,ty);
		
		return false;
	}
	
	public boolean longPress(float x, float y) {
		if(menu.isVisible())
		{
			menu.setVisible(false);
		}else{
			menu.setVisible(true);
		}
		
		/*
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		int startX = (int)(cam.position.x-((cam.viewportWidth*cam.zoom)/(2.0f)));
		int startY = (int)(cam.position.y-((cam.viewportHeight*cam.zoom)/(2.0f)));
		int endX = startX + (int)((cam.viewportWidth*cam.zoom)+1.0f);
		int endY = startY + (int)((cam.viewportHeight*cam.zoom)+1.0f);
		
		int oX = (int) (x/width * ((cam.viewportWidth*cam.zoom)+1.0f));
		int oY = (int) Math.abs((y-height)/height * (endY-startY));
		
		int goalNodeX = startX+oX;
		int goalNodeY = startY+oY;
		
		System.out.println("Generating list");
		for(AutoActor aa : AutoActorRenderer.actorList)
		{
			aa.generatePath(goalNodeX, goalNodeY);
		}*/
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
