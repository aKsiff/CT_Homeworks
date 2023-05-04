public class Sum {
    public static void main(String[] args) {
        StringBuilder transitor = new StringBuilder();

        for (int argsIndex = 0; argsIndex < args.length; argsIndex++) {
            StringBuilder currentArgsElement = new StringBuilder(args[argsIndex]);
            transitor.append(' ');
            transitor.append(currentArgsElement);
            transitor.append(' ');
        }

        String singleStringArgs = new String(transitor);
        int ourSumm = 0;
        boolean weAreCounting = false;
        StringBuilder currentUnformatedNumber = new StringBuilder();

        for (int symbol = 0; symbol < singleStringArgs.length(); symbol++) {
            if (!(Character.isWhitespace(singleStringArgs.charAt(symbol)))) {
                currentUnformatedNumber.append(singleStringArgs.charAt(symbol));
                weAreCounting = true;
            } else {
                if (weAreCounting) {
                    weAreCounting = false;
                    ourSumm += Integer.valueOf(currentUnformatedNumber.toString());
                    currentUnformatedNumber = new StringBuilder();
                }
            }
        }

        System.out.println(ourSumm);

    }
}