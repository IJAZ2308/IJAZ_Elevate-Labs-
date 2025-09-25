import java.util.ArrayList;

// Book class
class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() {
        if (!isIssued) {
            isIssued = true;
            System.out.println(title + " has been issued.");
        } else {
            System.out.println(title + " is already issued.");
        }
    }

    public void returnBook() {
        if (isIssued) {
            isIssued = false;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not issued.");
        }
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
    }
}

// User class
class User {
    private String name;
    private int userId;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() { return name; }
    public int getUserId() { return userId; }

    @Override
    public String toString() {
        return "User: " + name + " (ID: " + userId + ")";
    }
}

// Library class
class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to library.");
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " registered in library.");
    }

    public void showBooks() {
        System.out.println("\nBooks in Library:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void issueBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.issueBook();
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void returnBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.returnBook();
                return;
            }
        }
        System.out.println("Book not found!");
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();

        // Add Users
        User u1 = new User("Alice", 1);
        User u2 = new User("Bob", 2);
        lib.addUser(u1);
        lib.addUser(u2);

        // Add Books
        Book b1 = new Book("Java Basics", "James Gosling");
        Book b2 = new Book("OOP Concepts", "Bjarne Stroustrup");
        lib.addBook(b1);
        lib.addBook(b2);

        // Show all books
        lib.showBooks();

        // Issue & return books
        lib.issueBook("Java Basics");
        lib.issueBook("Java Basics");  // already issued
        lib.returnBook("Java Basics");
        lib.showBooks();
    }
}
