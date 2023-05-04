import java.io.*;
import java.util.TreeMap;
import java.util.Map;

public class WordStatWords {

    public static boolean isAPartOfTheWord(char letter) {
        return (Character.isLetter(letter) || Character.getType(letter) == 20 || letter == '\'');
    }

    public static void main(String[] args) {
        TreeMap<String, Integer> foundWords = new TreeMap<>();

        try {
            BufferedReader incomingFile = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]), "UTF-8"));
            try {
                BufferedWriter outComingFile = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]), "UTF-8"));
                try {
                    String readingString;
                    while (true) {
                        readingString = incomingFile.readLine();
                        if (readingString == null) {
                            break;
                        }
                        boolean weAreReading = false;
                        StringBuilder readSoFar = new StringBuilder();
                        String completeWord;
                        for (int symbol = 0; symbol < readingString.length(); symbol++) {
                            if (isAPartOfTheWord(readingString.charAt(symbol)) &&
                                    symbol != readingString.length() - 1
                            ) {
                                readSoFar.append(readingString.charAt(symbol));
                                weAreReading = true;
                            } else {
                                if (weAreReading) {
                                    if ((symbol == readingString.length() - 1)
                                            && isAPartOfTheWord(readingString.charAt(symbol))) {
                                        readSoFar.append(readingString.charAt(symbol));
                                    }
                                    completeWord = readSoFar.toString().toLowerCase();
                                    if (foundWords.containsKey(completeWord)) {
                                        int temporary = foundWords.get(completeWord) + 1;
                                        foundWords.put(completeWord, temporary);
                                    } else {
                                        foundWords.put(completeWord, 1);
                                    }
                                    readSoFar = new StringBuilder();
                                    weAreReading = false;
                                }
                            }
                        }
                    }
                    for (Map.Entry<String, Integer> dictionary : foundWords.entrySet()) {
                        outComingFile.write(dictionary.getKey() + " " + dictionary.getValue() + "\n");
                    }
                } finally {
                    outComingFile.close();
                    incomingFile.close();
                }
            } catch (IOException wnfe) {
                System.out.println("а найденные слова запоминать собираешься?" + wnfe.getMessage());
            }
        } catch (IOException rnfe) {
            System.out.println("удачи прочитать текст из несуществующего файла" + rnfe.getMessage());
        }
    }
}
