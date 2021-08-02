import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[][] arrays;
    private int opened = 0;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufNoneHeadTail;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be a positive integer");
        }
        arrays = new boolean[n][n];
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufNoneHeadTail = new WeightedQuickUnionUF(n * n + 1);
    }

    private int twoDToOneD(int x, int y, int num) {
        return x * num + y;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("row and col must between 1 to " + n);
        }
        row = row - 1;
        col = col - 1;
        if (!arrays[row][col]) {
            opened++;
            arrays[row][col] = true;

            // 第一行和最后一行连接虚拟的头尾
            if (row == 0) {
                uf.union(twoDToOneD(row, col, n) + 1, 0);
                ufNoneHeadTail.union(twoDToOneD(row, col, n) + 1, 0);
            }

            if (row == arrays.length - 1) {
                uf.union(twoDToOneD(row, col, n) + 1, n * n + 1);
            }

            //边界判断
            if (row - 1 >= 0 && arrays[row - 1][col]) {
                uf.union(twoDToOneD(row - 1, col, n) + 1, twoDToOneD(row, col, n) + 1);
                ufNoneHeadTail.union(twoDToOneD(row - 1, col, n) + 1, twoDToOneD(row, col, n)+ 1);
            }

            if (row + 1 < n && arrays[row + 1][col]) {
                uf.union(twoDToOneD(row + 1, col, n) + 1, twoDToOneD(row, col, n) + 1);
                ufNoneHeadTail.union(twoDToOneD(row + 1, col, n) + 1, twoDToOneD(row, col, n)+ 1);
            }

            if (col - 1 >= 0 && arrays[row][col - 1]) {
                uf.union(twoDToOneD(row, col - 1, n) + 1, twoDToOneD(row, col, n) + 1);
                ufNoneHeadTail.union(twoDToOneD(row, col - 1, n) + 1, twoDToOneD(row, col, n)+ 1);
            }

            if (col + 1 < n && arrays[row][col + 1]) {
                uf.union(twoDToOneD(row, col + 1, n) + 1, twoDToOneD(row, col, n) + 1);
                ufNoneHeadTail.union(twoDToOneD(row, col + 1, n) + 1, twoDToOneD(row, col, n)+ 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("row and col must between 1 to " + n);
        }
        row = row - 1;
        col = col - 1;
        return arrays[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("row and col must between 1 to " + n);
        }
        row = row - 1;
        col = col - 1;
        return arrays[row][col] && ufNoneHeadTail.find(twoDToOneD(row, col, n) + 1) == ufNoneHeadTail.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 3;
        Percolation percolation = new Percolation(n);
//        percolation.open(1, 2);
        percolation.open(3, 1);
        percolation.open(2, 2);
        percolation.open(1, 2);
//        percolation.open(2, 9);
//        percolation.open(2, 2);
//        percolation.open(2, 3);
//        percolation.open(3, 1);
        System.out.println(percolation.isFull(1, 2));

        percolation = new Percolation(n);
//        percolation.open(1, 2);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(1, 3);
        percolation.open(2, 1);
        percolation.open(2, 3);
        percolation.open(3, 1);

        percolation.open(2, 2);
//        percolation.open(2, 9);
//        percolation.open(2, 2);
//        percolation.open(2, 3);
//        percolation.open(3, 1);
        System.out.println(percolation.isFull(2, 2));
        System.out.println(percolation.opened);

    }
}
