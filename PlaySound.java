import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;


public class PlaySound {
    public PlaySound(){
        try {
            Clip bgm = AudioSystem.getClip();
            InputStream musicInputStream = PlaySound.class.getClassLoader()
                    .getResourceAsStream("./data/BGM/Vexento - Eyes Closed.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicInputStream);
            bgm.open(ais);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}