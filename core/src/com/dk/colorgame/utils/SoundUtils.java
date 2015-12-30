package com.dk.colorgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.dk.colorgame.constants.Options;

import java.util.List;
import java.util.Random;

/**
 * Created by Dawid Kotarba on 2015-07-31.
 */
public class SoundUtils {

    private SoundUtils() {
        // left blank
    }

    public static Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal("sounds/" + path));
    }

    public static void playSound(Sound sound) {
        if (sound != null && Options.volume > 0) {
            sound.play((float) Options.volume / 10);
        }
    }

    public static void playSound(Sound sound, int volume, float pitch) {
        long soundId = sound.play((float) volume / 10);
        sound.setPitch(soundId, pitch);
    }

    public static void playRandomSound(List<Sound> container, float volume, float pitch) {
        Random random = Utils.getRandom();
        long soundId = container.get(random.nextInt(container.size())).play(volume);
        container.get(random.nextInt(container.size())).setPitch(soundId, pitch);
    }
}
