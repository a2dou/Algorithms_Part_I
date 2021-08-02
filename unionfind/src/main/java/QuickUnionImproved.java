public class QuickUnionImproved implements IUF {
    private int values[];
    private int size[];

    public QuickUnionImproved(int n) {
        values = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i;
            size[i] = 1;
        }
    }

    public int root(int i) {
        while (values[i] != i) {
            values[i] = values[values[i]];
            i = values[i];

        }
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (size[pRoot] > size[qRoot]) {
            values[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            values[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
