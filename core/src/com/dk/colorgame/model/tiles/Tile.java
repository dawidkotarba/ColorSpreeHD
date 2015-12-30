package com.dk.colorgame.model.tiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.dk.colorgame.enums.DirectionsEnum;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.Order;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public interface Tile extends Comparable<Tile> {

    void draw();

    void setClickSound(Sound clickSound);

    void setDieSound(Sound deadSound);

    void activate();

    void deactivate();

    boolean isTouched(int x, int y);

    Order getOrder();

    void setOrder(Order order);

    void setMenuOrder(Order order);

    void updateOrder(DirectionsEnum direction);

    TileTypesEnum getTileType();

    TileFeatureEnum getTileFeature();

    boolean isActivated();

    void die();

    boolean shallBeRemoved();

    void setScale(float scale);

    Color getColor();

    int getPointsMultiplier();

    String getTooltip();
}
