package synthesizer;

/**
 * @author 老爷保号
 */
public class GuitarString {
    /**
     * Sample rate
     */
    private static final int SR = 44100;

    /**
     * Energy decay factor
     */
    private static final double DECAY = .996;

    /**
     * Buffer for storing sound data
     */
    private BoundedQueue<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int)Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    /**
     * Pluck the guitar string by replacing the buffer with white noise.
     */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /**
     * Advance the simulation one time step by performing one iteration of the Karplus-Strong algorithm.
     */
    public void tic() {
        Double dequeue = buffer.dequeue();
        buffer.enqueue(DECAY * (dequeue + buffer.peek()) * 0.5);

    }

    /**
     * Return the double at the front of the buffer.
     * @return
     */
    public double sample() {
        return buffer.peek();
    }
}
