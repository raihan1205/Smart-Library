/**
 * TASK 4: ADT DESIGNER — Interface (Information Hiding) [cite: 10]
 * This interface establishes the architectural contract for the Smart Library System. [cite: 5, 10]
 * By defining these abstract operations, we enforce strict Information Hiding. [cite: 10]
 */
public interface LibraryADT {

    /**
     * Adds a new book to the library catalogue system. [cite: 7, 15]
     * Aligned with the team's encapsulated Book entity using a long for the ISBN.
     * @param isbn   The unique identification number of the book [cite: 15]
     * @param title  The title of the book [cite: 15]
     * @param author The author of the book [cite: 15]
     */
    void addBook(long isbn, String title, String author);

    /**
     * Searches for a book in the library system catalogue database by its ISBN. [cite: 9, 16]
     * Keeps implementation hidden by not returning raw node structures to the UI. [cite: 10]
     * @param isbn The unique identification number to look up [cite: 16]
     */
    void searchBook(long isbn);

    /**
     * Borrows a book by locating it in the catalogue and adding it to the history stack. [cite: 11, 17]
     * @param isbn The unique identification number of the book to borrow [cite: 17]
     */
    void borrowBook(long isbn);

    /**
     * Displays all checked-out books in strict LIFO order. [cite: 8, 18]
     */
    void viewLatestHistory();
}
