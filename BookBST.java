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
        else {
            System.out.println("Error: Book with ISBN " + isbn + " already exists in the catalogue.");
        }
        return root;
    }

     // public method to insert a book into the BST
    public void insert(long isbn, String title, String author) {
        this.root = insertBook(this.root, isbn, title, author);
    }
     // private method to search for a book in the BST (for Record finder)
 private Book searchBook(Book root, long isbn) {
    if (root == null || root.getIsbn() == isbn) {
        return root;
    }

    if (isbn < root.getIsbn()) {
        return searchBook(root.left, isbn);
    }

    return searchBook(root.right, isbn);
}
    // public method to search for a book in the BST (for Record finder)
    public Book search(long isbn) {
        return searchBook(this.root, isbn);
    }

    // private method to delete a book from the BST
    private Book deleteBook(Book root, long isbn) {
        if (root == null) {
            return null;
        }

        if (isbn < root.getIsbn()) {
            root.left = deleteBook(root.left, isbn);
        } else if (isbn > root.getIsbn()) {
            root.right = deleteBook(root.right, isbn);
        } else {
            // Node to delete found
            // Case 1: No children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: One child
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // Case 3: Two children - find in-order successor (leftmost in right subtree)
            Book successor = findMin(root.right);
            root = new Book(successor.getIsbn(), successor.getTitle(), successor.getAuthor());
            root.left = deleteBook(root.left, successor.getIsbn());
            root.right = deleteBook(root.right, successor.getIsbn());
        }
        return root;
    }

    // helper method to find node with minimum ISBN (leftmost)
    private Book findMin(Book root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    // public method to delete a book from the catalogue
    public boolean delete(long isbn) {
        Book found = search(isbn);
        if (found != null) {
            this.root = deleteBook(this.root, isbn);
            System.out.println("Book removed from catalogue (ISBN: " + isbn + ")");
            return true;
        }
        return false;
    }
}

