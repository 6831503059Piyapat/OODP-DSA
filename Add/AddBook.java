package Add;

import Except.WrongChoiceException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class AddBook {
    private static final String CHARACTERS = "0123456789";
    public boolean isDuplicate(String name,String Id) {
        boolean isFound = false;
        name = name.replace(" ", "").toLowerCase();
        
    try {
        BufferedReader br = new BufferedReader(new FileReader("books.txt"));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if(name.equals(data[0].replace(" ","").toLowerCase())){
                isFound = true;
                
            }
            else if(Id.equals(data[1])){
                isFound = true;
            }
        }
        
        br.close();
        
    } catch (Exception e) {
        System.out.println("Something went wrong.");
    }
    return isFound;
}
    public static String generateRandomString(int length,String valueGenre) {
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random(); 
        randomString.append(valueGenre.charAt(0));
        randomString.append(valueGenre.charAt(1));
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }
        
        return randomString.toString();
    }

    public String getGenre(int value) throws WrongChoiceException{
        switch (value) {
            case 1:
                return "Fiction";
            case 2:
                return "Non-Fiction";
            case 3:
                return "Entertainment";
            case 4:
                return "Educational";
            case 5:
                return "Historical";
            default:
                throw new WrongChoiceException("\u001b[1m Invaild Genre \u001b[0m \n");
            
            
        }
    }
    public void addUi() throws WrongChoiceException{
        System.out.println("===== ADD BOOK ===== \nGenres:\n");
        System.out.println("\u001B[34m 1.\u001B[0m Fiction");
        System.out.println("\u001B[34m 2.\u001B[0m Non-Fiction");
        System.out.println("\u001B[34m 3.\u001B[0m Entertainment");
        System.out.println("\u001B[34m 4.\u001B[0m Educational");
        System.out.println("\u001B[34m 5.\u001B[0m Historical \n");
        
        boolean isLoop = true;
        while (isLoop) {
            Scanner keyboard = new Scanner(System.in);
        
        try{
            // Enter book name : |
            System.out.print("Enter book name: ");
            String nameBook = keyboard.nextLine();
            if(nameBook.replace(" ", "") == "" ){
                throw new WrongChoiceException("\u001b[1m Book name cannot be empty. \u001b[0m \n");
            }
            // Enter genre (1-5): | 
            System.out.print("Enter genre (1-5): ");
            // 
            int genreBook = keyboard.nextInt();
            System.out.println("");
            String valueGenre = getGenre(genreBook);
            String ValueId = generateRandomString(5 ,valueGenre);

            System.out.println("\u001B[34m-------------------- \u001B[0m");
            System.out.println("Book ID: "+ValueId);
            System.out.println("Book Name: "+ nameBook);
            System.out.println("\u001B[34mGenre: " + valueGenre);
            System.out.println("-------------------- \u001B[0m \n");
            boolean isDup = isDuplicate(nameBook, ValueId);
            if(isDup){
                throw new WrongChoiceException("Already Found this book name");
            }
            System.out.print("Confirm book? (Y/N): ");
             char ConfirmCharacter = keyboard.next().charAt(0);
             char confirmCharacter = Character.toUpperCase(ConfirmCharacter);
            switch (confirmCharacter) {
                case 'Y':
                    try{
                       FileWriter fw = new FileWriter("books.txt", true);
                       BufferedWriter bw = new BufferedWriter(fw);
                       bw.write(nameBook + "," + valueGenre +"," + ValueId+",Available");
                       bw.newLine(); 
                       bw.close();
                       System.out.println();
                       System.out.println(nameBook + "have been added to the library! :D");
                       System.out.println();
                       isLoop = false;
                    }
                    catch(IOException e){
                            System.out.println("Adding Failed.");
                    }
                    break;
                case 'N':
                    System.out.println("");
                break;
            
                default:
                    throw new WrongChoiceException("\u001b[1m Invalid input. Please enter Y or N. \n");
            }
                    
               

        }catch(InputMismatchException e){
            System.out.println("\u001b[1m Invaild Genre \u001b[0m \n");
        }
        catch(WrongChoiceException e){
            System.out.println(e.getMessage());
        }
      }
    }
}
