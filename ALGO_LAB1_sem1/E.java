package ALGO_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] sorted = merge_sort(arr);
        int k = scanner.nextInt();
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            int right_b;
            int left_b;
            if (sorted[sorted.length - 1] <= right) {
                right_b = sorted.length;
            } else {
                right_b = binSearchLesser(right, sorted);
            }
            if (sorted[0] >= left) {
                left_b = -1;
            } else {
                left_b = binSearchMaxer(left, sorted);
            }
            int count = right_b - left_b - 1;
            if (count >= 0) {
                ans[i] = count;
            } else {
                ans[i] = 0;
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.println(ans[i]);
        }

    }


    public static int binSearchMaxer(int x, int[] arr) {
        int left = -1;
        int right = arr.length;
        while (right - left > 1) {
            //System.out.print("hi");
            int mid = (left + right) / 2;
            if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }


    public static int binSearchLesser(int x, int[] arr) {
        int left = -1;
        int right = arr.length;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

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
