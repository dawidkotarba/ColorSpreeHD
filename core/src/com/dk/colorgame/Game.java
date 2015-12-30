package com.dk.colorgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.enums.EnvironmentsEnum;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.resources.Fonts;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.service.board.impl.MainBoardImpl;
import com.dk.colorgame.service.effects.particles.ParticleSpawner;
import com.dk.colorgame.utils.ActionResolver;
import com.dk.colorgame.utils.GraphicUtils;
import com.dk.colorgame.utils.InputUtils;
import com.dk.colorgame.utils.Utils;
import com.dk.colorgame.view.UICamera;
import com.dk.colorgame.view.menu.Menu;
import com.dk.colorgame.view.menu.impl.GameOverMenu;
import com.dk.colorgame.view.menu.impl.MainMenu;
import com.dk.colorgame.view.menu.impl.PausedMenu;
import com.dk.colorgame.view.menu.impl.options.AudioMenu;
import com.dk.colorgame.view.menu.impl.options.GameMenu;
import com.dk.colorgame.view.menu.impl.options.OptionsMenu;
import com.dk.colorgame.view.menu.impl.options.VideoMenu;
import com.dk.colorgame.view.menu.impl.records.CreditsMenu;
import com.dk.colorgame.view.menu.impl.records.RecordsMenu;
import com.dk.colorgame.view.menu.impl.records.RecordsResetMenu;
import com.dk.colorgame.view.menu.impl.records.ScoresMenu;

public class Game extends ApplicationAdapter {
    private static GameStateEnum gameState = GameStateEnum.MENU_MAIN;
    private GameStateEnum oldState = GameStateEnum.MENU_MAIN;
    private static ActionResolver actionResolver;
    private static boolean worldCreated;
    private SpriteBatch spriteBatch;
    private MainBoard mainBoard;
    private Viewport viewport;
    private Camera camera;
    private BitmapFont font;
    private static ParticleSpawner backgroundParticleSpawner;
    private static ParticleSpawner foregroundParticleSpawner;

    // menus
    private Menu mainMenu;
    private Menu gameOverMenu;
    private Menu scoresMenu;
    private Menu recordsMenu;
    private Menu pausedMenu;
    private Menu optionsMenu;
    private Menu optionsAudioMenu;
    private Menu optionsVideoMenu;
    private Menu creditsMenu;
    private Menu recordsResetMenu;
    private Menu gameMenu;

    public Game(ActionResolver actionResolver) {
        Game.actionResolver = actionResolver;
    }

    public static GameStateEnum getGameState() {
        return gameState;
    }

    public static void setGameState(GameStateEnum gameState) {
        Game.gameState = gameState;
    }

    @Override
    public void create() {
        setUpLogLevel();

        spriteBatch = new SpriteBatch();
        camera = new UICamera(spriteBatch);
        GraphicUtils.setCamera(camera);
        viewport = new ExtendViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();
        GraphicUtils.setViewport(viewport);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        Gdx.app.debug("Screen width:", Integer.toString(screenWidth));
        Gdx.app.debug("Screen width:", Integer.toString(screenHeight));

        font = (BitmapFont) AssetLoader.get(Fonts.HOMESPUN, BitmapFont.class);

        backgroundParticleSpawner = new ParticleSpawner();
        foregroundParticleSpawner = new ParticleSpawner();

        createWorld();
    }

