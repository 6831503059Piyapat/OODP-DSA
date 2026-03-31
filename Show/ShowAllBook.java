package Show;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Book.bookS;

public class ShowAllBook {

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

            System.out.println("===== Show All Books =====");

            for (String currentGenre : genres) {

                System.out.println("------ " + currentGenre + " ------");

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

                        if (data.getGenre().equalsIgnoreCase(currentGenre)) {

                            System.out.println("Book ID: " + data.getId());
                            System.out.println("Book Name: " + data.getTitle());
                            System.out.println("Genre: " + data.getGenre());
                            System.out.println("Available: " + (data.isAvailable() ? "True" : "False"));
                            System.out.println(); // เว้นบรรทัดเหมือนในรูป
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Error reading file.");
                }
            }

            // ===== confirm แบบในรูป =====
            boolean valid = false;

            while (!valid) {
                System.out.print("Go back to main menu? (Y/N): ");
                String input = scanner.nextLine().trim();

                if (input.length() > 0) {
                    char c = Character.toUpperCase(input.charAt(0));

                    if (c == 'Y') {
                        isLoop = false;
                        valid = true;
                    } else if (c == 'N') {
                        valid = true;
                    } else {
                        System.out.println("Invalid input. Please enter Y or N.");
                    }

                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
        }
    }
}