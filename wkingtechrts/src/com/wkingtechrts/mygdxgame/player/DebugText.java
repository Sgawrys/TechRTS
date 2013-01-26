package com.wkingtechrts.mygdxgame.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class DebugText {

	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera cam;
	private Vector3 textPosition = new Vector3(50,50,0);
	private Matrix4 rotMatrix = new Matrix4();
	
	public DebugText(SpriteBatch sb)
	{
		batch = sb;
		font = new BitmapFont();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.project(textPosition);
		rotMatrix = new Matrix4();
		rotMatrix.setToRotation(textPosition, 180);
	}
	
	public void draw()
	{
		
		batch.setProjectionMatrix(cam.combined);
		batch.setTransformMatrix(rotMatrix);
		batch.begin();
		font.setColor(1.0f,0.0f,0.0f,0.7f);
		font.setScale(1, -1);
		font.draw(batch, "Testing String", textPosition.x, textPosition.y);
		batch.end();
	}
}
