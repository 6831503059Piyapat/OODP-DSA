
import java.util.*;
import Except.WrongChoiceException;
import Add.AddBook;
import Search.SearchBook;
import Show.ShowAllBook;
import Return.ReturnBook;
import Borrow.BorrowBook;
import required.recompo;
public class Cors implements recompo{
    @Override
    public void showAllUi() {
        ShowAllBook show = new ShowAllBook();
        show.showAllUI();
    }

    @Override
    public void searchUi(Scanner key) {
        SearchBook search = new SearchBook();
        search.searchUi(key);
    }
    
    @Override
    public void ReturnMethod() {
        ReturnBook returnBook = new ReturnBook();
        returnBook.ReturnUI();
    }
    
    @Override
    public void BorrowUi() {
        BorrowBook borrow = new BorrowBook();
        borrow.BorrowUI();
    }
    
    @Override
    public void addUi() throws WrongChoiceException{
        AddBook add = new AddBook();
        add.addUi();
    }
    
    
    public static void main(String[] args) throws WrongChoiceException {
        // Create single Scanner instance for entire program
        try (Scanner keyboard = new Scanner(System.in)) {
            boolean isExit = false;
            while(!isExit){
                // Main Menu
            System.out.println("===== Library Management System ====="+"\n");
            System.out.println("\u001B[34m \u001b[1m 1. \u001b[1m \u001B[0m Add Book");
            System.out.println("\u001B[34m \u001b[1m 2. \u001b[1m \u001B[0m Show All Books");
            System.out.println("\u001B[34m \u001b[1m 3. \u001b[1m \u001B[0m Search Book");
            System.out.println("\u001B[34m \u001b[1m 4. \u001b[1m \u001B[0m Borrow Book");
            System.out.println("\u001B[34m \u001b[1m 5. \u001b[1m \u001B[0m Return Book");
            System.out.println("\u001B[34m \u001b[1m 0. \u001b[1m \u001B[0m Exit \n");
            System.out.print("Enter Choice : ");
            int valueChoice;
            try{
                valueChoice = keyboard.nextInt();
            }catch(Exception e){
                System.out.println("\u001b[1m Invalid input. Please enter a valid choice. \u001b[0m \n");
                keyboard.nextLine(); // Consume invalid input
                continue;
            }
            keyboard.nextLine(); // Consume newline
            System.out.println("");
            Cors cors = new Cors();
        switch(valueChoice){
            // ADD BOOK
            case 1:
                System.out.print("\033[H\033[2J");
               
                cors.addUi();
                
            break;
            // SHOW ALL BOOKS
            case 2:
                System.out.print("\033[H\033[2J");
               
                cors.showAllUi();
                break;
            // SEARCH BOOK
            case 3:
                System.out.print("\033[H\033[2J");
                
                cors.searchUi(keyboard);
                break;
                // BORROW BOOK
            case 4:
                System.out.print("\033[H\033[2J");
               
                cors.BorrowUi();
                break;
                // RETURN BOOK
            case 5:
                System.out.print("\033[H\033[2J");
                
                cors.ReturnMethod();
            break;
                // Exit
            case 0:
                System.out.print("\033[H\033[2J");
              
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
}
