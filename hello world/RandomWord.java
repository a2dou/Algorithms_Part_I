import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        String text = StdIn.readString();
        int i = 1;
        while (text != null && text.length() > 0) {
            if (StdRandom.bernoulli(1d / i++)) {
                champion = text;
            }

            if (StdIn.isEmpty()) {
                break;
            } else {
                text = StdIn.readString();
            }
        }
        StdOut.println(champion);
    }
}