    private void setUpLogLevel() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV) {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        } else if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD) {
            Gdx.app.setLogLevel(Application.LOG_NONE);
        }
    }

    @Override
    public void render() {
        GraphicUtils.addStateTime(Gdx.graphics.getDeltaTime());
        renderWorld();
    }

    void createWorld() {
        Options.init();
        mainBoard = new MainBoardImpl(spriteBatch, camera);
        mainBoard.create();
        mainMenu = new MainMenu(spriteBatch, viewport, mainBoard);
        gameOverMenu = new GameOverMenu(spriteBatch, viewport, mainBoard);
        scoresMenu = new ScoresMenu(spriteBatch, viewport, mainBoard);
        recordsMenu = new RecordsMenu(spriteBatch, viewport, mainBoard);
        pausedMenu = new PausedMenu(spriteBatch, viewport, mainBoard);
        optionsMenu = new OptionsMenu(spriteBatch, viewport, mainBoard);
        creditsMenu = new CreditsMenu(spriteBatch, viewport, mainBoard);
        recordsResetMenu = new RecordsResetMenu(spriteBatch, viewport, mainBoard);
        optionsAudioMenu = new AudioMenu(spriteBatch, viewport, mainBoard);
        optionsVideoMenu = new VideoMenu(spriteBatch, viewport, mainBoard);
        gameMenu = new GameMenu(spriteBatch, viewport, mainBoard);

        worldCreated = true;
    }

    void renderWorld() {
        camera.update();
        this.spriteBatch.setProjectionMatrix(camera.combined);
        this.spriteBatch.begin();

        loadAndShowProgress();
        updateBackgroundColor();

        float deltaTime = Gdx.graphics.getDeltaTime();

        if (gameState != GameStateEnum.STARTED) {
            ((UICamera) camera).zoom = 1;
        }

        backgroundParticleSpawner.draw(this.spriteBatch);

        switch (gameState) {
            case MENU_MAIN:
                mainMenu.render(deltaTime);
                break;
            case MENU_GAME_OVER:
                gameOverMenu.render(deltaTime);
                break;
            case MENU_SCORES:
                scoresMenu.render(deltaTime);
                break;
            case MENU_RECORDS:
                recordsMenu.render(deltaTime);
                break;
            case MENU_SCORE_RESET:
                recordsResetMenu.render(deltaTime);
                break;
            case MENU_OPTIONS:
                optionsMenu.render(deltaTime);
                break;
            case MENU_OPTIONS_AUDIO:
                optionsAudioMenu.render(deltaTime);
                break;
            case MENU_OPTIONS_GAME:
                gameMenu.render(deltaTime);
                break;
            case MENU_OPTIONS_VIDEO:
                optionsVideoMenu.render(deltaTime);
                break;
            case MENU_CREDITS:
                creditsMenu.render(deltaTime);
                break;
            case STARTED:
                mainBoard.draw();
                break;
            case PAUSED:
                pausedMenu.render(deltaTime);
                break;
        }

        foregroundParticleSpawner.draw(this.spriteBatch);
        this.spriteBatch.end();
    }

    private void updateBackgroundColor() {

        if (gameState == GameStateEnum.STARTED) {
            GraphicUtils.fadeBackgroundColor();
            return;
        }

        if (oldState != gameState) {

            if (!GraphicUtils.getBackgroundColor().equals(Color.CLEAR)) {
                GraphicUtils.fadeBackgroundColor();
                return;
            } else {
                oldState = gameState;
            }
        }
        GraphicUtils.colorizeBackground();
    }

    private void loadAndShowProgress() {
        AssetLoader.processLazyLoad();
        Utils.drawLoadingProgress(spriteBatch, font);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        backgroundParticleSpawner.dispose();
        foregroundParticleSpawner.dispose();
        InputUtils.dispose();
        GraphicUtils.dispose();
        gameState = GameStateEnum.MENU_MAIN;
        worldCreated = false;

        mainMenu.dispose();
        gameOverMenu.dispose();
        recordsMenu.dispose();
        scoresMenu.dispose();
        pausedMenu.dispose();
        optionsMenu.dispose();
        optionsAudioMenu.dispose();
        optionsVideoMenu.dispose();
        creditsMenu.dispose();
        recordsResetMenu.dispose();
        gameMenu.dispose();
    }

    public static boolean isWorldCreated() {
        return worldCreated;
    }

    public static ActionResolver getActionResolver() {
        return actionResolver;
    }

    public static ParticleSpawner getBackgroundParticleSpawner() {
        return backgroundParticleSpawner;
    }

    public static ParticleSpawner getForegroundParticleSpawner() {
        return foregroundParticleSpawner;
    }
}
