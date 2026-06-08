/*
 * Task 2: Borrowing History  —  LIFO stack of borrowed books.
 * Linked-list based (not java.util.Stack) so the DS is built from scratch.
 * push / pop / peek = O(1),  show = O(n).
 *
 * ------------------------------------------------------------------
 * INTEGRATION NOTES (for the rest of the group)
 * ------------------------------------------------------------------
 * Depends on : Member 1's Book class (private long isbn, private String
 *              title, private String author) — accessed only via the public
 *              getters getIsbn(), getTitle(), getAuthor().
 *
 * Public API : push(Book)  -> add a newly borrowed book on top
 *              pop()       -> remove + return most recent (for "return book")
 *              peek()      -> view most recent without removing
 *              isEmpty()   -> true if no borrows yet
 *              size()      -> number of books in history
 *              show()      -> print full history, newest first (LIFO)
 *
 * Usage in SmartLibrary.borrowBook(long isbn):
 *     Book b = catalogue.search(isbn);
 *     if (b != null) history.push(b);          // <-- only line you need
 *
 * Usage in viewLatestHistory():
 *     history.show();
 *
 * Do NOT touch the private Node class or the `top` field — that's the
 * information-hiding boundary. Go through the methods only.
 * ------------------------------------------------------------------
 */
public class BorrowStack {

    private static class Node {           // private => information hiding
        Book book; Node next;
        Node(Book b, Node n) { book = b; next = n; }
    }

    private Node top;
    private int size;

    public void push(Book b) {            // add newest borrow on top
        if (b != null) { top = new Node(b, top); size++; }
    }

    public Book pop() {                   // remove + return most recent
        if (top == null) return null;
        Book b = top.book; top = top.next; size--; return b;
    }

    public Book peek()       { return top == null ? null : top.book; }
    public boolean isEmpty() { return top == null; }
    public int size()        { return size; }

    public void show() {                  // LIFO: newest -> oldest
        if (top == null) { System.out.println("History is empty."); return; }
        System.out.println("--- Borrowing History (most recent first) ---");
        int i = 1;
        for (Node c = top; c != null; c = c.next, i++)
            System.out.println(i + ". [ISBN: " + c.book.getIsbn() + "] "
                                 + c.book.getTitle() + " — " + c.book.getAuthor());
    }

    // STANDALONE DEMO — REMOVE LINES 63-75 WHEN MERGING WITH YOUR CODE!!!
    public static void main(String[] args) {
        BorrowStack h = new BorrowStack();
        h.show();
        h.push(new Book(9780262033848L, "Intro to Algorithms",      "Cormen"));
        h.push(new Book(9780132350884L, "Clean Code",               "Martin"));
        h.push(new Book(9780201616224L, "The Pragmatic Programmer", "Hunt"));
        h.show();
        System.out.println("peek: " + h.peek().getTitle() + " | size: " + h.size());
        System.out.println("popped: " + h.pop().getTitle());
        h.show();
    }
}
