package ALGO_LAB;

import java.util.Scanner;

public class K {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        long sum = 1;
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }
        long min_length = binary_search(arr, sum, k);
        System.out.println(min_length);
    }

    public static boolean weCanHandle(long length, int k, int[] arr) {
        long c_length = 0;
        int c_k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > length) {
                return false;
            }
            if (length >= c_length + arr[i]) {
                c_length += arr[i];
            }
            else {
                c_k++;
                c_length = arr[i];
            }
        }
        return k >= c_k + 1;
    }

    public static long binary_search(int[] arr, long summ, int k) {
        long right = summ;
        long left = 0;
        while (right - left > 1) {
            long mid = (left + right) / 2;
            if (weCanHandle(mid, k, arr)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
