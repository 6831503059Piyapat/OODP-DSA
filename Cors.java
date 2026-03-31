
import java.util.*;
import Except.WrongChoiceException;
import Add.AddBook;
import Search.SearchBook;
import Borrow.BorrowBook;
public class Cors {
    public static void main(String[] args) throws WrongChoiceException {
        boolean isExit = false;
        while(!isExit){
        System.out.println("===== Library Management System ====="+"\n");
        System.out.println("\u001B[34m \u001b[1m 1. \u001b[1m \u001B[0m Add Book");
        System.out.println("\u001B[34m \u001b[1m 2. \u001b[1m \u001B[0m Show All Books");
        System.out.println("\u001B[34m \u001b[1m 3. \u001b[1m \u001B[0m Search Book");
        System.out.println("\u001B[34m \u001b[1m 4. \u001b[1m \u001B[0m Borrow Book");
        System.out.println("\u001B[34m \u001b[1m 5. \u001b[1m \u001B[0m Return Book");
        System.out.println("\u001B[34m \u001b[1m 0. \u001b[1m \u001B[0m Exit \n");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Choice : ");
        int valueChoice = keyboard.nextInt();
        System.out.println("");
        switch(valueChoice){
            case 1:
               AddBook add = new AddBook();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                add.addUi();
                
            break;

            case 3:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                
                SearchBook search = new SearchBook();
                search.searchUi();
                break;
                case 4:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                BorrowBook borrow = new BorrowBook();
                borrow.BorrowUI();
                break;
            case 0:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                isExit = true;
                System.out.println("Exiting the program. Goodbye!");
                break;
            default:
                System.out.println("\u001b[1m Invalid Choice. Please try again. \u001b[0m \n");
                break;
        }
    }
    }
}
