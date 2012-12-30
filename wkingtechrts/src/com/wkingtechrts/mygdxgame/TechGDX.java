package com.wkingtechrts.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.wkingtechrts.mygdxgame.terrain.TerrainGenerator;
import com.wkingtechrts.mygdxgame.terrain.TerrainRenderer;

public class TechGDX implements ApplicationListener {
	private OrthographicCamera camera;
	
	private TerrainGenerator gen;
	private TerrainRenderer renderer;
	
	private GestureDetector gd;
	
	@Override
	public void create() {
		
	
		
		Gdx.graphics.setContinuousRendering(false);
		
		gen = new TerrainGenerator();
		renderer = new TerrainRenderer(gen);
		Gdx.graphics.requestRendering();
		
		gd = new GestureDetector(new TechGestureListener(renderer.getCamera()));
		Gdx.input.setInputProcessor(gd);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
 		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
		if(gd.isLongPressed())
			renderer.zoomOut();
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
