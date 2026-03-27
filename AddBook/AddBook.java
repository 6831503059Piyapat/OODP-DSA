package AddBook;

import Book.BookS;
import java.util.*;
public class AddBook extends BookS {
    AddBook(){

    }
    public void addUi(){
        System.out.println("===== ADD BOOK ===== \nGenres:\n");
        System.out.println("\u001B[34m 1.\u001B[0m Fiction");
        System.out.println("\u001B[34m 2.\u001B[0m Non-Fiction");
        System.out.println("\u001B[34m 3.\u001B[0m Entertainment");
        System.out.println("\u001B[34m 4.\u001B[0m Educational");
        System.out.println("\u001B[34m 5.\u001B[0m Historical");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter book name: ");
        String nameBook = keyboard.nextLine();
        System.out.print("Enter genre (1-5): ");
        try{
            int genreBook = keyboard.nextInt();
            switch(nameBook){

        }
        }catch(InputMismatchException e){
            System.out.println("\u001b[1m Invaild Genre. \u001b[1m");
        }
        
    }
}
