package com.dk.colorgame.service.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dawid Kotarba on 2015-06-21.
 */
public abstract class AbstractBoard implements Board {

    protected final SpriteBatch spriteBatch;
    protected final BitmapFont font;

    protected AbstractBoard(SpriteBatch spriteBatch, String fontName) {
        this.spriteBatch = spriteBatch;
        font = new BitmapFont(Gdx.files.internal(fontName));
    }
}
