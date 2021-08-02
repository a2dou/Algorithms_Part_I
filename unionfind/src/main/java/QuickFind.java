public class QuickFind implements IUF {
    private int values[];

    public QuickFind(int n) {
        values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i;
        }
    }

    public void union(int p, int q) {
        int start = values[p];
        int end = values[q];
        for (int i = 0; i < values.length; i++) {
            if (values[i] == values[p]) {
                values[i] = values[q];
            }
        }
    }

    public boolean connected(int p, int q) {
        return values[p] == values[q];
    }
}
