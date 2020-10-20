package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static String createCode(int limit, String alphabet) {
        if (limit > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + limit +
                    " because there aren't enough unique digits.");
            System.exit(0);
        }
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        char[] letters = alphabet.toCharArray();
        while (code.length() != limit) {
            char letter = letters[random.nextInt(letters.length)];
            if (!(code.toString().contains(String.valueOf(letter)))) {
                code.append(letter);
            }
        }
        return code.toString();
    }

    private static String getStarlets(int num) {
        char[] arr = new char[num];
        Arrays.fill(arr, '*');
        return new String(arr);
    }

    private static int[] howManyBullsAndCows(String code, String response,
                                             int codeLength) {
        int bulls = 0;
        int cows = 0;
        char[] codes = code.toCharArray();
        char[] responses = response.toCharArray();
        for (int i = 0; i < codeLength; i++) {
            if (codes[i] == responses[i]) {
                bulls++;
            } else if (code.contains(Character.toString(response.charAt(i)))) {
                cows++;
            }
        }
        return new int[]{bulls, cows};
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String GRADE = "Grade: ";
        final String TURN = "Turn ";
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder blls;
        StringBuilder cws;
        final String CONGRATZ = "Congratulations! You guessed the secret code.";
        int bulls;
        int cows;
        int i = 0;
        int codeLength = 0;
        String codeLength1 = "";

        System.out.println("Input the length of the secret code:");
        try {
            codeLength1 = scanner.nextLine();
            codeLength = Integer.parseInt(codeLength1);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + codeLength1 + " isn't a valid number.");
            System.exit(0);
        }
        if (codeLength <= 0) {
            System.out.println("error");
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = Integer.parseInt(scanner.nextLine());
        if (possibleSymbols < codeLength) {
            System.out.println("Error: it's not possible to generate a code with a length of " +
                    codeLength + " with " + possibleSymbols + " unique symbols.");
            System.exit(0);
        }
        if (possibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
        alphabet = alphabet.substring(0, possibleSymbols);
        System.out.println("The secret is prepared: " + getStarlets(codeLength) + " (0-9, a-"
                + alphabet.charAt(alphabet.length() - 1) + ").");
        String code = createCode(codeLength, alphabet);
        System.out.println("Okay, let's start a game!");

        while (true) {
            i++;
            System.out.println(TURN + i + ":");
            String response = scanner.nextLine();
            if (response.equals(code)) {
                System.out.println(GRADE + codeLength + " bulls\n"
                        + CONGRATZ);
                break;
            } else {
                int[] bullsAndCows = howManyBullsAndCows(code, response,
                        codeLength);
                bulls = bullsAndCows[0];
                cows = bullsAndCows[1];
                blls = new StringBuilder("bull");
                cws = new StringBuilder("cow");
                System.out.print(GRADE);
                if (bulls > 1) {
                    blls.append("s");
                }
                if (cows > 1) {
                    cws.append("s");
                }
                System.out.print(bulls + " " + blls.toString() + " and "
                        + cows + " " + cws.toString() + "\n");
            }
        }
    }
}
