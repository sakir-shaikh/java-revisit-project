import ui.MainMenu;
import util.AppMessages;
import util.ConsoleColors;

public class Main {
    public static void main(String[] args) {
        try {
            ConsoleColors.printTitle(AppMessages.APP_TITLE);
            ConsoleColors.printInfo(AppMessages.WELCOME);
            
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
            
        } catch (Exception e) {
            ConsoleColors.printError(AppMessages.UNEXPECTED_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }
}