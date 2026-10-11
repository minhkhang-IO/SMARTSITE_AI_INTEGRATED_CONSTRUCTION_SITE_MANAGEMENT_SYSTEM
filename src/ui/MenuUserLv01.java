package ui;

import static ui.Draft.pause;
import smartsite.Main;
public class MenuUserLv01 extends MenuUserLv02{

   public MenuUserLv01() {
        super();
    }

    
   @Override
    public void displayOption() {
        super.displayOption();
        System.out.println("06. Show all history attendance");
        System.out.println("07. Add new person into system manager");
        System.out.println("08. Find person by ID");
        System.out.println("09. Sort the list by name");
        System.out.println("10. Delete person by ID");
        System.out.println("11. Display all the list");
    }
    @Override
    public void chooseOption(int choice) {
        switch (choice) {
            case(6):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case(7):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case(8):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case(9):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case(10):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case(11):
                System.out.println("Function not complete yet!");
                pause();
                break;
            default:
                super.chooseOption(choice);
                break;
        }
    }
}
