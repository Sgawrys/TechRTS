package com.wkingtechrts.mygdxgame.terrain;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.math.MathUtils;

public class TerrainGenerator {
	
	public static final int MAPSIZEX = 256;
	public static final int MAPSIZEY = 256;
	
	public TerrainTile[][] tileMap = new TerrainTile[MAPSIZEX][MAPSIZEY];
	
	
	public TerrainGenerator()
	{
		
		float[][] perlinNoise = generatePerlinNoise(generateWhiteNoise(MAPSIZEX,MAPSIZEY),6);
		MathUtils colorRand = new MathUtils();
		
		for(int i = 0; i < perlinNoise.length; i++)
		{
			for(int j = 0; j < perlinNoise[0].length; j++)
			{
				float perlinVal = perlinNoise[i][j];
				Vector2 tilePos = new Vector2(i,j);
				
				Color tileCol = new Color();
				
				
				float rand = 0.0f;
				
				if(perlinVal <= .5f)
					tileCol = new Color(.278f, .431f+rand, .929f, 1);
				
				if(perlinVal > .5f && perlinVal <= .65f)
					tileCol = new Color(.262f, .650f+rand, .156f, 1);
				
				if(perlinVal > .65f && perlinVal <= .7f)
					tileCol = new Color(.125f+rand, .419f, .047f, 1);
				
				if(perlinVal > .7f && perlinVal <= .85f)
					tileCol = new Color(.93f, .768f, .278f+rand, 1);
				
				if(perlinVal > .85f)
					tileCol = new Color(.85f, .509f, .168f+rand, 1);
				
				tileMap[i][j] = new TerrainTile(tilePos, tileCol);
			}
		}
	}
	
	public TerrainTile[][] getTiles()
	{
		return tileMap;
	}
	
	
	public static float[][] generateWhiteNoise(int width, int height)
	{
		Random generator = new Random((int)(Math.random() * 50000));
		float[][] noise = new float[width][height];
		
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				noise[x][y] = generator.nextFloat() % 1;
			}
		}
		return noise;
	}
	
	public static float[][] generateSmoothNoise(float[][] baseNoise, int octave)
	{
		int width = baseNoise.length;
		int height = baseNoise[0].length;
		
		float[][] smoothNoise = new float[width][height];
		
		int samplePeriod = 1 << octave;
		float sampleFrequency = 1.0f / samplePeriod;
		
		for(int i = 0; i < width; i++)
		{
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % width;
			
			float horizontal_blend = (i - sample_i0) * sampleFrequency;
			
			for(int j = 0; j < height; j++)
			{
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height;
				float vertical_blend = (j - sample_j0) * sampleFrequency;
				
				
				float top = interpolate(baseNoise[sample_i0][sample_j0],baseNoise[sample_i1][sample_j0],horizontal_blend);
				float bottom = interpolate(baseNoise[sample_i0][sample_j1],baseNoise[sample_i1][sample_j1],horizontal_blend);
				
				smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
			}
		}
		return smoothNoise;
	}
	
	public static float[][] generatePerlinNoise(float[][] baseNoise, int octaveCount)
	{
		int width = baseNoise.length;
		int height = baseNoise[0].length;
		
		float[][][] smoothNoise = new float[octaveCount][][];
		
		float persistence = 0.5f;
		
		for(int i = 0; i < octaveCount; i++)
		{
			smoothNoise[i] = generateSmoothNoise(baseNoise,i);
		}
		
		float[][] perlinNoise = new float[width][height];
		for(int i = 0; i < perlinNoise.length; i++)
            perlinNoise[i] = new float[height];
		
		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;
				
		for(int octave = octaveCount - 1; octave >= 0; octave--)
		{
			amplitude *= persistence;
			totalAmplitude += amplitude;
			
			for(int i = 0; i < width; i++)
			{
				for(int j = 0; j < height; j++)
				{
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
				}
			}
		}
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				perlinNoise[i][j] /= totalAmplitude;
			}
		}
		
		return perlinNoise;
	}
	
	public static float interpolate(float x0, float x1, float alpha)
	{
		return (x0 * (1-alpha)) + (alpha * x1);
	}
	
	public int getMapsizeX()
	{
		return MAPSIZEX;
	}
	
	public int getMapsizeY()
	{
		return MAPSIZEY;
	}
	
}
