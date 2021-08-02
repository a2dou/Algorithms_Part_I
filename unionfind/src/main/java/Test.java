import java.util.Random;

public class Test {

    public static void main(String[] args) {
        int max = 10;
        QuickUnionImproved quickFind = new QuickUnionImproved(max);
        Random random = new Random();
        for (int i = 0; i < max / 2; i++) {
            int p = random.nextInt(max);
            int q = random.nextInt(max);
            quickFind.union(p, q);
            System.out.println(String.format("%d - %d", p, q));
        }

        for (int i = 0; i < max / 2; i++) {
            int p = random.nextInt(max);
            int q = random.nextInt(max);
            System.out.println(String.format("%d - %d : %s", p, q, quickFind.connected(p, q)));
        }
    }
}
