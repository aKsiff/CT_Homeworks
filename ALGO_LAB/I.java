package ALGO_LAB;

import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double pv = scanner.nextInt();
        double pf = scanner.nextInt();
        double k = Double.parseDouble(scanner.next());
        double ans = ternarySearch(pv, pf, k);
        System.out.println(ans);
    }
    public static double f(double x, double vp, double vf, double k) {
        return (Math.sqrt(Math.pow((k - 1.0), 2.0) + Math.pow(x, 2.0)) / vp)
                + (Math.sqrt(Math.pow(k, 2.0) + Math.pow((x - 1.0), 2.0)) / vf);
    }
    public static double ternarySearch(double vp, double vf, double k) {
        double right = 1.0;
        double left = 0.0;
        double eps = 0.00001;
        while (right - left > eps) {
            double a = (left * 2.0 + right) / 3.0;
            double b = (left + right * 2.0) / 3.0;
            if (f(a, vp, vf, k) < f(b, vp, vf, k)) {
                right = b;
            } else {
                left = a;
            }
        }
        return (right + left) / 2;
    }
}
