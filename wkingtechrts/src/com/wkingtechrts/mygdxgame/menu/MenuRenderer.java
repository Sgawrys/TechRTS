package com.wkingtechrts.mygdxgame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class MenuRenderer{

	private ShapeRenderer menuRender;
	private OrthographicCamera camera;
	private int width;
	private int height;
	private boolean visible;
	public Rectangle button;
	
	public MenuRenderer()
	{
		menuRender = new ShapeRenderer();
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		button = new Rectangle(0,0,width/5,height/5);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void drawMenu()
	{
		if(visible)
		{
			
			menuRender.setProjectionMatrix(camera.combined);
			menuRender.begin(ShapeType.Rectangle);
			menuRender.setColor(new Color(1.0f,0.0f,0.0f,1.0f));
			menuRender.rect(0, 0, width, height/5);
			menuRender.end();
			menuRender.begin(ShapeType.FilledRectangle);
			menuRender.setColor(new Color(1.0f,0.0f,0.0f,1.0f));
			menuRender.filledRect(button.x,button.y,button.width,button.height);
			
			menuRender.end();
		}
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setVisible(boolean b)
	{
		visible = b;
	}
}
