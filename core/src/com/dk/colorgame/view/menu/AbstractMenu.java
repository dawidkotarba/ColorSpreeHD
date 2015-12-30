package com.dk.colorgame.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.Strings;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.model.Order;
import com.dk.colorgame.model.tiles.Tile;
import com.dk.colorgame.resources.Fonts;
import com.dk.colorgame.resources.Sounds;
import com.dk.colorgame.resources.Textures;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.utils.BoardUtils;
import com.dk.colorgame.utils.GraphicUtils;
import com.dk.colorgame.utils.InputUtils;
import com.dk.colorgame.utils.SoundUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.dk.colorgame.constants.Colors.BLUE;
import static com.dk.colorgame.constants.Colors.CYAN;
import static com.dk.colorgame.constants.Colors.GRAY;
import static com.dk.colorgame.constants.Colors.GREEN;
import static com.dk.colorgame.constants.Colors.RED;
import static com.dk.colorgame.constants.Colors.VIOLET;
import static com.dk.colorgame.constants.Colors.YELLOW;
import static com.dk.colorgame.constants.Constants.BOARD_ROWS;
import static com.dk.colorgame.constants.Constants.LABEL_TITLE_Y;
import static com.dk.colorgame.constants.Strings.LABEL_EXIT;
import static com.dk.colorgame.constants.Strings.LABEL_MAIN_MENU;
import static com.dk.colorgame.constants.Strings.LABEL_PLAY;
import static com.dk.colorgame.resources.Fonts.HOMESPUN_BIG;

/**
 * Created by Dawid Kotarba on 2015-06-05.
 */
public abstract class AbstractMenu implements Menu {

    protected static final List<Menu> allMenus = new ArrayList<>();
    protected final SpriteBatch spriteBatch;
    protected final Skin skin;
    protected final Stage stage;
    protected final String btnSkin = "button";
    protected final String btnDownSkin = "buttonDown";
    protected final String squareBtnSkin = "buttonSquare";
    protected final String squareBtnDownSkin = "buttonDownSquare";
    protected final Texture buttonTexture;
    protected final Texture buttonDownTexture;
    protected final Texture buttonSquareTexture;
    protected final Texture buttonSquareDownTexture;
    protected final List<Actor> actors = new ArrayList<>();
    protected final MainBoard mainBoard;
    protected final BitmapFont font;
    protected final BitmapFont bigFont;
    protected List<Tile> tiles;
    protected int shuffleTimer;
    protected Color mainColor = Color.BLACK;
    protected Sound clickSound;
    protected int lastBtnColor;

    public static final List<Color> COLOR_LIST = new ArrayList<Color>() {
        {
            add(VIOLET);
            add(BLUE);
            add(GREEN);
            add(YELLOW);
            add(RED);
            add(CYAN);
            add(GRAY);
        }
    };

    protected AbstractMenu(SpriteBatch spriteBatch, Viewport viewport, final MainBoard mainBoard) {
        this.spriteBatch = spriteBatch;
        this.mainBoard = mainBoard;
        stage = new Stage(viewport);
        InputUtils.getInputMultiplexer().addProcessor(stage);
        skin = new Skin();

        buttonTexture = (Texture) AssetLoader.get(Textures.IMG_BUTTON, Texture.class);
        buttonDownTexture = (Texture) AssetLoader.get(Textures.IMG_BUTTON_DOWN, Texture.class);
        skin.add(btnSkin, buttonTexture);
        skin.add(btnDownSkin, buttonDownTexture);

        buttonSquareTexture = (Texture) AssetLoader.get(Textures.IMG_BUTTON_SQUARE, Texture.class);
        buttonSquareDownTexture = (Texture) AssetLoader.get(Textures.IMG_BUTTON_SQUARE_DOWN, Texture.class);
        skin.add(squareBtnSkin, buttonSquareTexture);
        skin.add(squareBtnDownSkin, buttonSquareDownTexture);

        tiles = createMenuTiles();

        font = (BitmapFont) AssetLoader.get(Fonts.HOMESPUN, BitmapFont.class);
        bigFont = new BitmapFont(Gdx.files.internal(HOMESPUN_BIG));
        clickSound = (Sound) AssetLoader.get(Sounds.SOUND_CLICK, Sound.class);
        Collections.shuffle(COLOR_LIST);

        allMenus.add(this);
    }

    protected Actor createBackgroundParticleActor(String particle) {

        ParticleEffect backgroundParticle = (ParticleEffect) AssetLoader.get(particle, ParticleEffect.class);
        backgroundParticle.reset();
        Actor particleActor = new ParticleEffectActor(backgroundParticle);
        particleActor.setPosition(GraphicUtils.getWorldWidth() / 2, 400);
        return particleActor;
    }

    private MoveToAction getButtonMoveAction(int posX, int posY) {
        MoveToAction buttonMove = new MoveToAction();
        buttonMove.setPosition(posX, posY);
        buttonMove.setDuration(0.4f);

        return buttonMove;
    }

    protected void drawHeaderText(String text) {
        drawHeaderText(text, LABEL_TITLE_Y);
    }

    protected void drawHeaderText(String text, int posY) {
        bigFont.draw(spriteBatch, text, GraphicUtils.calculateCenterFontOffset(bigFont, text), posY);
    }

    protected void drawText(String text, int posY) {
        font.draw(spriteBatch, text, GraphicUtils.calculateCenterFontOffset(font, text), posY);
    }

