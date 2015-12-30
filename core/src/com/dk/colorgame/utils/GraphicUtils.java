package com.dk.colorgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.resources.loaders.AssetLoader;

import static com.dk.colorgame.resources.Textures.IMG_LIGHT;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public class GraphicUtils {

    private static final GlyphLayout glyphLayout = new GlyphLayout();
    private static Camera camera;
    private static Viewport viewport;
    private static float stateTime;
    private static Color backgroundColor = new Color(0, 0, 0, 0);
    private static Color colorizedBackgroundColor = new Color(0, 0, 0, 0);
    private static Sprite lightSprite;

    private GraphicUtils() {
        // intentionally left blank
    }

    public static void clearScreen(float r, float g, float b) {
        Gdx.gl.glClearColor(r, g, b, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void clearScreen(Color color) {
        clearScreen(color.r, color.g, color.b);
    }

    public static void fadeBackgroundColor() {

        boolean fading = false;

        if (backgroundColor == null) {
            backgroundColor = new Color(0, 0, 0, 0);
        }

        clearScreen(backgroundColor);

        if (backgroundColor.r > 0) {
            backgroundColor.r += Constants.BACKGROUND_COLOR_INCREASE;
            fading = true;
        }


        if (backgroundColor.g > 0) {
            backgroundColor.g += Constants.BACKGROUND_COLOR_INCREASE;
            fading = true;
        }


        if (backgroundColor.b > 0) {
            backgroundColor.b += Constants.BACKGROUND_COLOR_INCREASE;
            fading = true;
        }

        if (!fading && !backgroundColor.equals(Color.CLEAR)) {
            backgroundColor = new Color(0, 0, 0, 0);
        }

    }

    public static void colorizeBackground() {

        if (backgroundColor == null) {
            backgroundColor = new Color(0, 0, 0, 0);
        }

        if (colorizedBackgroundColor == null) {
            colorizedBackgroundColor = new Color(0, 0, 0, 0);
        }

        clearScreen(backgroundColor);

        if (backgroundColor.r < colorizedBackgroundColor.r) {
            backgroundColor.r += Constants.BACKGROUND_COLORIZE_INCREASE;
        }

        if (backgroundColor.g < colorizedBackgroundColor.g) {
            backgroundColor.g += Constants.BACKGROUND_COLORIZE_INCREASE;
        }

        if (backgroundColor.b < colorizedBackgroundColor.b) {
            backgroundColor.b += Constants.BACKGROUND_COLORIZE_INCREASE;
        }

    }

    public static void setBackgroundColor(Color color) {
        backgroundColor = new Color(color);
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        GraphicUtils.camera = camera;
    }

    public static float getStateTime() {
        return stateTime;
    }

    public static void addStateTime(float stateTime) {
        GraphicUtils.stateTime += stateTime;
    }

    public static Animation loadAnimation(String resourcePath, int frameCols, int frameRows, int frameCount, float frameDuration) {

        Texture texture = (Texture) AssetLoader.get(resourcePath, Texture.class);
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / frameCols, texture.getHeight() / frameRows);
        TextureRegion[] frames = new TextureRegion[frameCount];
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                if (index == frameCount) {
                    break;
                }

                frames[index++] = tmp[i][j];
            }
        }
        return new Animation(frameDuration, frames);
    }

    public static Animation loadAnimation(String sourceAtlas, float animSpeed) {
        TextureAtlas textureAtlas = (TextureAtlas) AssetLoader.get(sourceAtlas, TextureAtlas.class);
        return new Animation(animSpeed, textureAtlas.getRegions());
    }

    public static float getTextHeight(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.height;
    }

    public static float getTextWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public static float calculateCenterFontOffset(BitmapFont font, String text) {
        float textWidth = getTextWidth(font, text);
        float screenWidth = viewport.getWorldWidth();
        return (screenWidth - textWidth) / 2;
    }

    public static Viewport getViewport() {
        return viewport;
    }

    public static void setViewport(Viewport viewport) {
        GraphicUtils.viewport = viewport;
    }

    public static float getWorldWidth() {
        return viewport.getWorldWidth();
    }

    public static float getWorldHeight() {
        return viewport.getWorldHeight();
    }

    public static void drawFPS(SpriteBatch spriteBatch, BitmapFont font) {
        String fpsLine = "FPS: " + Gdx.graphics.getFramesPerSecond();
        font.draw(spriteBatch, fpsLine, GraphicUtils.calculateCenterFontOffset(font, fpsLine), GraphicUtils.getWorldHeight() - 10);
    }

    public static void drawDevInfo(SpriteBatch spriteBatch, BitmapFont font) {
        font.draw(spriteBatch, "DEV", 0, GraphicUtils.getWorldHeight() - 10);
    }

    public static Color getColorFromRGB(int r, int g, int b) {
        return new Color((float) r / 255, (float) g / 255, (float) b / 255, 1);
    }

    public static void renderLight(SpriteBatch spriteBatch, float posX, float posY, float maxLightIntensity, boolean randomLightIntensity, float lightScale) {

        if (lightSprite == null) {
            lightSprite = new Sprite((Texture) AssetLoader.get(IMG_LIGHT, Texture.class));
        }

        spriteBatch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
        lightSprite.setPosition(posX, posY);
        lightSprite.setScale(lightScale);

        if (randomLightIntensity) {
            maxLightIntensity = Utils.getRandom().nextFloat() * maxLightIntensity;
        }

        lightSprite.setColor(maxLightIntensity, maxLightIntensity, maxLightIntensity, 1);
        lightSprite.draw(spriteBatch, 1);
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void renderLight(SpriteBatch spriteBatch, float posX, float posY, float maxLightIntensity) {
        renderLight(spriteBatch, posX, posY, maxLightIntensity, false, 1);
    }

    public static void dispose() {
        lightSprite = null;
        backgroundColor = null;
        colorizedBackgroundColor = null;
    }

    public static void setColorizedBackgroundColor(Color colorizedBackgroundColor) {
        GraphicUtils.colorizedBackgroundColor = colorizedBackgroundColor;
    }
}
