import java.util.Scanner;

/**
 * Implements the library operations and provides a console menu for users.
 */
public class SmartLibrary {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

    // Adds a new book to the library
    public void addBook(long isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
    }

    // Returns the Book object if found, otherwise returns null
    public Book searchBook(long isbn) {
        return catalogue.search(isbn);
    }

    // Allows a user to borrow a book by its ISBN
    public void borrowBook(long isbn) {
        borrowBook(isbn, null);
    }

    // Allows a user to borrow a book by its ISBN and student ID (optional)
    public void borrowBook(long isbn, String studentId) {
        Book b = catalogue.search(isbn);

        if (b != null) {
            history.push(b);
            if (studentId == null || studentId.isBlank()) {
                System.out.println("Success! Book borrowed.");
            } else {
                System.out.println("Success! Book borrowed by student " + studentId + ".");
            }
            System.out.println("Book added to borrowing history.");
            System.out.println("Note: This project build does not implement catalogue deletion on borrow.");
        } else {
            System.out.println("Book not found in catalogue.");
        }
    }

    // Allows a user to view history of borrowed books
    public void viewLatestHistory() {
        history.show();
    }

    // Keeps the menu running in a loop until the user picks exit
    public void runMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Choice: ");

            String menuInput = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(menuInput.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number between 1-5.");
                continue;
            }

            if (choice == 5) {
                System.out.println("Goodbye !!");
                break;
            }

            handleChoice(choice, sc);
        }

        sc.close();
    }

    // Prints the menu options
    private void printMenu() {
        System.out.println("\n--- Smart Library Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. View History");
        System.out.println("5. Exit");
    }

    // Handles choice based on what the user picked
    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                handleAddBook(sc);
                break;

            case 2:
                handleSearch(sc);
                break;

            case 3:
                handleBorrowBook(sc);
                break;

            case 4:
                viewLatestHistory();
                break;

            default:
                System.out.println("Invalid option. Please choose 1-5.");
        }
    }

    // Handles adding a book
    private void handleAddBook(Scanner sc) {
        System.out.print("Enter ISBN: ");
        String isbnInput = sc.nextLine();

        long addIsbn;

        try {
            addIsbn = Long.parseLong(isbnInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ISBN. Please enter a number.");
            return;
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Enter Author: ");
        String author = sc.nextLine().trim();

        if (author.isEmpty()) {
            System.out.println("Author cannot be empty.");
            return;
        }

        addBook(addIsbn, title, author);
    }

    // Handles borrowing a book
    private void handleBorrowBook(Scanner sc) {
        System.out.print("Enter ISBN to borrow: ");
        String borrowInput = sc.nextLine();

        long borrowIsbn;

        try {
            borrowIsbn = Long.parseLong(borrowInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ISBN. Please enter a number.");
            return;
        }

        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine().trim();

        if (studentId.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        borrowBook(borrowIsbn, studentId);
    }

    // Displays the search menu and handles the selected search method
    private void handleSearch(Scanner sc) {
        System.out.println("\n--- Search Options ---");
        System.out.println("1. Search by ISBN");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.print("Choice: ");

        String input = sc.nextLine();

        switch (input) {
            case "1":
                System.out.print("Enter ISBN to search: ");
                String isbnInput = sc.nextLine();

                try {
                    long isbn = Long.parseLong(isbnInput.trim());
                    Book found = searchBook(isbn);

                    if (found != null) {
                        System.out.println("Found: [ISBN: " + found.getIsbn() + "] "
                                + found.getTitle() + " by " + found.getAuthor());
                    } else {
                        System.out.println("Book not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ISBN. Please enter a number.");
                }
                break;

            case "2":
                System.out.println("Search by title is not available in the current BookBST implementation.");
                break;

            case "3":
                System.out.println("Search by author is not available in the current BookBST implementation.");
                break;

            default:
                System.out.println("Invalid search option.");
        }
    }

}