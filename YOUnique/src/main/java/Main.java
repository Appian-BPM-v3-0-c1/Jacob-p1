import com.revature.YOUnique.ui.MainMenu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    LocalDateTime mdy = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
    String today = mdy.format(formatter);


    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        mainMenu.startMenu();


    }
}