    protected void clearOtherStages() {
        for (Menu menu : allMenus) {
            if (menu != this) {
                menu.clearStage();
            }
        }
    }

    protected void createPlayBtn(int posY) {
        TextButton playBtn = createRectangleButton(LABEL_PLAY, posY, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainBoard.reset();
                Game.setGameState(GameStateEnum.STARTED);
            }
        });

        addActor(playBtn);
    }

    protected void createExitBtn(int posY) {
        TextButton exitBtn = createRectangleButton(LABEL_EXIT, posY, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        addActor(exitBtn);
    }

    protected void createMainMenuBtn(int posY) {
        TextButton mainMenuBtn = createRectangleButton(LABEL_MAIN_MENU, posY, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameStateEnum.MENU_MAIN);
            }
        });

        addActor(mainMenuBtn);
    }

    protected void addActor(Actor actor) {
        actors.add(actor);
    }

    @Override
    public void clearStage() {
        stage.clear();
    }

    public void fillStage() {

        if (stage.getActors().size == 0) {
            for (Actor actor : actors) {
                stage.addActor(actor);
            }
        }
    }

    protected int getButtonCenterPos() {
        return (int) GraphicUtils.getWorldWidth() / 2 - buttonTexture.getWidth() / 2;
    }

    protected TextButton createRectangleButton(String text, int posY, ChangeListener changeListener) {
        return createButton(btnSkin, btnDownSkin, text, getButtonCenterPos(), posY, changeListener);
    }

    protected TextButton createSquareButton(String text, int posX, int posY, ChangeListener changeListener) {
        return createButton(squareBtnSkin, squareBtnDownSkin, text, posX, posY, changeListener);
    }


    protected TextButton createButton(String buttonUpSkin, String buttonDownSkin, String text, int posX, int posY, ChangeListener changeListener) {

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        if (lastBtnColor++ == COLOR_LIST.size() - 1) {
            lastBtnColor = 0;
        }

        Color btnColor = COLOR_LIST.get(lastBtnColor);

        if (lastBtnColor++ == COLOR_LIST.size() - 1) {
            lastBtnColor = 0;
        }

        Color btnDownColor = COLOR_LIST.get(lastBtnColor);

        textButtonStyle.up = skin.newDrawable(buttonUpSkin, btnColor);
        textButtonStyle.down = skin.newDrawable(buttonDownSkin, btnDownColor);
        textButtonStyle.font = (BitmapFont) AssetLoader.get(Fonts.HOMESPUN, BitmapFont.class);

        TextButton textButton = new TextButton(text, textButtonStyle);
        textButton.setPosition(posX, 0);
        textButton.addAction(getButtonMoveAction(posX, posY));
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                SoundUtils.playSound(clickSound);
            }
        });
        textButton.addListener(changeListener);

        return textButton;
    }

    @Override
    public void render(float delta) {
        // end batch to draw above tiles correctly
        spriteBatch.end();
        spriteBatch.begin();
        GraphicUtils.setColorizedBackgroundColor(mainColor);
        fillStage();
        stage.act(delta);
        stage.draw();
        clearOtherStages();
    }

    protected void shuffleHeaderTiles(int minDelta, int maxDelta) {
        shuffleTimer++;

        if (shuffleTimer > minDelta && shuffleTimer < maxDelta) {
            tiles = createHeaderTiles();

            for (Tile tile : tiles) {
                tile.setScale(1);
            }
        }

        if (shuffleTimer > maxDelta) {
            shuffleTimer = 0;
        }
    }

    protected void shuffleMenuTiles(int minDelta, int maxDelta) {
        shuffleTimer++;

        if (shuffleTimer > minDelta && shuffleTimer < maxDelta) {
            tiles = createMenuTiles();

            for (Tile tile : tiles) {
                tile.setScale(1);
            }
        }

        if (shuffleTimer > maxDelta) {
            shuffleTimer = 0;
        }
    }

    protected List<Tile> createHeaderTiles() {
        return createTiles(7);
    }

    protected List<Tile> createMenuTiles() {
        return createTiles(9);
    }

    private List<Tile> createTiles(int minRow) {
        List<Tile> tiles = new ArrayList<>();

        for (int col = 1; col <= Constants.BOARD_MENU_COLS; col++) {
            for (int row = BOARD_ROWS; row >= minRow; row--) {

                Tile tile = BoardUtils.randomMenuTile(spriteBatch);
                Order tileOrder = new Order(col, row);
                tile.setMenuOrder(tileOrder);
                tiles.add(tile);
            }
        }
        return tiles;
    }

    protected void drawHeaderTiles() {
        shuffleHeaderTiles(150, 170);
        drawTiles();
    }

    protected void drawMenuTiles() {
        shuffleMenuTiles(150, 170);
        drawTiles();
    }

    private void drawTiles() {
        for (Tile tile : tiles) {
            tile.draw();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    protected void createOnOffBtn(boolean isOn, int posY, ChangeListener changeListener) {
        final TextButton btn = createRectangleButton("", posY, changeListener);

        String text = isOn ? Strings.LABEL_OFF : Strings.LABEL_ON;
        btn.setText(text);

        btn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (btn.getText().toString().equals(Strings.LABEL_ON)) {
                    btn.setText(Strings.LABEL_OFF);
                } else {
                    btn.setText(Strings.LABEL_ON);
                }
            }
        });

        addActor(btn);
    }

    protected String showOnOff(boolean value) {
        return value ? Strings.LABEL_ON : Strings.LABEL_OFF;
    }
}
