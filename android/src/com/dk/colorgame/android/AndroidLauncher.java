package com.dk.colorgame.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dk.colorgame.Game;
import com.dk.colorgame.constants.AdIds;
import com.dk.colorgame.constants.Constants;
import com.dk.colorgame.constants.GoogleAnalyticsIds;
import com.dk.colorgame.constants.GooglePlayServicesIds;
import com.dk.colorgame.constants.Options;
import com.dk.colorgame.enums.EnvironmentsEnum;
import com.dk.colorgame.enums.GameStateEnum;
import com.dk.colorgame.utils.ActionResolver;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.games.Games;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AndroidLauncher extends AndroidApplication implements ActionResolver, GameHelper.GameHelperListener {

    private AdView adView;
    private View gameView;
    private InterstitialAd interstitialAd;
    private Game game;

    // analytics
    private GoogleAnalytics analytics;
    private Tracker tracker;
    private Map<String, String> trackingDetails;
    private SimpleDateFormat dateFormatter;

    private GameHelper gameHelper;
    private final static int REQUEST_CODE_UNUSED = 9002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        game = new Game(this);
        gameView = initializeForView(game, config);
        initialize(game, config);

        createInterstitialAd();
        initGoogleAnalytics();
        initGooglePlayServices();
    }

    private void initGooglePlayServices() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD) {
            gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
            gameHelper.enableDebugLog(false);
            gameHelper.setup(this);
        }
    }

    private void initGoogleAnalytics() {
        // use values from Options after game init
        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD && Options.stats) {
            analytics = GoogleAnalytics.getInstance(this);
            analytics.setLocalDispatchPeriod(1800);
            tracker = analytics.newTracker(GoogleAnalyticsIds.GA_TRACKING_ID);
            tracker.enableAdvertisingIdCollection(true);
            tracker.enableAutoActivityTracking(true);
            tracker.setSampleRate(100);
            tracker.enableExceptionReporting(true);
            tracker.setSessionTimeout(-1);

            dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
            trackingDetails = new HashMap<String, String>();
        }
    }

    //@Override
    protected void onCreate_banner_ad_bak_(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        AdView admobView = createBannerAdView();
        View gameView = createGameView(config);

        layout.addView(admobView);
        layout.addView(gameView);

        setContentView(layout);
        startAdvertising(admobView);

        initGoogleAnalytics();
        initGooglePlayServices();
    }

    private void createInterstitialAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AdIds.INTERSTITIAL_AD_UNIT_ID);
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                interstitialAd.show();
            }
        });
    }

    private AdView createBannerAdView() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setId(AdIds.BANNER_AD_ID);

        if (showAds())
            adView.setAdUnitId(AdIds.BANNER_AD_UNIT_ID);
        else
            adView.setAdUnitId("");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);

        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            adView.setBackgroundColor(Color.DKGRAY);
        else
            adView.setBackgroundColor(Color.BLACK);

        return adView;
    }

    @Override
    public void showInterstitialAd() {

        if (!showAds())
            return;

        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    AdRequest interstitialRequest = new AdRequest.Builder().build();
                    interstitialAd.loadAd(interstitialRequest);
                }
            });
        } catch (Exception e) {
            Gdx.app.log(Constants.ANDROID_TAG, "Exception in showing interstitial ad.", e);
        }
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        game = new Game(this);
        gameView = initializeForView(game, cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
    }

    private void startAdvertising(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private boolean showAds() {
        return Constants.ENVIRONMENT == EnvironmentsEnum.PROD && Constants.SHOW_ADS;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD)
            gameHelper.onStart(this);

        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD && Options.stats && tracker != null && trackingDetails != null) {
            trackingDetails.put("App start time", dateFormatter.format(new Date()));
            GoogleAnalytics.getInstance(this).reportActivityStart(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD)
            gameHelper.onStop();

        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD && Options.stats && tracker != null && trackingDetails != null) {
            trackingDetails.put("App end time", dateFormatter.format(new Date()));
            tracker.send(trackingDetails);
            GoogleAnalytics.getInstance(this).reportActivityStop(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Constants.ENVIRONMENT == EnvironmentsEnum.PROD)
            gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) adView.resume();
    }

    @Override
    public void onPause() {
        if (adView != null) adView.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (adView != null) adView.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return false;
    }

    @Override
    public void onBackPressed() {

        GameStateEnum gameState = Game.getGameState();

        switch (gameState) {

            case MENU_RECORDS:
                Game.setGameState(GameStateEnum.MENU_SCORES);
                break;
            case MENU_SCORE_RESET:
                Game.setGameState(GameStateEnum.MENU_RECORDS);
                break;
            case MENU_GAME_OVER:
                break;
            case MENU_SCORES:
                Game.setGameState(GameStateEnum.MENU_MAIN);
                break;
            case MENU_OPTIONS:
                Game.setGameState(GameStateEnum.MENU_MAIN);
                break;
            case MENU_OPTIONS_VIDEO:
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
                break;
            case MENU_OPTIONS_AUDIO:
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
                break;
            case MENU_OPTIONS_GAME:
                Game.setGameState(GameStateEnum.MENU_OPTIONS);
                break;
            case MENU_CREDITS:
                Game.setGameState(GameStateEnum.MENU_RECORDS);
                break;
            case PAUSED:
                Game.setGameState(GameStateEnum.STARTED);
                break;
            case STARTED:
                Game.setGameState(GameStateEnum.PAUSED);
                break;
            case MENU_MAIN:
                showQuitMenu();
                break;
        }
    }

    @Override
    public void signIn() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void submitScore(long score) {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;
        if (gameHelper.isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), GooglePlayServicesIds.LEADERBOARD_HIGH_SCORES, score);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }

    @Override
    public void submitTime(int timeInSeconds) {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;
        if (gameHelper.isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), GooglePlayServicesIds.LEADERBOARD_TIME, timeInSeconds * 1000);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }

    @Override
    public void showScoreLeaderboard() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;

        if (gameHelper.isSignedIn()) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), GooglePlayServicesIds.LEADERBOARD_HIGH_SCORES), REQUEST_CODE_UNUSED);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }

    @Override
    public void showTimeLeaderboard() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;

        if (gameHelper.isSignedIn()) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), GooglePlayServicesIds.LEADERBOARD_TIME), REQUEST_CODE_UNUSED);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return false;

        return gameHelper.isSignedIn();
    }

    @Override
    public void unlockAchievement(String achievementId) {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;
        if (gameHelper.isSignedIn()) {
            Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }

    @Override
    public void showAchievements() {
        if (Constants.ENVIRONMENT == EnvironmentsEnum.DEV)
            return;

        if (gameHelper.isSignedIn()) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), REQUEST_CODE_UNUSED);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }

    @Override
    public void onSignInFailed() {
    }

    @Override
    public void onSignInSucceeded() {
    }

    public void showQuitMenu() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit")
                .setMessage("Are you sure to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Gdx.app.exit();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
