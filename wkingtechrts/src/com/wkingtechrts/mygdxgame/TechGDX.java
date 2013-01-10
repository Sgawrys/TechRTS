package com.wkingtechrts.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.wkingtechrts.mygdxgame.automaton.AutoActor;
import com.wkingtechrts.mygdxgame.automaton.AutoActorRenderer;
import com.wkingtechrts.mygdxgame.buildings.BuildingsRender;
import com.wkingtechrts.mygdxgame.menu.MenuRenderer;
import com.wkingtechrts.mygdxgame.player.Player;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;
import com.wkingtechrts.mygdxgame.terrain.TerrainRenderer;

public class TechGDX implements ApplicationListener {
	
	private OrthographicCamera camera;
	private TerrainGenerator gen;
	private TerrainRenderer renderer;
	private GestureDetector gd;
	private SpriteBatch batch;
	private AutoActorRenderer actorRender;
	private BuildingsRender buildingRender;
	private Player player;
	private MenuRenderer menu;
	
	@Override
	public void create() {
		gen = new TerrainGenerator();
		renderer = new TerrainRenderer(gen);
		

		Texture t = new Texture(Gdx.files.internal("data/debug/actor.png"));

		AutoActor aa = new AutoActor(128.0f,128.0f,t);
		actorRender = new AutoActorRenderer(renderer.getCamera());
		actorRender.addToRender(aa);
		
		buildingRender = new BuildingsRender(renderer.getCamera());
		
		player = new Player(buildingRender, actorRender);

		menu = new MenuRenderer();
		gd = new GestureDetector(new TechGestureListener(renderer.getCamera(), player, menu));
		Gdx.input.setInputProcessor(gd);
		
		batch = new SpriteBatch();
		
	}

	@Override
	public void dispose() {
		TerrainGenerator.tileMap = null;
		for(AutoActor a : actorRender.actorList)
		{
			a.dispose();
		}
		batch.dispose();
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
 		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
		actorRender.render(batch);
		buildingRender.render(batch);
		menu.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
