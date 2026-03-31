import Except.WrongChoiceException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Borrow {
    

    public void BorrowUI{
        boolean isLoop = ture;
        String genres ;
        while (isLoop){
            System.out.println("===== Borrow Book =====");
            System.out.println("Genres");
            System.out.println("1.Fiction");
            System.out.println("2.Non-Fiction");
            System.out.println("3.Entertainment");
            System.out.println("4.Educational");
            System.out.println("5.Historical");
            System.out.println();
            System.out.println("Select Genre (1-5): ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    genres = "Fiction";
                    BorrowMedtod();
                    break;
                case 2:
                    genres = "Non-Fiction";
                    BorrowMedtod();
                    break;
                case 3:
                    genres = "Entertainment";
                    BorrowMedtod();
                    break;
                case 4:
                    genres = "Educational";
                    BorrowMedtod();
                    break;
                case 5:
                    genres = "Historical";
                    BorrowMedtod();
                    break;
                default:
                    System.out.println("Invalid Genre.");
            }
        }
    }

    public void BorrowMedtod(String title , String genre , String id , boolean isAvailable){
        List<String> fileContent = new ArrayList<>();
        boolean isFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            System.out.println("\n------- "+ genres +" -------");
            while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) {
                        continue; 
                    }
                    bookS data = new bookS(parts[0], parts[1], parts[2], parts[3].equalsIgnoreCase("Available") ? true : false);
                    String name = data.getTitle().trim().toLowerCase(); // convert book name to lowercase
                    String genre = data.getGenre().trim();
                    String id = data.getId().trim().toLowerCase(); // convert ID to lowercase
                    if (genre = genres) {
                        System.out.println("\nBook ID: " + data.getId());
                        System.out.println("Book Name: " + data.getTitle());
                        System.out.println("Genre: " + genre);
                        System.out.println("Available: " + (data.isAvailable() ? "Available" : "Not Available"));
                    }
                }break;
                System.out.println(".\n.\n.\n");

                boolean isLoop = true;
                while (isLoop) {
                System.out.print("Enter your selected book: ");
                String input = scanner.nextLine().trim().toLowerCase(); // convert input to lowercase

                boolean isFound = false;

                try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) {
                        fileContent.add(line);
                        continue;
                    }

                    String bookName = parts[0].trim().toLowerCase();
                    String bookId = parts[2].trim().toLowerCase();
            
                    // Assume 'input' is the book name/ID the user typed
                    if (input.equals(bookName) || input.equals(bookId)) {
                        if (parts[3].trim().equalsIgnoreCase("Available")) {
                        // Change status to notAvailable
                        line = parts[0] + "," + parts[1] + "," + parts[2] + ",notAvailable";
                        isFound = true;
                        System.out.println("\nBook borrowed successfully!");
                    } else {
                        System.out.println("\nBook is already borrowed.");
                    }
            }
            fileContent.add(line);
        }
                } catch (IOException e) {
                    System.out.println("Error reading file.");
                }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

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
    }
}