package com.wkingtechrts.mygdxgame.automaton;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AutoActor extends Actor {

	private Texture texture;
	private Sprite sprite;
	public AutoActor(float x, float y, Texture tex)
	{
		texture = tex;
		TextureRegion tr = new TextureRegion(texture,0,0,texture.getWidth(),texture.getHeight());
		sprite = new Sprite(tr);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setSize(2, 2);
		sprite.setRotation(90);
		sprite.setPosition(x, y);
		super.setX(x);
		super.setY(y);
	}
	
	public void setTexture(Texture newTex)
	{
		texture = newTex;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
}
