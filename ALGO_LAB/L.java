package ALGO_LAB;

import java.util.Scanner;

public class L {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long k = scanner.nextLong();
        long left = 1;
        long right = (long)n * n + 1;
        while (right > left) {
            long mid = (right + left) / 2;
            if (kIsWhere(mid, n) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left - 1);
    }

    public static long kIsWhere(long k, int size) {
        long count = 0;
        for (int i = 1; i <= size; ++i) {
            count += Math.min(size, ((k - 1) / i));
        }
        return count;
    }
}









    /*
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] squares = new int[n];
        for (int i = 0; i<n; i++) {
            squares[i] = (i+1)*(i+1);
        }

        int low = 0;
        int high = 0;



        int went = low*low + 2*(low - 1);
        System.out.println(Arrays.toString(squares) + " " + low + " " + high);
        int counter = low*low + 2*(low - 1);
        for (int i = 1; i <= high; i++) {
            int x = high * i;

        }
        System.out.println(counter);
    }

    public static int findX(int x, int n, int arr) {
        int base = 0;
        int max = n*n;
        while (max - base > 1) {
            int mi = (max + base) / 2;

        }
        return 0;
    }


    public static int binSearchMinSquare(int x, int[] arr) {
        int left = -1;
        int right = arr.length;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }


    public static int binSearchMaxSquare(int x, int[] arr) {
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

    public static boolean kIsThere(int low, int high, int k) {
        return false;
        }
    }
}


     */