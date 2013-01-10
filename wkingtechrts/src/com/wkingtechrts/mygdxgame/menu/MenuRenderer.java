package com.wkingtechrts.mygdxgame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.wkingtechrts.mygdxgame.menu.MenuType;

public class MenuRenderer{

	private ShapeRenderer menuRender;
	private OrthographicCamera camera;
	private int width;
	private int height;
	private boolean visible;
	public Rectangle activateBuilding;
	public Rectangle buildFarms;
	public Rectangle buildCastles;
	public Rectangle buildArcher;
	public Rectangle returnButton;
	private MenuType currentMenu = MenuType.OPTIONS;
	
	public MenuRenderer()
	{
		menuRender = new ShapeRenderer();
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		activateBuilding = new Rectangle(0,0,width/5,height/5);
		buildFarms = new Rectangle(width/5,0,width/5,height/5);
		buildCastles = new Rectangle(2 * width/5,0,width/5,height/5);
		buildArcher = new Rectangle(3 * width/5,0,width/5,height/5);
		returnButton = new Rectangle(4 * width/5,0,width/5,height/5);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void draw()
	{
		switch(currentMenu)
		{
			case OPTIONS:drawMenu();break;
			case BUILDINGS:drawBuildingMenu();break;
			case UNITCONTROL:break;
			case BUILDINGCONTROL:break;
			default:break;
		}
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
			menuRender.filledRect(activateBuilding.x,activateBuilding.y,activateBuilding.width,activateBuilding.height);
			menuRender.end();
		}
	}
	
	public void drawBuildingMenu()
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
			menuRender.filledRect(buildArcher.x,buildArcher.y,buildArcher.width,buildArcher.height);
			menuRender.setColor(new Color(0.0f,1.0f,0.0f,1.0f));
			menuRender.filledRect(buildFarms.x,buildFarms.y,buildFarms.width,buildFarms.height);
			menuRender.setColor(new Color(0.0f,0.0f,1.0f,1.0f));
			menuRender.filledRect(buildCastles.x,buildCastles.y,buildCastles.width,buildCastles.height);
			menuRender.setColor(new Color(1.0f,1.0f,1.0f,1.0f));
			menuRender.filledRect(returnButton.x,returnButton.y,returnButton.width,returnButton.height);
			menuRender.end();
		}
	}
	
	public void setMenuType(MenuType mt)
	{
		this.currentMenu = mt;
	}
	
	public MenuType currentMenu()
	{
		return currentMenu;
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
