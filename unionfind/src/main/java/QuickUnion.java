public class QuickUnion implements IUF {
    private int values[];

    public QuickUnion(int n) {
        values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i;
        }
    }

    public int root(int i) {
        while (values[i] != i) {
            i = values[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        values[pRoot] = qRoot;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
