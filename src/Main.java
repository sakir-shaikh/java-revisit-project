import ui.MainMenu;
import util.ConsoleColors;

public class Main {
    public static void main(String[] args) {
        try {
            ConsoleColors.printTitle("WELCOME TO JOB PORTAL SYSTEM");
            ConsoleColors.printInfo("A simple job portal built with plain Java and JSON database");
            
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
            
        } catch (Exception e) {
            ConsoleColors.printError("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}