import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        int arrayLength = 0;
        int innerArrayLength;
        Scanner lineScanner = new Scanner(System.in);
        int[][] reversingNumbers = new int[10][10];
        int i = 0;
        int j;

        while (lineScanner.hasNextLine()) {
            j = 0;
            arrayLength++;
            if (arrayLength > reversingNumbers.length) {
                reversingNumbers = Arrays.copyOf(reversingNumbers, reversingNumbers.length * 2);
            }
            innerArrayLength = 0;
            Scanner stringRunner = new Scanner(lineScanner.nextLine());
            if (reversingNumbers[i] == null) {
                reversingNumbers[i] = new int[10];
            }
            while (stringRunner.hasNextInt()) {
                innerArrayLength++;
                if (innerArrayLength > reversingNumbers[i].length) {
                    reversingNumbers[i] = Arrays.copyOf(reversingNumbers[i], reversingNumbers[i].length * 2);
                }
                reversingNumbers[i][j] = stringRunner.nextInt();
                j++;
            }
            reversingNumbers[i] = Arrays.copyOf(reversingNumbers[i], innerArrayLength);
            i++;
        }
        reversingNumbers = Arrays.copyOf(reversingNumbers, arrayLength);

        for (i = reversingNumbers.length - 1; i >= 0; i--) {
            for (j = reversingNumbers[i].length - 1; j >= 0; j--) {
                System.out.print(reversingNumbers[i][j] + " ");
            }
            System.out.println();
        }

    }
}