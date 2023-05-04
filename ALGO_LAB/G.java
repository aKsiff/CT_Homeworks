package ALGO_LAB;

import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println(binary_search(n, x, y));
    }

    public static int myMax(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static int myMin(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static int binary_search(int n, int x, int y) {
        int left = 0;
        int right = (n - 1) * myMax(x, y);
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (mid / x + mid / y < n - 1) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right + myMin(x, y);
    }
}
