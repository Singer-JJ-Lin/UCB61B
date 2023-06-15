package lab14;

import lab14lib.Generator;

/**
 * @author 老爷保号
 */
public class StrangeBitwiseGenerator implements Generator {
    private int state;

    private int period;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    private double normalize(int x) {
        return (double) x * 2 / period - 1;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state & (state >>> 7) % period);
    }
}
