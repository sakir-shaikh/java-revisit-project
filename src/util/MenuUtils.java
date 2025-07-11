package util;

public class MenuUtils {
    public static void printMenu(String title, String... options) {
        ConsoleColors.printTitle(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println();
    }
} 