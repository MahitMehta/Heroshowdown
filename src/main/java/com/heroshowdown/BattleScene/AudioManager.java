package com.heroshowdown.BattleScene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

public class AudioManager {
    public final MediaPlayer fieldMusic; 
    public final MediaPlayer battleMusic; 

    public AudioManager() {
        this.fieldMusic = this.getMediaPlayer("field_music.wav", true);
        this.battleMusic = this.getMediaPlayer("battle_music.mp3", true);
    }

    private MediaPlayer getMediaPlayer(String path) {
        return this.getMediaPlayer(path, false);
    }

    /**
     * @param loop if set to true, then audio will loop
     * @param path path of audio file located in src/assets/audio/
     * @return Mediaplayer JavaFX Object
     */
    private MediaPlayer getMediaPlayer(String path, boolean loop) {
        String absolutePath = System.getProperty("user.dir") + "/src/assets/audio/" + path;
        Media sound = new Media(new File(absolutePath).toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(sound);

        if (loop) {
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                    mediaPlayer.play();
                }
            });
        }

        return mediaPlayer; 
    }
}
