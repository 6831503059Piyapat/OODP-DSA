package Borrow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Book.bookS;

public class BorrowBook {
    private boolean isLoop = true;
    public BorrowBook() {
    }

    public void BorrowUI(){
        
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
                    BorrowMedtod(genres);
                    break;
                case 2:
                    genres = "Non-Fiction";
                    BorrowMedtod(genres);
                    break;
                case 3:
                    genres = "Entertainment";
                    BorrowMedtod(genres);
                    break;
                case 4:
                    genres = "Educational";
                    BorrowMedtod(genres);
                    break;
                case 5:
                    genres = "Historical";
                    BorrowMedtod(genres);
                    break;
                default:
                    System.out.println("Invalid Genre.");
            }
        }
    }

    public void BorrowMedtod(String genres){
        Scanner scanner = new Scanner(System.in);
       
        List<String> fileContent = new ArrayList<>();
        boolean isFound = false;
        boolean validChoice = false;
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            System.out.println("\n------- "+ "genres" +" -------");
            while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) {
                        continue; 
                    }
                    bookS data = new bookS(parts[0], parts[1], parts[2], parts[3].equalsIgnoreCase("Available") ? true : false);
                    String name = data.getTitle().trim().toLowerCase(); // convert book name to lowercase
                    String genre = data.getGenre().trim();
                    String id = data.getId().trim().toLowerCase(); // convert ID to lowercase
                    if (genre.equals(genres)) {
                        System.out.println("\nBook ID: " + data.getId());
                        System.out.println("Book Name: " + data.getTitle());
                        System.out.println("Genre: " + genre);
                        System.out.println("Available: " + (data.isAvailable() ? "Available" : "Not Available"));
                    }
                    
                }
                System.out.println(".\n.\n.\n");
                
                
                System.out.print("Enter your selected book: ");
                String input = scanner.nextLine().trim().toLowerCase(); // convert input to lowercase

                boolean isFound1 = false;

                try (BufferedReader br1 = new BufferedReader(new FileReader("books.txt"))) {
                    String line1;
                    while ((line1 = br1.readLine()) != null) {
                    String[] parts = line1.split(",");
                    if (parts.length < 3) {
                        fileContent.add(line1);
                        continue;
                    }

                    String bookName = parts[0].trim().toLowerCase();
                    String bookId = parts[2].trim().toLowerCase();
            
                    // Assume 'input' is the book name/ID the user typed
                    if (input.equals(bookName) || input.equals(bookId)) {
                        if (parts[3].trim().equalsIgnoreCase("Available")) {
                        // Change status to notAvailable
                        line1 = parts[0] + "," + parts[1] + "," + parts[2] + ",Not Available";
                        isFound1 = true;
                        System.out.println("\nBook borrowed successfully!  ");
                    } else {
                        System.out.println("\nBook is already borrowed.");
                    }
                }
                fileContent.add(line1);
             
            }

                } catch (IOException e) {
                    System.out.println("Error reading file.");
            }
             try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            for (int i = 0; i < fileContent.size(); i++) {
                writer.write(fileContent.get(i));
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }   
            
            

        } 
        catch (IOException e) {
            System.out.println("Error reading file."); 
        }
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
