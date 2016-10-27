package MPAss4;

/**
 * Created by niervin on 10/22/2016.
 */
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    //Sound of the BALL;
    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
    //background music
    public static final AudioClip UNDTALE = Applet.newAudioClip(Sound.class.getResource("Undertale.wav"));
}