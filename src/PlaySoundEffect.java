import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlaySoundEffect {
    public final String gameplayTheme = "assets\\sounds\\music\\gameplaytheme.wav";

    static void playSound(String soundPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        if(soundPath.equals("assets\\sounds\\pawnjump.wav")) TimeUnit.SECONDS.sleep(1);
        File soundFile = new File(soundPath);
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
        Clip soundEffect = AudioSystem.getClip();
        soundEffect.open(sound);
        FloatControl volume= (FloatControl) soundEffect.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-15.0f);
        soundEffect.start();
    }
    static Clip playMusicOnRepeat(String musicPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File musicFile = new File(musicPath);
        AudioInputStream sound = AudioSystem.getAudioInputStream(musicFile);
        Clip musicTheme = AudioSystem.getClip();
        musicTheme.open(sound);
        FloatControl volume= (FloatControl) musicTheme.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-30.0f);
        musicTheme.loop(Clip.LOOP_CONTINUOUSLY);
        return musicTheme;
    }
    static void stopMusicOnRepeat(Clip clip){
        clip.stop();
    }
    static void playRandomDice(String what) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(5)+1;
        switch(randomNumber){
            case 1:
                playSound("assets\\sounds\\" + what + randomNumber + ".wav");
            case 2:
                playSound("assets\\sounds\\" + what + randomNumber + ".wav");
            case 3:
                playSound("assets\\sounds\\" + what + randomNumber + ".wav");
            case 4:
                playSound("assets\\sounds\\" + what + randomNumber + ".wav");
            case 5:
                playSound("assets\\sounds\\" + what + randomNumber + ".wav");
        }
    }
}
