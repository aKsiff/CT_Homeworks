package ALGO_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        boolean e = false;
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        a = merge_sort(a);
        b = merge_sort(b);
        int minimum = a[0] + b[0];
        int maximum = a[n - 1] + b[n - 1];
        while (maximum - minimum > 1) {
            int medium = (maximum + minimum) / 2;
            int walken = 0;
            for (int i = 0; i < a.length; i++) {
                int lower = -1;
                int higher = b.length;
                while (higher - lower > 1) {
                    int mid = (higher + lower) / 2;
                    if (medium - a[i] > b[mid]) {
                        lower = mid;
                    }
                    else {
                        if (medium - a[i] == b[mid]) {
                        e = true;
                        }
                        higher = mid;
                    }
                }
                walken += higher;
            }
            if (walken >= k) {
                maximum = medium;
            }
            else {
                minimum = medium;
            }
        }
        if (e) {
            maximum--;
        }
        System.out.println(maximum);
    }

    /*public static int itsIndex(int x, int[] arr, int[] brr) {
        int walken = 0;
        for (int i = 0; i < arr.length; i++) {
            int lower = -1;
            int higher = brr.length;
            while (higher - lower > 1) {
                int mid = (higher + lower) / 2;
                if (x - arr[i] > brr[mid]) {
                    lower = mid;

                }
                else {
                    higher = mid;
                }
            }
            walken += higher;
        }
        return walken;
}
     */

    public static int[] merge(int[] a1, int[] a2) {
        int[] result = new int[a1.length + a2.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < result.length; i++) {
            if (i1 == a1.length) {
                for (int j = i2; j < a2.length; j++) {
                    result[i] = a2[j];
                    i++;
                }
            } else if (i2 == a2.length) {
                for (int j = i1; j < a1.length; j++) {
                    result[i] = a1[j];
                    i++;
                }
            } else if (a1[i1] > a2[i2]) {
                result[i] = a2[i2];
                i2++;
            } else {
                result[i] = a1[i1];
                i1++;
            }
        }
        return result;
    }

    public static int[] merge_sort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int[] a1 = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] a2 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        a1 = merge_sort(a1);
        a2 = merge_sort(a2);
        return merge(a1, a2);
    }
}