package Show;

import java.util.ArrayList;
import java.util.List;
import Book.bookS;


public class BookPagination {
    private static final int ITEMS_PER_PAGE = 5;
    
    // Head Pointer
    private BookNode head;
    
    // Current Pointer
    private BookNode currentPage;
    
    private int currentPageNumber;
    private int totalPages;

    
    public BookPagination(List<bookS> books) {
        this.head = null;
        this.currentPage = null;
        this.currentPageNumber = 0;
        this.totalPages = (int) Math.ceil((double) books.size() / ITEMS_PER_PAGE);

        // Head Pointer: Stores reference to first node
        BookNode prevNode = null;
        for (bookS book : books) {
            BookNode newNode = new BookNode(book);

            if (prevNode != null) {
                // Previous Pointer: Link back to previous node
                newNode.setPrevious(prevNode);
                // Next Pointer: Link forward from previous node
                prevNode.setNext(newNode);
            } else {
                // Head initialization at first node
                this.head = newNode;
            }

            prevNode = newNode;
        }

        this.currentPage = head;
    }


    public void displayCurrentPage() {
        if (head == null) {
            System.out.println("No books to display.");
            return;
        }

        System.out.println("\n===== Page " + (currentPageNumber + 1) + " of " + totalPages + " =====\n");

        // Current Pointer: References the first book of current page
        BookNode current = currentPage;
        int count = 0;

        // Traverse forward using Next Pointer for 5 items
        while (current != null && count < ITEMS_PER_PAGE) {
            bookS book = current.getData();
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book Name: " + book.getTitle());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Available: " + (book.isAvailable() ? "Available" : "Not Available"));
            System.out.println();

            // Next Pointer: Move to next node in the list
            current = current.getNext();
            count++;
        }
    }


    public void nextPage() {
        if (currentPageNumber >= totalPages - 1) {
            System.out.println("Already at the last page!");
            return;
        }

        // Move 5 nodes forward using Next Pointer
        for (int i = 0; i < ITEMS_PER_PAGE && currentPage != null; i++) {
            currentPage = currentPage.getNext();
        }

        currentPageNumber++;
        System.out.println("Moved to next page.");
    }


    public void previousPage() {
        if (currentPageNumber <= 0) {
            System.out.println("Already at the first page!");
            return;
        }

        // Move 5 nodes backward using Previous Pointer
        for (int i = 0; i < ITEMS_PER_PAGE && currentPage != null; i++) {
            currentPage = currentPage.getPrevious();
        }

        currentPageNumber--;
        System.out.println("Moved to previous page.");
    }

    
    //  Get current page number (1-indexed)
     
    public int getCurrentPageNumber() {
        return currentPageNumber + 1;
    }

    
     // Get total number of pages
     
    public int getTotalPages() {
        return totalPages;
    }

    
    // Check if at last page
     
    public boolean isLastPage() {
        return currentPageNumber >= totalPages - 1;
    }

    
    // Check if at first page
     
    public boolean isFirstPage() {
        return currentPageNumber <= 0;
    }
}
