package com.dk.colorgame.desktop;

import com.dk.colorgame.utils.ActionResolver;

/**
 * Created by Dawid Kotarba on 2015-08-19.
 */
public class DesktopActionResolver implements ActionResolver {
    @Override
    public void signIn() {
        // intentionally left blank
    }

    @Override
    public void signOut() {
        // intentionally left blank
    }

    @Override
    public void submitScore(long score) {
        // intentionally left blank
    }

    @Override
    public void submitTime(int time) {
        // intentionally left blank
    }

    @Override
    public void showScoreLeaderboard() {
        // intentionally left blank
    }

    @Override
    public void showTimeLeaderboard() {
        // intentionally left blank
    }

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public void unlockAchievement(String achievementId) {
        // intentionally left blank
    }

    @Override
    public void showAchievements() {
        // intentionally left blank
    }

    @Override
    public void showInterstitialAd() {
        // intentionally left blank
    }
}
