
import java.util.Arrays;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] resultingArray = merge_sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(resultingArray[i] + " ");
        }

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
