package Show;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Book.bookS;

public class ShowAllBook {
     private List<bookS> fetchBooksForGenre(String genre) {
        List<bookS> books = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length < 4) continue;
                
                bookS data = new bookS(
                    parts[0],
                    parts[1],
                    parts[2],
                    parts[3].equalsIgnoreCase("Available")
                );
                
                if (data.getGenre().equalsIgnoreCase(genre)) {
                    books.add(data);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return books;
    }

    
    public void showAllUI() {
        Scanner scanner = new Scanner(System.in);

        String[] genres = {
            "Fiction",
            "Non-Fiction",
            "Entertainment",
            "Educational",
            "Historical"
        };

        boolean isLoop = true;

        while (isLoop) {
            // Show genre selection menu
            System.out.println("===== Show Books by Category =====");
            System.out.println("Select a Genre:");
            for (int i = 0; i < genres.length; i++) {
                System.out.println((i + 1) + ". " + genres[i]);
            }
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Enter your choice (0-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice == 0) {
                isLoop = false;
                break;
            }
            
            if (choice < 1 || choice > genres.length) {
                System.out.println("Invalid choice. Please try again.\n");
                continue;
            }
            
            String selectedGenre = genres[choice - 1];
            
            // Fetch books for selected genre from file
            List<bookS> genreBooks = fetchBooksForGenre(selectedGenre);
            
            if (genreBooks.isEmpty()) {
                System.out.println("\nNo books found in the " + selectedGenre + " category.\n");
                continue;
            }
            
            // Create pagination object with Double Linked List structure
            // Head Pointer initialized in BookPagination constructor
            BookPagination pagination = new BookPagination(genreBooks);
            
            boolean viewing = true;
            while (viewing) {
                System.out.println("===== " + selectedGenre + " Books =====");
                
                // Display current page (5 books at a time)
                // Uses current pointer to navigate the double linked list
                pagination.displayCurrentPage();
                
                System.out.println("Navigation: 'D' = Next Page | 'A' = Previous Page | 'B' = Back to Menu");
                System.out.print("Enter command: ");
                String input = scanner.nextLine().trim().toLowerCase();
                
                if (input.length() > 0) {
                    char command = input.charAt(0);
                    
                    switch (command) {
                        // 'd' - Move forward: Uses Next Pointer to navigate 5 items forward
                        case 'd':
                            if (pagination.isLastPage()) {
                                System.out.println("Already at the last page!");
                            } else {
                                // Next Pointer: Moves to next set of 5 books
                                pagination.nextPage();
                            }
                            break;
                            
                        // 'a' - Move backward: Uses Previous Pointer to navigate 5 items backward
                        case 'a':
                            if (pagination.isFirstPage()) {
                                System.out.println("Already at the first page!");
                            } else {
                                // Previous Pointer: Moves to previous set of 5 books
                                pagination.previousPage();
                            }
                            break;
                            
                        // 'b' - Back to category selection
                        case 'b':
                            viewing = false;
                            break;
                            
                        default:
                            System.out.println("Invalid command. Please enter 'd', 'a', or 'b'.\n");
                            break;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a command.\n");
                }
            }
        }
    }
    
  
   
}