package ALGO_LAB;

import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] sorted = new int[n];
        int k = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            sorted[i] = scanner.nextInt();
        }
        for (int i = 0; i < k; i++) {
            int left = scanner.nextInt();
            int right_b;
            int left_b;
            right_b = binSearchLesser(left, sorted);
            left_b = binSearchMaxer(left, sorted);

            int minmod;

            if (right_b == sorted.length) {
                right_b = sorted.length - 1;
            }
            if (left_b == -1) {
                left_b = 0;
            }

            if (left - sorted[left_b] == sorted[right_b] - left) {
                    minmod = sorted[left_b];
            } else {
                if (left - sorted[left_b] < sorted[right_b] - left) {
                    minmod = sorted[left_b];
                } else {
                    minmod = sorted[right_b];
                }
            }
            System.out.print(minmod + " ");
        }
    }

    public static int binSearchMaxer(int x, int[] arr) {
        int left = -1;
        int right = arr.length;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (arr[mid] <= x) {
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
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}