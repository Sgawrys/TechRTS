package com.wkingtechrts.mygdxgame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    public TechGDX x;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        x = new TechGDX();
        
        initialize(x, cfg);
    }
    
    public void onDestroy()
    {
    	x.dispose();
    	super.onDestroy();
    	System.exit(0);
    }
    
    public void onBackPressed()
    {
    	System.exit(0);
    	super.onBackPressed();
    }
}