import java.util.Arrays;
import java.util.Scanner;

public class L {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.fill(dp, 1);
        int ans = 0;
        int key = 0;
        int[] prev = new int[n];
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (ans < dp[i]) {
                ans = dp[i];
                key = i;
            }
        }
        System.out.println(dp[key]);

        if (dp[key] == 1) {
            answer.append(String.valueOf(arr[0]));
        } else {
            int[] keys = new int[ans];
            for (int i = 0; i < ans; i++) {
                keys[i] = arr[key];
                key = prev[key];
            }
            for (key = ans - 1; key >= 0; key--) {
                answer.append(keys[key]).append(' ');
            }
        }
        System.out.println(answer);
    }
}
