import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;

/**
 * @author 老爷保号
 */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;

    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static final int totalNotes = 37;

    private static double ithConcert(int i) {
        return CONCERT_A * Math.pow(2, (i - 24) / 12);
    }

    public static void main(String[] args) {
        GuitarString[] guitarStrings = new GuitarString[totalNotes];
        for (int i = 0; i < totalNotes; i++) {
            guitarStrings[i] = new GuitarString(ithConcert(i));
        }

        while (true) {
            if(StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index < 0){
                    System.out.println("please enter valid key : " + KEYBOARD);
                    continue;
                }
                guitarStrings[index].pluck();
            }

            double sample = 0;
            for (GuitarString string : guitarStrings){
                sample += string.sample();
            }
            StdAudio.play(sample);
            for (GuitarString string : guitarStrings){
                string.tic();
            }
        }

    }


}
