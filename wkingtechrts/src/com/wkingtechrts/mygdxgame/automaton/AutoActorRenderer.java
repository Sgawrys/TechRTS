package com.wkingtechrts.mygdxgame.automaton;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AutoActorRenderer {

	private LinkedList<AutoActor> actorList;
	private OrthographicCamera camera;
	
	public AutoActorRenderer(OrthographicCamera cam)
	{
		actorList = new LinkedList<AutoActor>();
		camera = cam;
	}
	
	public void render(SpriteBatch batch)
	{
		if(!actorList.isEmpty())
		{

			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			for(AutoActor actor : actorList)
			{
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
