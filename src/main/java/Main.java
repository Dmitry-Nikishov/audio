import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {
        final float sampleRate = 8000;

        AudioFormat af = new AudioFormat(
                sampleRate /*sample rate*/,
                8,
                1,
                true,
                false);

        SourceDataLine sdl;
        try {
            sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();

            final int msec = 2;
            final double hz = 3000.0;

            byte[] buf = new byte[1];

            // create audio signal
            for (int i = 0; i < msec*8; i++) {
                double angle = i/(sampleRate/hz)*2.0*Math.PI;
                buf[0] = (byte)(Math.sin(angle) * 127.0);
                sdl.write(buf, 0, 1);
            }

            sdl.drain();
            sdl.stop();
            sdl.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

