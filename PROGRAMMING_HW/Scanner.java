import java.io.*;

public class Scanner {
    private boolean thereWillBaALine = false;
    private Reader inputScanner;
    private char buffer[] = new char[1024];
    private int buffLength = 0;
    private int bufPosition = 0;
    private int separatorsAchieved = 0;
    private int number;
    private long octal;
    private int howMuchWeSciped = 0;
    private StringBuilder bLine = new StringBuilder();
    private String line = null;

    public Scanner(File fileName) {
        try {
            inputScanner = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        } catch (IOException e) {
            System.out.println("file not found " + e.getMessage());
        }
    }

    public Scanner(String singleString) {
        inputScanner = new StringReader(singleString);
    }

    public Scanner(InputStream systemIn) {
        inputScanner = new InputStreamReader(systemIn);
    }

    public String nextLine() {
        try {
            while (line == null) {
                if (bufPosition >= buffLength) {
                    buffLength = inputScanner.read(buffer);
                    bufPosition = 0;
                    if (buffLength == -1) {
                        if (bufPosition != 0) {
                            line = bLine.toString();

                        }
                        break;
                    }
                }
/*              switch (buffer[i]) {
                    case ('\r'):
                        separatorsAchieved = 1;
                        lineFound = true;
                        break;
                    case ('\n'):
                        if (separatorsAchieved == 1) {
                            separatorsAchieved = 0;
                            lineFound = false;
                        } else {
                            lineFound = true;
                        }
                        break;
                } */
                if (buffer[bufPosition] == '\n' || buffer[bufPosition] == System.lineSeparator().charAt(System.lineSeparator().length() - 1)) {
                    line = bLine.toString();
                    bufPosition++;
                    thereWillBaALine = true;
                    break;
                }

                bLine.append(buffer[bufPosition]);
                bufPosition++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        bLine = new StringBuilder();
        String trans = line;
        line = null;
        return trans;
    }

    public boolean hasNextLine() {
        try {
            while (true) {
                if (bufPosition == 0 && buffLength == 0) {
                    thereWillBaALine = true;
                    break;
                }
                if (bufPosition >= buffLength) {
                    buffLength = inputScanner.read(buffer);
                    bufPosition = 0;
                    if (buffLength == -1) {
                        thereWillBaALine = false;
                        if (bufPosition != 0) {
                            line = bLine.toString();
                        }
                        break;

                    }
                }
                if (thereWillBaALine) {
                    break;
                }

                if (buffer[bufPosition] == '\n' || buffer[bufPosition] == System.lineSeparator().charAt(System.lineSeparator().length() - 1)) {
                    line = bLine.toString();
                    thereWillBaALine = true;
                    bufPosition++;
                }

                thereWillBaALine = true;
                bLine.append(buffer[bufPosition]);
                bufPosition++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean trans = thereWillBaALine;
        thereWillBaALine = false;
        return trans;

    }

    public int nextInt() {
        boolean weAreCounting = false;
        boolean weHaveCounted = false;
        StringBuilder currentUnformatedNumber = new StringBuilder();
        number = 0;

        try {
            while (true) {
                if (bufPosition >= buffLength) {
                    buffLength = inputScanner.read(buffer);
                    bufPosition = 0;
                    if (buffLength == -1) {
                        if (weAreCounting) {
                            number = Integer.valueOf(currentUnformatedNumber.toString());
                        }
                        break;
                    }
                }

                if (Character.isDigit(buffer[bufPosition]) || buffer[bufPosition] == '-') {
                    currentUnformatedNumber.append(buffer[bufPosition]);
                    weAreCounting = true;
                } else {
                    if (weAreCounting) {
                        weAreCounting = false;
                        number = Integer.valueOf(currentUnformatedNumber.toString());
                        weHaveCounted = true;
                        currentUnformatedNumber = new StringBuilder();
                    }
                    if (buffer[bufPosition] == '\n' || buffer[bufPosition] == System.lineSeparator().charAt(System.lineSeparator().length() - 1)) {
                        if (weAreCounting) {
                            number = Integer.valueOf(currentUnformatedNumber.toString());
                            weHaveCounted = true;
                            bufPosition++;
                            break;
                        } else {
                            bufPosition++;
                        }
                    }
                }

                if (weHaveCounted) {
                    break;
                }
                bufPosition++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return number;

    }

    public boolean hasNextInt() {
        boolean soIsThere = false;
        try {
            while (true) {
                if (bufPosition >= buffLength) {
                    buffLength = inputScanner.read(buffer);
                    bufPosition = 0;
                    if (buffLength == -1) {
                        break;
                    }
                }
                if (Character.isDigit(buffer[bufPosition]) || buffer[bufPosition] == '-') {
                    soIsThere = true;
                    break;
                }
                bufPosition++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return soIsThere;

    }

    public long nextOct() {
        boolean weAreCounting = false;
        boolean weHaveCounted = false;
        StringBuilder currentUnformatedNumber = new StringBuilder();
        octal = 0;

        try {
            while (true) {
                if (bufPosition >= buffLength) {
                    buffLength = inputScanner.read(buffer);
                    bufPosition = 0;
                    if (buffLength == -1) {
                        if (weAreCounting) {
                            octal = Long.parseUnsignedLong(currentUnformatedNumber.toString());

                        }
                        break;
                    }
                }

                if (Character.isDigit(buffer[bufPosition]) || buffer[bufPosition] == '-') {
                    currentUnformatedNumber.append(buffer[bufPosition]);
                    weAreCounting = true;
                } else {
                    if (weAreCounting) {
                        weAreCounting = false;
                        octal = Long.parseUnsignedLong(currentUnformatedNumber.toString());
                        weHaveCounted = true;
                        currentUnformatedNumber = new StringBuilder();
                    }

                    if (buffer[bufPosition] == '\n' || buffer[bufPosition] == System.lineSeparator().charAt(System.lineSeparator().length() - 1)) {
                        if (weAreCounting) {
                            octal = Long.parseUnsignedLong(currentUnformatedNumber.toString());
                            weHaveCounted = true;
                            bufPosition++;
                            break;
                        } else {
                            bufPosition++;
                        }
                    }
                }

                if (weHaveCounted) {
                    break;
                }
                bufPosition++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return octal;

    }

    public boolean hasNextOct() {
        boolean soIsThere = false;
        try {
            while (true) {
                if (bufPosition >= buffLength) {
                    buffLength = inputScanner.read(buffer);
                    bufPosition = 0;
                    if (buffLength == -1) {
                        break;
                    }
                }
                if (Character.isDigit(buffer[bufPosition]) || buffer[bufPosition] == '-') {
                    soIsThere = true;
                    break;
                }
                bufPosition++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return soIsThere;
    }
}
/*
if (buffer[i] == '\r') {
                            separatorsAchieved = true;
                        }
                        if (buffer[i] == '\n') {
                            if (!separatorsAchieved) {
                                lineIndex += 1;
                                wordIndex = 0;
                                separatorsAchieved = false;
                            }
                        }


 */