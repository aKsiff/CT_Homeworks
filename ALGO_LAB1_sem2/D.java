import java.util.Arrays;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        long[] arr1 = new long[n];
        StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            arr1[i] = reader.nextLong();
        }
        Offcut_tree_D tree = new Offcut_tree_D(arr1);
        tree.build(arr1);
        String request;
        int r, l;
        while (reader.hasNext()) {
            request = reader.next();
            if (request.equals("set")) {
                l = reader.nextInt() - 1;
                r = reader.nextInt();
                long x = reader.nextInt();
                tree.set(l, r, x);
                //System.out.println(tree.treeToString());
            }
            if (request.equals("min")) {
                l = reader.nextInt() - 1;
                r = reader.nextInt();
                ans.append(tree.min(l, r)).append("\n");
            }
            if (request.equals("add")) {
                l = reader.nextInt() - 1;
                r = reader.nextInt();
                long x = reader.nextInt();
                tree.add(l, r, x);
                //System.out.println(tree.treeToString());
            }
        }
        System.out.println(ans.toString());
    }
}


class Offcut_tree_D {

    private long[][] tree;

    private long[] arr;

    public Offcut_tree_D(long[] arr) {
        this.arr = arr;
        this.tree = new long[arr.length * 4][3];
    }

    public void build(long[] arr) {
        build_child(arr, 0, 0, arr.length);
    }

    public void build_child(long[] arr, int v, int vl, int vr) {
        if (vl + 1 == vr) {
            tree[v][0] = arr[vl];
            tree[v][2] = Long.MIN_VALUE;
            return;
        }
        int vm = (vl + vr) / 2;
        tree[v][2] = Long.MIN_VALUE;
        build_child(arr, 2 * v + 1, vl, vm);
        build_child(arr, 2 * v + 2, vm, vr);
        tree[v][0] = Math.min(tree[2 * v + 1][0], tree[2 * v + 2][0]);
    }

    public String treeToString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < tree.length; i++) {
            ans.append(Arrays.toString(tree[i])).append(" ");
        }
        return ans.toString();
    }

    public void set(int i, int j, long x) {
        set_impl(x, 0, 0, arr.length, i, j);
    }

    private void set_impl(long x, int v, int vl, int vr, int ql, int qr) {
        push_add(v, vl, vr);
        push_set(v, vl, vr);
        if (qr <= vl || vr <= ql) {
            return;
        }
        if (ql <= vl && vr <= qr) {
            tree[v][2] = x;
            push_add(v, vl, vr);
            push_set(v, vl, vr);
            //push_set(v, vl, vr);
            /// здесь нужно что то сделать ?????????
            return;
        }
        int vm = (vl + vr) / 2;
        set_impl(x, v * 2 + 1, vl, vm, ql, qr);
        set_impl(x, v * 2 + 2, vm, vr, ql, qr);
        tree[v][0] = Math.min(tree[v * 2 + 1][0], tree[v * 2 + 2][0]);
    }

    public void add(int r, int l, long x) {
        add_impl(x, 0, 0, arr.length, r, l);
    }

    private void add_impl(long x, int v, int vl, int vr, int ql, int qr) {
        push_add(v, vl, vr);
        push_set(v, vl, vr);
        if (qr <= vl || vr <= ql) {
            return;
        }
        if (ql <= vl && vr <= qr) {
            tree[v][1] += x;
            push_add(v, vl, vr);
            push_set(v, vl, vr);
            /// здесь нужно что то сделать ?????????
            return;
        }
        int vm = (vl + vr) / 2;
        add_impl(x, v * 2 + 1, vl, vm, ql, qr);
        add_impl(x, v * 2 + 2, vm, vr, ql, qr);
        tree[v][0] = Math.min(tree[v * 2 + 1][0], tree[v * 2 + 2][0]);
    }

    public long min(int l, int r) {
        return min_impl(0, 0, arr.length, l, r);
    }

    private long min_impl(int v, int vl, int vr, int ql, int qr) {
        push_add(v, vl, vr);
        push_set(v, vl, vr);
        if (qr <= vl || vr <= ql) {
            return Long.MAX_VALUE;
        }
        if (ql <= vl && vr <= qr) {
            push_add(v, vl, vr);
            return tree[v][0];
        }
        int vm = (vl + vr) / 2;
        long left = min_impl(2 * v + 1, vl, vm, ql, qr);
        long right = min_impl(2 * v + 2, vm, vr, ql, qr);
        return Math.min(left, right);
    }

    private void push_add(int v, int l, int r) {
        if (tree[v][1] != 0 && (l + 1 != r)) {
            tree[v][0] += tree[v][1];
            push_set(2 * v + 1, l, (l + r) / 2);
            push_set(2 * v + 2, (l + r) / 2, r);
            tree[2 * v + 1][1] += tree[v][1];
            tree[2 * v + 2][1] += tree[v][1];
            tree[v][1] = 0;
        }
        if (l + 1 == r) {
            tree[v][0] += tree[v][1];
            tree[v][1] = 0;
        }
    }

    private void push_set(int v, int l, int r) {
        if (tree[v][2] != Long.MIN_VALUE && (l + 1 != r)) {
            tree[v][0] = tree[v][2];
            tree[v][1] = 0;
            tree[2 * v + 1][1] = 0;
            tree[2 * v + 2][1] = 0;
            tree[2 * v + 1][2] = tree[v][2];
            tree[2 * v + 2][2] = tree[v][2];
            tree[v][2] = Long.MIN_VALUE;
        }
        if (tree[v][2] != Long.MIN_VALUE && l + 1 == r) {
            tree[v][0] = tree[v][2];
            tree[v][2] = Long.MIN_VALUE;
        }
        // если лист присвоить значение
    }
}
