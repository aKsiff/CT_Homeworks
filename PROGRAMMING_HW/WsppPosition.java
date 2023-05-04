import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

public class WsppPosition {
    public static void main(String[] args) throws IOException {

        LinkedHashMap<String, int[][]> foundWords = new LinkedHashMap<>();
        int wordIndex;
        int lineIndex = 0;
        int[][] transitor;

        try {
            FileInputStream incomingFile = new FileInputStream(args[0]);
            Scanner scanner = new Scanner(incomingFile);
            BufferedWriter outComingFile = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8));
            try {
                boolean weAreReading = false;
                StringBuilder readSoFar = new StringBuilder();
                String completeWord;
                while (scanner.hasNextLine()) {
                    String lineRunner = scanner.nextLine();
                    wordIndex = 0;
                    lineIndex++;
                    for (int i = 0; i < lineRunner.length(); i++) {
                        if (isAPartOfTheWord(lineRunner.charAt(i))) {
                            readSoFar.append(lineRunner.charAt(i));
                            weAreReading = true;
                        } else {
                            if (weAreReading) {
                                wordIndex++;
                                completeWord = readSoFar.toString().toLowerCase();

                                int[][] base = new int[1][2];
                                transitor = foundWords.getOrDefault(completeWord, base);
                                transitor[0][0]++;
                                if (transitor[0][0] >= transitor.length - 1) {
                                    transitor = Arrays.copyOf(transitor, transitor.length*2);
                                }
                                transitor[transitor[0][0]] = new int[2];
                                transitor[transitor[0][0]][0] = lineIndex;
                                transitor[transitor[0][0]][1] = wordIndex;

                                foundWords.put(completeWord, transitor);
                                readSoFar.setLength(0);
                                weAreReading = false;
                            }
                        }
                    }

                    if (weAreReading) {
                        wordIndex++;
                                completeWord = readSoFar.toString().toLowerCase();
                                int[][] base = new int[1][2];
                                transitor = foundWords.getOrDefault(completeWord, base);
                                transitor[0][0]++;
                                transitor = Arrays.copyOf(transitor, transitor.length + 1);

                                transitor[transitor.length - 1] = new int[2];
                                transitor[transitor.length - 1][0] = lineIndex;
                                transitor[transitor.length - 1][1] = wordIndex;

                                foundWords.put(completeWord, transitor);
                                readSoFar.setLength(0);
                                weAreReading = false;
                    }



                }

                for (Map.Entry<String, int[][]> dictionary : foundWords.entrySet()) {
                    outComingFile.write(dictionary.getKey() + " " + arrayToString(dictionary.getValue()) + '\n');
                }

            } finally {
                outComingFile.close();
                incomingFile.close();
            }
        } catch (IOException wnfe) {
            System.out.println("а найденные слова запоминать собираешься? " + wnfe.getMessage());
        }
    }

    private static String arrayToString(int[][] primitiveArray) {
        StringBuilder stringedArray = new StringBuilder();
        stringedArray.append(primitiveArray[0][0]+primitiveArray[0][1]);
        for (int i = 1; i <= primitiveArray[0][0]; i++) {
            stringedArray.append(' ');
            stringedArray.append(primitiveArray[i][0]);
            stringedArray.append(":");
            stringedArray.append(primitiveArray[i][1]);
        }
        return stringedArray.toString();
    }

    private static boolean isAPartOfTheWord(char letter) {
        return (Character.isLetter(letter) || Character.getType(letter) == Character.DASH_PUNCTUATION || letter == '\'');
    }
}
