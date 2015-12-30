package com.dk.colorgame.service.board.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.GameConstants;
import com.dk.colorgame.constants.GooglePlayServicesIds;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.enums.AwardsEnum;
import com.dk.colorgame.enums.DiagonalDirectionsEnum;
import com.dk.colorgame.enums.DirectionsEnum;
import com.dk.colorgame.enums.EnvironmentsEnum;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.enums.RecordsEnum;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.Order;
import com.dk.colorgame.model.tiles.Tile;
import com.dk.colorgame.resources.Fonts;
import com.dk.colorgame.resources.Particles;
import com.dk.colorgame.resources.Sounds;
import com.dk.colorgame.resources.loaders.AssetLoader;
import com.dk.colorgame.service.PointsCalculator;
import com.dk.colorgame.service.board.AwardBoard;
import com.dk.colorgame.service.board.FloatingBoard;
import com.dk.colorgame.service.board.MainBoard;
import com.dk.colorgame.service.board.ScoreBoard;
import com.dk.colorgame.service.effects.ShakeEffect;
import com.dk.colorgame.service.effects.particles.GameParticleEffect;
import com.dk.colorgame.utils.BoardUtils;
import com.dk.colorgame.utils.GraphicUtils;
import com.dk.colorgame.utils.InputUtils;
import com.dk.colorgame.utils.SoundUtils;
import com.dk.colorgame.utils.Utils;
import com.dk.colorgame.view.UICamera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import static com.dk.colorgame.constants.Constants.BOARD_COLS;
import static com.dk.colorgame.constants.Constants.BOARD_ROWS;
import static com.dk.colorgame.constants.Constants.EFFECT_SHAKE_TIME;
import static com.dk.colorgame.constants.Constants.LABEL_AWARD_X_OFFSET;
import static com.dk.colorgame.constants.Constants.LABEL_AWARD_Y;
import static com.dk.colorgame.constants.Constants.LABEL_RECORD_Y;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public class MainBoardImpl implements MainBoard, InputProcessor {

    private final SpriteBatch spriteBatch;
    private final Camera camera;
    private final ScoreBoard scoreBoard;
    private final AwardBoard awardBoard;
    private final AwardBoard recordBoard;
    private final FloatingBoard floatingBoard;
    private final List<Tile> tiles;
    private final List<Tile> activeTiles;
    private final List<Tile> dyingTiles;
    private final BitmapFont font;
    private final ShakeEffect lightShakeEffect;
    private final ShakeEffect heavyShakeEffect;
    private Tile firstTouched;
    private Tile lastTouched;
    private boolean activatedReset;
    private ParticleEffect backgroundParticle;
    private Sound activateSound;
    private Sound award1;
    private Sound award2;
    private Sound award3;
    private Sound award4;

    public MainBoardImpl(SpriteBatch spriteBatch, Camera camera) {
        this.spriteBatch = spriteBatch;
        this.camera = camera;

        tiles = new ArrayList<>(Constants.BOARD_COLS * Constants.BOARD_ROWS);
        activeTiles = new ArrayList<>();
        dyingTiles = new ArrayList<>();
        scoreBoard = new ScoreBoardImpl(spriteBatch, Fonts.HOMESPUN);
        awardBoard = new AwardBoardImpl(spriteBatch, Fonts.HOMESPUN_BIG, LABEL_AWARD_X_OFFSET, LABEL_AWARD_Y);
        recordBoard = new RecordBoardImpl(spriteBatch, Fonts.HOMESPUN, 0, LABEL_RECORD_Y);
        floatingBoard = new FloatingBoardImpl(spriteBatch, Fonts.HOMESPUN);
        lightShakeEffect = new ShakeEffect(35, 20);
        heavyShakeEffect = new ShakeEffect(35, 60);

        scoreBoard.setCounterBoard(recordBoard);

        backgroundParticle = (ParticleEffect) AssetLoader.get(Particles.PARTICLE_BACKGROUND, ParticleEffect.class);
        backgroundParticle.setPosition(GraphicUtils.getWorldWidth() / 2, GraphicUtils.getWorldHeight() / 2);
        backgroundParticle.reset();

        font = (BitmapFont) AssetLoader.get(Fonts.HOMESPUN, BitmapFont.class);
        activateSound = (Sound) AssetLoader.get(Sounds.SOUND_TILE_ACTIVATE, Sound.class);
        award1 = (Sound) AssetLoader.get(Sounds.SOUND_AWARD1, Sound.class);
        award2 = (Sound) AssetLoader.get(Sounds.SOUND_AWARD2, Sound.class);
        award3 = (Sound) AssetLoader.get(Sounds.SOUND_AWARD3, Sound.class);
        award4 = (Sound) AssetLoader.get(Sounds.SOUND_AWARD4, Sound.class);

        InputUtils.getInputMultiplexer().addProcessor(this);
    }

    @Override
    public void create() {
        createTiles();
    }

    private void submitScore(int score) {
        Game.getActionResolver().showInterstitialAd();
        Game.getActionResolver().submitScore(score);
        Game.getActionResolver().submitTime(scoreBoard.getGameplayTime());

        unlockScoreAchievements(score);
        unlockTimeAchievements(scoreBoard.getGameplayTime());
        unlockSwipeTilesAchievements(scoreBoard.getCurrentSwipeTiles());
    }

    private void unlockScoreAchievements(int score) {
        if (score >= 1000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_1000);
        }

        if (score >= 2000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_2000);
        }

        if (score >= 5000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_5000);
        }

        if (score >= 10000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_10000);
        }

        if (score >= 25000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_25000);
        }

        if (score >= 500000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_50000);
        }

        if (score >= 100000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_100000);
        }

        if (score >= 200000) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_2000000);
        }
    }

    private void unlockTimeAchievements(int gameplayTime) {

        if (gameplayTime >= 5 * 60) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_5_MINS);
        }

        if (gameplayTime >= 10 * 60) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_10_MINS);
        }

        if (gameplayTime >= 15 * 60) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_15_MINS);
        }

        if (gameplayTime >= 30 * 60) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_30_MINS);
        }

        if (gameplayTime >= 45 * 60) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_45_MINS);
        }

        if (gameplayTime >= 60 * 60) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_60_MINS);
        }
    }

    public void unlockSwipeTilesAchievements(int swipeTiles) {
        if (swipeTiles >= 10) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_CONNECT_10_BLOCKS);
        }

        if (swipeTiles >= 15) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_CONNECT_15_BLOCKS);
        }

        if (swipeTiles >= 20) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_CONNECT_20_BLOCKS);
        }

        if (swipeTiles >= 25) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_CONNECT_25_BLOCKS);
        }

        if (swipeTiles >= 30) {
            Game.getActionResolver().unlockAchievement(GooglePlayServicesIds.ACHIEVEMENT_CONNECT_30_BLOCKS);
        }
    }

    public void processZooming() {
        UICamera uiCamera = (UICamera) camera;
        float zoomFactor = -0.02f;

        if (isGameFinished()) {
            uiCamera.zoom += zoomFactor;
        } else {
            if (uiCamera.zoom > 1) {
                uiCamera.zoom += zoomFactor;
            } else {
                uiCamera.zoom = 1;
            }
        }
    }

    public boolean hasFinishedEndingZoom() {
        UICamera uiCamera = (UICamera) camera;
        return uiCamera.zoom < 0;
    }

    @Override
    public void draw() {

        if (isGameFinished() && hasFinishedEndingZoom()) {
            Game.setGameState(GameStateEnum.MENU_GAME_OVER);
            submitScore(scoreBoard.getCurrentScore());
            return;
        }

        processZooming();

        drawBackgroundParticle();

        drawRegularTiles();
        drawSpecialTiles();
        drawActiveTiles();

        scoreBoard.draw();
        awardBoard.draw();
        recordBoard.draw();
        floatingBoard.draw(firstTouched, lastTouched, activeTiles);
        lightShakeEffect.update(Gdx.graphics.getDeltaTime(), camera, new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2));
        heavyShakeEffect.update(Gdx.graphics.getDeltaTime(), camera, new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2));
        updateAndDrawDyingTiles();

        renderTouchLight();

        if (Options.drawFps) {
            GraphicUtils.drawFPS(spriteBatch, font);
        }

        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV) {
            GraphicUtils.drawDevInfo(spriteBatch, font);
        }
    }

    private void drawBackgroundParticle() {

        if (Options.particleEffects) {
            backgroundParticle.getEmitters().first().setPosition(GraphicUtils.getWorldWidth() / 2, GraphicUtils.getWorldHeight() / 2);
            backgroundParticle.update(Gdx.graphics.getDeltaTime());
            backgroundParticle.draw(spriteBatch);
        }
    }

    private void renderTouchLight() {
        if (Gdx.input.isTouched(0)) {
            GraphicUtils.renderLight(spriteBatch, InputUtils.getTouchCoordinates(0).getX() - 120, InputUtils.getTouchCoordinates(0).getY() - 120, 0.5f);
        }
    }

    private void drawRegularTiles() {
        for (Tile tile : tiles) {
            if (tile.getTileFeature() == TileFeatureEnum.REGULAR && !tile.isActivated()) {
                tile.draw();
            }
        }
    }

    private void drawSpecialTiles() {
        for (Tile tile : tiles) {
            if (tile.getTileFeature() != TileFeatureEnum.REGULAR) {
                tile.draw();
            }
        }
    }

    private void drawActiveTiles() {
        for (Tile tile : tiles) {
            if (tile.getTileFeature() == TileFeatureEnum.REGULAR && tile.isActivated()) {
                tile.draw();
            }
        }
    }

    private void updateAndDrawDyingTiles() {

        Iterator<Tile> it = dyingTiles.iterator();

        while (it.hasNext()) {
            Tile tile = it.next();

            if (tile.shallBeRemoved()) {
                it.remove();
            } else {
                tile.draw();
            }
        }

        Gdx.app.debug("Dying tiles size: ", Integer.toString(dyingTiles.size()));
    }

    @Override
    public void reset() {
        UICamera camera = (UICamera) GraphicUtils.getCamera();
        camera.zoom = 1.5f;

        saveIfHighScore();
        tiles.clear();
        activeTiles.clear();
        createTiles();

        initScaleForAllTiles();

        scoreBoard.reset();
    }

    private void initScaleForAllTiles() {
        for (Tile tile : tiles) {
            tile.setScale(Constants.TILE_INIT_RESET_SIZE);
        }
    }

    private Tile getTouchedTile(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.isTouched(x, y)) {
                return tile;
            }
        }

        return null;
    }

    private Tile getTile(Order order) {
        for (Tile tile : tiles) {
            if (tile.getOrder().equals(order)) {
                return tile;
            }
        }
        return null;
    }

    private Tile getNeighbour(Tile ofTile, DirectionsEnum direction) {
        int col = ofTile.getOrder().getColumn();
        int row = ofTile.getOrder().getRow();

        switch (direction) {
            case UP:
                return getTile(new Order(col, row + 1));
            case DOWN:
                return getTile(new Order(col, row - 1));
            case LEFT:
                return getTile(new Order(col - 1, row));
            case RIGHT:
                return getTile(new Order(col + 1, row));
        }

        return null;
    }

    private Tile getDiagonalNeighbour(Tile ofTile, DiagonalDirectionsEnum direction) {
        int col = ofTile.getOrder().getColumn();
        int row = ofTile.getOrder().getRow();

        switch (direction) {

            case UPPER_LEFT:
                return getTile(new Order(col - 1, row + 1));
            case UPPER_RIGHT:
                return getTile(new Order(col + 1, row + 1));
            case LOWER_LEFT:
                return getTile(new Order(col - 1, row - 1));
            case LOWER_RIGHT:
                return getTile(new Order(col + 1, row - 1));
        }

        return null;
    }

    private List<Tile> getNeighbours(Tile ofTile) {
        List<Tile> neighbours = new ArrayList<>();

        for (DirectionsEnum direction : DirectionsEnum.values()) {

            Tile neighbour = getNeighbour(ofTile, direction);

            if (neighbour != null) {
                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }

    private List<Tile> getDiagonalNeighbours(Tile ofTile) {
        List<Tile> neighbours = new ArrayList<>();

        for (DiagonalDirectionsEnum direction : DiagonalDirectionsEnum.values()) {

            Tile neighbour = getDiagonalNeighbour(ofTile, direction);

            if (neighbour != null) {
                neighbours.add(neighbour);
            }
        }

        return neighbours;

    }

    private boolean areNeighbours(Tile tile1, Tile tile2) {
        List<Tile> neighbours = getNeighbours(tile1);

        for (Tile tile : neighbours) {
            if (tile == tile2) {
                return true;
            }
        }
        return false;
    }

    private void deactivateTiles() {
        for (Tile tile : tiles) {
            tile.deactivate();
        }
        activeTiles.clear();
        firstTouched = null;
        lastTouched = null;
    }


    private Tile randomTile(SpriteBatch spriteBatch) {

        Tile tile = BoardUtils.randomTile(spriteBatch);

        if (awardBoard.withdrawAwardPoint()) {
            tile = BoardUtils.randomExtraBonusTile(spriteBatch, this);
        }

        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV) {
            tile = BoardUtils.randomTestTiles(spriteBatch, this, tile);
        }

        return tile;
    }

    private void selectFirstTile(int screenX, int screenY) {

        Tile touchedTile = getTouchedTile(screenX, screenY);
        firstTouched = touchedTile;
        lastTouched = touchedTile;

        if (touchedTile != null) {

            if (touchedTile.getTileFeature() == TileFeatureEnum.BONUS) {
                return;
            }

            if (isTileTypeUnderSecondFingerDifferent(touchedTile)) {
                return;
            }

            activateTile(touchedTile);
            SoundUtils.playSound(activateSound);
        }
    }

    private boolean isTileTypeUnderSecondFingerDifferent(Tile touchedTile) {
        return activeTiles.size() > 0 && activeTiles.get(0).getTileType() != touchedTile.getTileType();
    }

    private void processSelectedTiles() {

        if (activeTiles.size() > 1 && !isGameFinished()) {
            scoreAndRemoveTiles();
            shakeWhenDynamite(dyingTiles);
            squeezeTilesDown(dyingTiles);
            createTiles();
        } else {
            deactivateTiles();
        }
    }

    private void shakeWhenDynamite(List<Tile> tiles) {
        for (Tile tile : tiles) {
            if (tile.getTileType() == TileTypesEnum.BOMB) {
                heavyShakeEffect.shake(EFFECT_SHAKE_TIME);
                return;
            }

            if (tile.getTileType() == TileTypesEnum.DYNAMITE) {
                lightShakeEffect.shake(EFFECT_SHAKE_TIME);
                return;
            }
        }
    }

    private boolean deselectTiles(Tile touchedTile) {

        if (activeTiles.size() > 1 && touchedTile == activeTiles.get(activeTiles.size() - 2)) {
            Tile tile = activeTiles.get(activeTiles.size() - 1);
            tile.deactivate();
            activeTiles.remove(activeTiles.size() - 1);
            lastTouched = touchedTile;
            SoundUtils.playSound(activateSound);
            return true;
        }
        return false;

    }

    private boolean checkIfExplosiveIsSelected() {
        for (Tile tile : activeTiles) {
            if (tile.getTileType() == TileTypesEnum.DYNAMITE || tile.getTileType() == TileTypesEnum.BOMB) {
                return true;
            }
        }
        return false;
    }

    private void selectMultipleTiles(int screenX, int screenY) {
        Tile touchedTile = getTouchedTile(screenX, screenY);

        if (checkIfExplosiveIsSelected()) {
            return;
        }

        if (deselectTiles(touchedTile)) {
            return;
        }

        if (touchedTile == null || touchedTile.isActivated()) {
            return;
        }

        if (lastTouched == null) {
            activateTile(touchedTile);
            SoundUtils.playSound(activateSound);
            return;
        }

        if (lastTouched == touchedTile) {
            return;
        }

        if (firstTouched == null) {
            firstTouched = lastTouched;
        }

        // ensure first touched is not bomb, dynamite or other
        if (firstTouched.getTileFeature() == TileFeatureEnum.BONUS) {
            return;
        }

        if (isCorrectTypeToActivate(touchedTile)) {
            activateTile(touchedTile);
            SoundUtils.playSound(activateSound);
        }

        Gdx.app.debug("Tiles size: ", Integer.toString(tiles.size()));

    }

    private boolean isCorrectTypeToActivate(Tile tile) {
        return areNeighbours(tile, lastTouched) &&
                (tile.getTileType() == firstTouched.getTileType() || tile.getTileFeature() == TileFeatureEnum.BONUS);
    }

    private void saveIfHighScore() {
        scoreBoard.saveIfHighScore();
    }

    private boolean isGameFinished() {
        return scoreBoard.isGameFinished() || !canTilesStillBeConnected();
    }

    private void activateTile(Tile tile) {
        tile.activate();
        activeTiles.add(tile);
        lastTouched = tile;
    }

    private void deactivateTile(Tile tile) {
        tile.deactivate();
        activeTiles.remove(tile);
        lastTouched = null;
    }

    @Override
    public void activateNeighbours(Tile tile) {
        List<Tile> neighbours = getNeighbours(tile);

        for (Tile neighbour : neighbours) {
            if (!neighbour.isActivated()) {
                activateTile(neighbour);
            }
        }

    }

    @Override
    public void activateRandomNeighbour(Tile tile) {
        List<Tile> neighbours = getNeighbours(tile);
        List<Tile> notActivatedNeighbours = new ArrayList<>();

        for (Tile neighbour : neighbours) {
            if (!neighbour.isActivated()) {
                notActivatedNeighbours.add(neighbour);
            }
        }

        if (notActivatedNeighbours.size() == 0) {
            return;
        }

        Random random = Utils.getRandom();
        Tile tileToActivate = notActivatedNeighbours.get(random.nextInt(notActivatedNeighbours.size()));
        activateTile(tileToActivate);
    }

    @Override
    public void activateDiagonalNeighbours(Tile tile) {
        List<Tile> neighbours = getDiagonalNeighbours(tile);

        for (Tile neighbour : neighbours) {
            if (!neighbour.isActivated()) {
                activateTile(neighbour);
            }
        }

    }

    @Override
    public void activateRandomTiles(int number) {

        while (number > 0) {

            Tile tile = getRandomTile();

            if (tile == null || tile.isActivated()) {
                continue;
            }

            activateTile(tile);
            number--;
        }
    }

    @Override
    public Tile getRandomTile() {
        Random random = Utils.getRandom();

        int column = random.nextInt(BOARD_COLS) + 1;
        int row = random.nextInt(BOARD_ROWS) + 1;

        return getTile(new Order(column, row));
    }

    private boolean canTilesStillBeConnected() {
        for (Tile tile : tiles) {
            if (tile.getTileFeature() == TileFeatureEnum.BONUS) {
                return true;
            }
        }

        for (Tile tile : tiles) {
            List<Tile> neighbours = getNeighbours(tile);

            for (Tile neighbour : neighbours) {
                if (tile.getTileType().equals(neighbour.getTileType())) {
                    return true;
                }
            }
        }

        return false;
    }

    private void squeezeTilesDown(List<Tile> tiles) {

        for (Tile activeTile : tiles) {
            for (int row = getMinRowToSqueze(tiles); row <= BOARD_ROWS; row++) {
                Tile tile = getTile(new Order(activeTile.getOrder().getColumn(), row));

                if (tile == null) {
                    continue;
                }

                while (getNeighbour(tile, DirectionsEnum.DOWN) == null) {
                    tile.updateOrder(DirectionsEnum.DOWN);

                    if (isFirstRow(tile)) {
                        break;
                    }
                }
            }
        }
    }

    private int getMinRowToSqueze(List<Tile> tiles) {
        int minRow = 2;

        TreeSet<Integer> rows = new TreeSet<>();

        for (Tile tile : tiles) {
            rows.add(tile.getOrder().getRow());
        }

        if (rows.first() < minRow) {
            return minRow;
        }
        return rows.first();
    }

    private boolean isFirstRow(Tile tile) {
        return tile.getOrder().getRow() == 1;
    }

    private void scoreAndRemoveTiles() {

        int points = PointsCalculator.calculateAddedPoints(activeTiles, firstTouched);

        scoreBoard.addScore(points);
        scoreBoard.addSeconds(PointsCalculator.calculateAddedSeconds(activeTiles, firstTouched));

        addAwards(activeTiles.size());
        addRecord(points, activeTiles.size());

        tiles.removeAll(activeTiles);

        dyingTiles.addAll(activeTiles);

        for (Tile tile : activeTiles) {
            tile.die();
        }

        activeTiles.clear();
    }

    private void addAwards(int tiles) {

        final int minAwardTiles = GameConstants.GAME_AWARDS_MIN_TILES;

        if (tiles >= minAwardTiles && activeTiles.size() > 0) {
            Color color = activeTiles.get(0).getColor();
            GraphicUtils.setBackgroundColor(color);
            addAwardParticle(color);
        }

        if (tiles >= GameConstants.GAME_MIN_TILES_FOR_BONUS && tiles < minAwardTiles) {
            awardBoard.addPoints(1);
        } else if (tiles == minAwardTiles) {
            awardBoard.add(AwardsEnum.NICE.getAward(), 2);
            SoundUtils.playSound(award1);
        } else if (tiles > minAwardTiles && tiles <= minAwardTiles + 2) {
            awardBoard.add(AwardsEnum.GOOD.getAward(), 3);
            SoundUtils.playSound(award1);
        } else if (tiles > minAwardTiles + 2 && tiles <= minAwardTiles + 4) {
            awardBoard.add(AwardsEnum.GREAT.getAward(), 4);
            SoundUtils.playSound(award2);
        } else if (tiles > minAwardTiles + 4 && tiles <= minAwardTiles + 6) {
            awardBoard.add(AwardsEnum.WOW.getAward(), 5);
            SoundUtils.playSound(award3);
        } else if (tiles > minAwardTiles + 6) {
            awardBoard.add(AwardsEnum.COLOR_SPREE.getAward(), 6);
            SoundUtils.playSound(award4);
        }
    }

    private void addAwardParticle(Color color) {
        float x = GraphicUtils.getWorldWidth() / 2;
        float y = GraphicUtils.getWorldHeight() / 2;

        if (color.equals(Colors.BLUE)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_BLUE_AWARD, new Vector2(x, y)));
        } else if (color.equals(Colors.VIOLET)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_VIOLET_AWARD, new Vector2(x, y)));
        } else if (color.equals(Colors.GREEN)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_GREEN_AWARD, new Vector2(x, y)));
        } else if (color.equals(Colors.YELLOW)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_YELLOW_AWARD, new Vector2(x, y)));
        } else if (color.equals(Colors.RED)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_RED_AWARD, new Vector2(x, y)));
        } else if (color.equals(Colors.CYAN)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_CYAN_AWARD, new Vector2(x, y)));
        } else if (color.equals(Colors.GRAY)) {
            Game.getBackgroundParticleSpawner().addEffect(new GameParticleEffect(Particles.PARTICLE_GRAY_AWARD, new Vector2(x, y)));
        }
    }

    private void addRecord(int points, int selectedTiles) {
        if (scoreBoard.updateHighSwipeScore(points) && scoreBoard.updateHighSwipeTiles(selectedTiles)) {
            recordBoard.add(RecordsEnum.POINTS_SELECTION_RECORD.getRecord(), 0);
        } else if (scoreBoard.updateHighSwipeScore(points)) {
            recordBoard.add(RecordsEnum.POINTS_RECORD.getRecord(), 0);
        } else if (scoreBoard.updateHighSwipeTiles(selectedTiles)) {
            recordBoard.add(RecordsEnum.SELECTION_RECORD.getRecord(), 0);
        }
    }

    private void createTiles() {

        int addedTiles = 0;

        for (int col = 1; col <= BOARD_COLS; col++) {
            for (int row = 1; row <= BOARD_ROWS; row++) {

                Tile tile = getTile(new Order(col, row));

                if (tile == null) {
                    tile = randomTile(spriteBatch);
                    Order tileOrder = new Order(col, row);
                    tile.setOrder(tileOrder);
                    tiles.add(tile);
                    addedTiles++;
                }
            }
        }

        Gdx.app.debug("Added tiles: ", Integer.toString(addedTiles));
    }

    @Override
    public boolean keyDown(int keycode) {

        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV && keycode == Input.Keys.SPACE) {
            Game.setGameState(GameStateEnum.MENU_MAIN);
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Game.getGameState() == GameStateEnum.STARTED) {

            if (pointer > 0) {
                deactivateTiles();
                activatedReset = true;
                return true;
            }

            selectFirstTile(screenX, screenY);
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (Game.getGameState() == GameStateEnum.STARTED) {
            processSelectedTiles();
            if (pointer == 0) {
                activatedReset = false;
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Game.getGameState() == GameStateEnum.STARTED) {
            if (activatedReset) {
                return true;
            }

            if (firstTouched == null) {
                selectFirstTile(screenX, screenY);
            } else {
                selectMultipleTiles(screenX, screenY);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
