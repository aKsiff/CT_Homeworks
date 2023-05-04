package ALGO_LAB;

import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n = scanner.nextDouble();
        double x = Math.ceil(binSearchLesser(n, 0, n) * 1000000) / 1000000;
        System.out.println(x);

    }

    public static double binSearchLesser(double x, double left, double right) {
        while (right - left > 0.0000001) {
            double mid = (left + right) / 2;
            if (Math.pow(mid, 2) + Math.pow(mid, 0.5) >= x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return (right + left) / 2;
    }
}