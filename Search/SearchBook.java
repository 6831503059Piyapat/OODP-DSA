package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
                    String[] data = line.split(",");
                    String name = data[0].trim().toLowerCase(); // convert book name to lowercase
                    String genre = data[1].trim();
                    String id = data[2].trim().toLowerCase(); // convert ID to lowercase

                    if (input.equals(name) || input.equals(id)) {
                        isFound = true;
                        System.out.println("\n---------------------------------------");
                        System.out.println("Book ID: " + data[2].trim());
                        System.out.println("Book Name: " + data[0].trim());
                        System.out.println("Genre: " + genre);
                        System.out.println("Available: " + (book.isBorrow() ? "Not Available" : "Available"));
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
                char choice = scanner.nextLine().trim().toUpperCase().charAt(0);
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
            }
        }
    }
}