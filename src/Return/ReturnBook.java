package Return;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Book.bookS;

public class ReturnBook {
    private boolean isLoop = true;
    
    public ReturnBook() {
    }

    public void ReturnUI() {
        Scanner scanner = new Scanner(System.in);
        
        while (isLoop) {
            System.out.println("\n===== Return Book =====");
            
            
            List<bookS> borrowedBooks = getBorrowedBooks();
            
            if (borrowedBooks.isEmpty()) {
                System.out.println("\nNo borrowed books to return.");
                System.out.println("\nPress Enter to go back to main menu...");
                scanner.nextLine();
                isLoop = false;
                return;
            }
            
            
            System.out.println("\n--- Your Borrowed Books ---");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                bookS book = borrowedBooks.get(i);
                System.out.println((i + 1) + ". " + book.getTitle() + " (ID: " + book.getId() + ") - " + book.getGenre());
            }
            System.out.println("0. Back to Main Menu");
            
            System.out.print("\nSelect book to return (0-" + borrowedBooks.size() + "): ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            if (choice == 0) {
                isLoop = false;
                return;
            }
            
            if (choice < 1 || choice > borrowedBooks.size()) {
                System.out.println("Invalid selection.");
                continue;
            }
            
            
            bookS selectedBook = borrowedBooks.get(choice - 1);
            returnBook(selectedBook.getId());
            
            
            System.out.print("\nReturn another book? (Y/N): ");
            String answer = scanner.nextLine().trim().toUpperCase();
            if (!answer.equals("Y")) {
                isLoop = false;
            }
        }
    }

    
    private List<bookS> getBorrowedBooks() {
        List<bookS> borrowedBooks = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                
                String status = parts[3].trim();
                if (status.equalsIgnoreCase("Not Available")) {
                    bookS book = new bookS(parts[0].trim(), parts[1].trim(), parts[2].trim(), false);
                    borrowedBooks.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        
        return borrowedBooks;
    }

    
    private void returnBook(String bookId) {
        List<String> fileContent = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[2].trim().equalsIgnoreCase(bookId)) {
                    
                    line = parts[0] + "," + parts[1] + "," + parts[2] + ",Available";
                    found = true;
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }
        
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            for (String line : fileContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
            return;
        }
        
        if (found) {
            System.out.println("\n✓ Book returned successfully!");
        }
    }
}

