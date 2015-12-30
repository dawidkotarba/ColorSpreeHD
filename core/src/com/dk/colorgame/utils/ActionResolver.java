package com.dk.colorgame.utils;

/**
 * Created by Dawid Kotarba on 2015-08-17.
 */
public interface ActionResolver {

    public void signIn();

    public void signOut();

    public void submitScore(long score);

    public void submitTime(int timeInSeconds);

    public void showScoreLeaderboard();

    public void showTimeLeaderboard();

    public boolean isSignedIn();

    void unlockAchievement(String achievementId);

    void showAchievements();

    void showInterstitialAd();
}
