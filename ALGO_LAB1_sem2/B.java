import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        long[] arr1 = new long[n];
        StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            arr1[i] = reader.nextLong();
        }
        Offcut_tree tree = new Offcut_tree(arr1);
        tree.build(arr1);
        String request;
        while (reader.hasNext()) {
            request = reader.next();
            if (request.equals("set")) {
                int i = reader.nextInt() - 1;
                long x = reader.nextInt();
                tree.set(i, x);
                //System.out.println(tree.treeToString());
            } else {
                int l = reader.nextInt() - 1;
                int r = reader.nextInt();
                ans.append(tree.min(l, r)).append("\n");
            }
        }
        System.out.println(ans.toString());
    }
}

class Offcut_tree {

    private long[] tree;

    private long[] arr;

    public Offcut_tree(long[] arr) {
        this.arr = arr;
        this.tree = new long[arr.length * 4];
    }

    public void build(long[] arr) {
        build_child(arr, 0, 0, arr.length);
    }

    public void build_child(long[] arr, int v, int vl, int vr) {
        if (vl + 1 == vr) {
            tree[v] = arr[vl];
            return;
        }
        int vm = (vl + vr) / 2;
        build_child(arr, 2 * v + 1, vl, vm);
        build_child(arr, 2 * v + 2, vm, vr);
        tree[v] = Math.min(tree[2 * v + 1], tree[2 * v + 2]);
    }

    public String treeToString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < tree.length; i++) {
            ans.append(tree[i]).append(" ");
        }
        return ans.toString();
    }

    public void set(int i, long x) {
        set_impl(x, 0, 0, arr.length, i, i+1);
    }

    private void set_impl( long x, int v, int vl, int vr, int ql, int qr) {
        if (qr <= vl || vr <= ql) {
            return;
        }
        if (ql <= vl && vr <= qr) {
            tree[v] = x;
            return;
        }
        int vm = (vl + vr) / 2;
        set_impl(x, v*2+1, vl, vm, ql, qr);
        set_impl(x, v*2+2, vm, vr, ql, qr);
        tree[v] = Math.min(tree[v*2+1], tree[v*2+2]);

    }

    public long min(int l, int r) {
        return min_impl(0, 0, arr.length, l, r);
    }

    private long min_impl(int v, int vl, int vr, int ql, int qr) {
        if (qr <= vl || vr <= ql) {
            return Long.MAX_VALUE;
        }
        if (ql <= vl && vr <= qr) {
            return tree[v];
        }

        int vm = (vl + vr) / 2;
        long left = min_impl(2 * v + 1, vl, vm, ql, qr);
        long right = min_impl(2 * v + 2, vm, vr, ql, qr);
        return Math.min(left, right);
    }
}