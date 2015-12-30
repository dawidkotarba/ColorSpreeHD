package com.dk.colorgame.model.tiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.enums.DirectionsEnum;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.Order;
import com.dk.colorgame.model.TouchCoords;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.utils.BoardUtils;
import com.dk.colorgame.utils.GraphicUtils;
import com.dk.colorgame.utils.InputUtils;
import com.dk.colorgame.utils.SoundUtils;
import com.dk.colorgame.utils.Utils;

import static com.dk.colorgame.constants.Constants.BOARD_Y_OFFSET;
import static com.dk.colorgame.constants.Constants.TILE_ACTIVATION_ROTATE;
import static com.dk.colorgame.constants.Constants.TILE_ACTIVE_SIZE;
import static com.dk.colorgame.constants.Constants.TILE_CREATION_SCALE;
import static com.dk.colorgame.constants.Constants.TILE_DYING_FADE_AWAY;
import static com.dk.colorgame.constants.Constants.TILE_DYING_ROTATION;
import static com.dk.colorgame.constants.Constants.TILE_DYING_SCALE;
import static com.dk.colorgame.constants.Constants.TILE_EXTRA_DYING_SCALE;
import static com.dk.colorgame.constants.Constants.TILE_FALL_DOWN_SPEED;
import static com.dk.colorgame.constants.Constants.TILE_INIT_SIZE;
import static com.dk.colorgame.constants.Constants.TILE_LIGHT_MULTIPLIER;
import static com.dk.colorgame.constants.Constants.TILE_LIGHT_OFFSET;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public abstract class AbstractTile implements Tile {

    protected final SpriteBatch spriteBatch;
    protected Vector2 position;
    protected Color color;
    protected Animation animation;

    private Sprite sprite;
    private final Sprite activeSprite;
    private Texture texture;
    private Order order; // col, row
    private TileTypesEnum tileType;
    private TileFeatureEnum tileFeature;
    private boolean active;
    private boolean dead;
    private float fadeAwayAlpha = 1;
    private final float stateTimeOffset;
    private Sound clickSound;
    private Sound dieSound;

    private AbstractTile(SpriteBatch spriteBatch, String activeTextureResource) {
        this.spriteBatch = spriteBatch;

        texture = (Texture) AssetLoader.get(activeTextureResource, Texture.class);
        sprite = new Sprite(texture);

        Texture activeTexture = (Texture) AssetLoader.get(activeTextureResource, Texture.class);
        activeSprite = new Sprite(activeTexture);
        tileType = TileTypesEnum.NOT_INIT;
        tileFeature = TileFeatureEnum.REGULAR;
        stateTimeOffset = Utils.getRandom().nextFloat();
    }

    protected AbstractTile(SpriteBatch spriteBatch, String textureResource, String activeTextureResource) {
        this(spriteBatch, activeTextureResource);
        texture = (Texture) AssetLoader.get(textureResource, Texture.class);
        sprite = new Sprite(texture);
        sprite.setScale(TILE_INIT_SIZE, TILE_INIT_SIZE);
    }

    protected AbstractTile(SpriteBatch spriteBatch, Animation animation, String activeTextureResource) {
        this(spriteBatch, activeTextureResource);
        this.animation = animation;
    }

    @Override
    public void draw() {

        if (animation == null) {
            sprite.setPosition(position.x, position.y);
        }

        activeSprite.setPosition(position.x, position.y);

        if (active) {
            animateActivation();
        } else {
            animateDeactivation();

            if (animation != null) {
                TextureRegion currentFrame = animation.getKeyFrame(stateTimeOffset + GraphicUtils.getStateTime(), true);
                spriteBatch.draw(currentFrame, position.x, position.y);
            } else {
                sprite.draw(spriteBatch);
            }
        }

        animateCreation();
        animateFallDawn();

        if (dead) {
            animateDying();
            GraphicUtils.renderLight(spriteBatch, position.x + TILE_LIGHT_OFFSET, position.y + TILE_LIGHT_OFFSET, TILE_LIGHT_MULTIPLIER * fadeAwayAlpha);
        }
    }

    private void animateDying() {
        if (fadeAwayAlpha > 0) {
            if (getTileFeature() == TileFeatureEnum.BONUS) {
                applyScale(true, TILE_EXTRA_DYING_SCALE);
            } else {
                applyScale(true, TILE_DYING_SCALE);
            }
            activeSprite.rotate(TILE_DYING_ROTATION);
            applyFadeAway(TILE_DYING_FADE_AWAY);
        }
    }

    private void animateCreation() {
        if (sprite.getScaleX() < 1 && sprite.getScaleY() < 1) {
            applyScale(true, TILE_CREATION_SCALE);
        }
    }

    private void animateActivation() {
        sprite.rotate(TILE_ACTIVATION_ROTATE);
        activeSprite.rotate(TILE_ACTIVATION_ROTATE);
        activeSprite.draw(spriteBatch);
    }

    private void animateDeactivation() {
        activeSprite.setRotation(0);

        float rotation = sprite.getRotation();

        if (rotation > 0) {

            if (rotation >= 360) {
                float shortRotation = rotation % 360;
                sprite.setRotation(shortRotation);
            }

            sprite.rotate(-TILE_ACTIVATION_ROTATE * 2);
        } else {
            sprite.setRotation(0);
        }
    }

    private void animateFallDawn() {
        float tileY = calculatePosition(order).y;

        if (position.y > tileY) {
            position.y -= TILE_FALL_DOWN_SPEED;
        } else {
            position.y = tileY;
        }
    }

    private void applyScale(boolean increase, float step) {
        float scaleX = sprite.getScaleX();
        float scaleY = sprite.getScaleY();

        if (increase) {
            sprite.setScale(scaleX + step, scaleY + step);
            activeSprite.setScale(scaleX + step, scaleY + step);
        } else {
            sprite.setScale(scaleX - step, scaleY - step);
            activeSprite.setScale(scaleX - step, scaleY - step);
        }
    }

    private void applyFadeAway(float step) {
        fadeAwayAlpha -= step;
        activeSprite.setColor(1, 1, 1, fadeAwayAlpha);
    }

    private Vector2 calculatePosition(Order order) {
        float tileX = BoardUtils.calculateBoardOffset(texture, Constants.BOARD_COLS) + (order.getColumn() - 1) * texture.getWidth();
        float tileY = BOARD_Y_OFFSET + (order.getRow() - 1) * texture.getHeight();

        return new Vector2(tileX, tileY);
    }

    private Vector2 calculateMenuPosition(Order order) {
        float tileX = BoardUtils.calculateBoardOffset(texture, Constants.BOARD_MENU_COLS) + (order.getColumn() - 1) * texture.getWidth();
        float tileY = BOARD_Y_OFFSET + (order.getRow() - 1) * texture.getHeight();

        return new Vector2(tileX, tileY);
    }


    @Override
    public void setClickSound(Sound clickSound) {
        this.clickSound = clickSound;
    }

    @Override
    public void setDieSound(Sound dieSound) {
        this.dieSound = dieSound;
    }

    @Override
    public void activate() {

        SoundUtils.playSound(clickSound);

        activeSprite.setScale(TILE_ACTIVE_SIZE, TILE_ACTIVE_SIZE);
        active = true;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public boolean isTouched(int x, int y) {
        TouchCoords touchCoords = InputUtils.translateTouchCoordinates(x, y);
        return getTextureBounds().contains(touchCoords.getX(), touchCoords.getY());
    }

    private Rectangle getTextureBounds() {
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public Order getOrder() {
        if (order == null) {
            throw new AssertionError("Order has not been initialized.");
        }

        return order;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
        position = calculatePosition(order);
    }

    @Override
    public void setMenuOrder(Order order) {
        this.order = order;
        position = calculateMenuPosition(order);
    }

    @Override
    public void updateOrder(DirectionsEnum direction) {

        switch (direction) {
            case UP:
                order.add(0, 1);
                break;
            case DOWN:
                order.sub(0, 1);
                break;
            case LEFT:
                order.sub(1, 0);
                break;
            case RIGHT:
                order.add(1, 0);
                break;
        }
    }

    @Override
    public int compareTo(Tile tile) {

        if (order.equals(tile.getOrder())) {
            return 0;
        }

        if (order.getColumn() < tile.getOrder().getColumn()) {
            return -1;
        }
        if (order.getColumn() > tile.getOrder().getColumn()) {
            return 1;
        }

        if (order.getColumn() < tile.getOrder().getColumn()) {
            return -1;
        }
        if (order.getColumn() > tile.getOrder().getColumn()) {
            return 1;
        }

        return 0;
    }


    @Override
    public void die() {
        SoundUtils.playSound(dieSound);
        dead = true;
    }

    @Override
    public TileTypesEnum getTileType() {
        return tileType;
    }

    protected void setTileType(TileTypesEnum tileType) {
        this.tileType = tileType;
    }

    @Override
    public TileFeatureEnum getTileFeature() {
        return tileFeature;
    }

    protected void setTileFeature(TileFeatureEnum tileFeature) {
        this.tileFeature = tileFeature;
    }

    @Override
    public boolean isActivated() {
        return active;
    }

    @Override
    public boolean shallBeRemoved() {
        return (dead && fadeAwayAlpha <= 0);
    }

    @Override
    public void setScale(float scale) {
        sprite.setScale(scale);
        activeSprite.setScale(scale);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getPointsMultiplier() {
        return 1;
    }

    @Override
    public String getTooltip() {
        return "";
    }
}
