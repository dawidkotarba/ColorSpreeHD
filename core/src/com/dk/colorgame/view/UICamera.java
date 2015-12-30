package com.dk.colorgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public class UICamera extends OrthographicCamera {

    public UICamera(SpriteBatch batch) {
        setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(this.combined);
        update();
    }

}
