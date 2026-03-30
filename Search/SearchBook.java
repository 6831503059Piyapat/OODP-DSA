package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Book.bookS;
public class SearchBook {

    public void searchUi() {
        
        Scanner scanner = new Scanner(System.in);
        boolean isLoop = true;
        while (isLoop) {
            System.out.print("Enter Book Name or ID: ");
            String input = scanner.nextLine().trim().toLowerCase(); // convert input to lowercase

            boolean isFound = false;

            try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) {
                        continue; 
                    }
                    bookS data = new bookS(parts[0], parts[1], parts[2], parts[3].equalsIgnoreCase("Available") ? true : false);
                    String name = data.getTitle().trim().toLowerCase(); // convert book name to lowercase
                    String genre = data.getGenre().trim();
                    String id = data.getId().trim().toLowerCase(); // convert ID to lowercase
                    if (input.equals(name) || input.equals(id)) {
                        isFound = true;
                        System.out.println("\n---------------------------------------");
                        System.out.println("Book ID: " + data.getId());
                        System.out.println("Book Name: " + data.getTitle());
                        System.out.println("Genre: " + genre);
                        System.out.println("Available: " + (data.isBorrow() ?"Available" : "Not Available"));
                        System.out.println("---------------------------------------\n");
                        break;
                    }
                }

                if (!isFound) {
                    System.out.println("\nBook not found :(\n");
                }

            } catch (IOException e) {
                System.out.println("Error when reading the book file.");
            }

            // go to main
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Go back to main menu? (Y/N): ");
                String choiceInput = scanner.nextLine().trim().toUpperCase();
                if (choiceInput.length() > 0) {
                    char choice = choiceInput.charAt(0);
                    switch (choice) {
                        case 'Y':
                            isLoop = false;
                            validChoice = true;
                            break;
                        case 'N':
                            validChoice = true;
                            break;
                        default:
                            System.out.println("Invalid input. Please enter Y or N.");
                            break;
                    }
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
        }
        scanner.close();
    }
}