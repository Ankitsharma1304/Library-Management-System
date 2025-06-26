import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    void display() {
        System.out.println("Book ID: " + id + ", Title: " + title +
                ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No"));
    }
}

class User {
    int userId;
    String name;
    ArrayList<Book> issuedBooks;

    User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    void display() {
        System.out.println("User ID: " + userId + ", Name: " + name);
        if (issuedBooks.isEmpty()) {
            System.out.println("No books issued.");
        } else {
            System.out.println("Issued Books:");
            for (Book b : issuedBooks) {
                System.out.println(" - " + b.title);
            }
        }
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    void addUser(User user) {
        users.add(user);
    }

    void viewBooks() {
        for (Book b : books) {
            b.display();
        }
    }

    void viewUsers() {
        for (User u : users) {
            u.display();
        }
    }

    Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    User findUser(int id) {
        for (User u : users) {
            if (u.userId == id) return u;
        }
        return null;
    }

    void issueBook(int userId, int bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user == null || book == null) {
            System.out.println("User or Book not found.");
            return;
        }

        if (book.isIssued) {
            System.out.println("Book is already issued.");
        } else {
            book.isIssued = true;
            user.issuedBooks.add(book);
            System.out.println("Book issued successfully to " + user.name);
        }
    }

    void returnBook(int userId, int bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user == null || book == null) {
            System.out.println("User or Book not found.");
            return;
        }

        if (user.issuedBooks.contains(book)) {
            user.issuedBooks.remove(book);
            book.isIssued = false;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("This user didn't issue the book.");
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        // Adding sample data
        lib.addBook(new Book(101, "Java Programming", "James Gosling"));
        lib.addBook(new Book(102, "Data Structures", "Mark Allen Weiss"));
        lib.addUser(new User(1, "Ankit Sharma"));
        lib.addUser(new User(2, "Ravi Kumar"));

        int choice;
        do {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. View Books");
            System.out.println("2. View Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    lib.viewBooks();
                    break;
                case 2:
                    lib.viewUsers();
                    break;
                case 3:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    lib.issueBook(uid, bid);
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int rid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int rbid = sc.nextInt();
                    lib.returnBook(rid, rbid);
                    break;
                case 5:
                    System.out.println("Thanks for using Library Management System.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
