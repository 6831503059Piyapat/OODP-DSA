package Show;

import Book.bookS;


public class BookNode {
    private bookS data;
    private BookNode next;
    
    // Previous Pointer: Points to the previous node in the list
    private BookNode previous;

    public BookNode(bookS data) {
        this.data = data;
        this.next = null;      // Initially, next pointer is null
        this.previous = null;  // Initially, previous pointer is null
    }

    // Getter and Setter for Data
    public bookS getData() {
        return data;
    }

    public void setData(bookS data) {
        this.data = data;
    }

    // Getter and Setter for Next Pointer
    // Next pointer: Navigates forward in the list
    public BookNode getNext() {
        return next;
    }

    public void setNext(BookNode next) {
        this.next = next;
    }

    // Getter and Setter for Previous Pointer
    // Previous pointer: Navigates backward in the list
    public BookNode getPrevious() {
        return previous;
    }

    public void setPrevious(BookNode previous) {
        this.previous = previous;
    }
}
