import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PlaySoundEffect {
    static void playSound(String soundPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        if(soundPath.equals("assets\\sounds\\pawnjump.wav")) TimeUnit.SECONDS.sleep(1);
        File soundFile = new File(soundPath);
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(sound);
        FloatControl volume= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-15.0f);
        clip.start();
    }
}
