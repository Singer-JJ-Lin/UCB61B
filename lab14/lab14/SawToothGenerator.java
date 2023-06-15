package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

/**
 * @author 老爷保号
 */
public class SawToothGenerator implements Generator {
    private int period;

    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        this.state = 0;
    }

    @Override
    public double next() {
        state = (state + 1);
        return normalize(state % period);
    }

    private double normalize(int x) {
        return (double)x * 2 / period - 1;
    }
}
