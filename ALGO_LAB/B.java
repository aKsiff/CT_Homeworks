import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        int[] result = new int[101];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int x = 0; x < n; x++) {
            result[scanner.nextInt()]++;
        }
        for (int i = 0; i < result.length; i++) {
            while (result[i] != 0) {
                System.out.print(i + " ");
                result[i]--;
            }

        }
    }
}
