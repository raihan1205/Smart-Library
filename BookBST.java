 // a BST class to store books 
public class BookBST {

    private Book root; // the root node of the BST

    // private method to insert a book into the BST
    private  Book insertBook(Book root, long isbn, String title, String author) {
        if (root == null) {
            root = new Book(isbn, title, author);
            System.out.println("Book added: \"" + title + "\" by " + author + " (ISBN: " + isbn + ")");
            return root;  
        }
        else if (isbn < root.getIsbn()) {
            root.left = insertBook(root.left, isbn, title, author);
        }
        else if (isbn > root.getIsbn()) {
            root.right = insertBook(root.right, isbn, title, author);
        }
        else {System.out.println("Error: Book with ISBN " + isbn + " already exists in the catalogue.");}
        return root;
    }

     // public method to insert a book into the BST
    public void insert(long isbn, String title, String author) {
        this.root = insertBook(this.root, isbn, title, author);
    }
     // private skeleton method to search for a book in the BST (for Record finder)
 private Book searchBook(Book root, long isbn) {
    if (root == null || root.getIsbn() == isbn) {
        return root;
    }

    if (isbn < root.getIsbn()) {
        return searchBook(root.left, isbn);
    }

    return searchBook(root.right, isbn);
}
    // public skeleton method to search for a book in the BST (for Record finder)
public Book search(long isbn) {
    return searchBook(this.root, isbn);
}
    }

