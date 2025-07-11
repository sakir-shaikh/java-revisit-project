package util;

import java.util.Scanner;

public class InputUtils {
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColors.printError(AppMessages.PLEASE_ENTER_VALID_NUMBER);
            }
        }
    }

    public static long getLongInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColors.printError(AppMessages.PLEASE_ENTER_VALID_NUMBER);
            }
        }
    }

    public static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
} 