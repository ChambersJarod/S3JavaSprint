package classes;

public class Book implements Borrowable {
    private String title;
    private Author author;  // Author references Author class
    private String ISBN;
    private String publisher;
    private int numberOfCopies;
    private Status status;

    //empty constructor
    public Book() {}

    //compleate constructor
    public Book(String title, Author author, String ISBN, String publisher, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
    }

    //getting and setting the books title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //getting and setting the books author
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    //getting and setting the books isbn
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    //getting and setting the books publisher
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    //getting and setting the books number of copys
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    //getting and setting the books status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //converting the book info to a string
    @Override
    public String toString() {
        return String.format(
                "Book Title: %s%n" +
                        "Author: %s%n" +
                        "ISBN: %s%n" +
                        "Publisher: %s%n" +
                        "Copies: %d%n",
                title, author.getName(), ISBN, publisher, numberOfCopies);
    }

    //borrowing a book
    public Book borrowBook(Book book, Patron patron) {
        return this.borrowBook(book, patron, 1);
    }

    @Override
    public Book borrowBook(Book book, Patron patron, int qty) {
        //writing an error if the library doesn't have enough copys
        if (book.getNumberOfCopies() < qty){
            System.out.println("Error: Improper amount of books to loan out");
            book.setStatus(Status.CHECKED_OUT);
        } else {
            //taking away the ammout of requested books from the library
            book.setNumberOfCopies(book.getNumberOfCopies() - qty);
            System.out.println(book.getTitle() + " x" + qty + " has been checked out by " + patron.getName());
            //if the book is out of copys the status is changed to checked out
            if (book.getNumberOfCopies() == 0){
                book.setStatus(Status.CHECKED_OUT);
            //if it is not out of copys setting the status to aviable
            }else {
                book.setStatus(Status.AVAILABLE);
            }
            //adding the book to the patron
            for (int i = 0; i < qty; i++) {
                patron.addBook(book);
            }
        }
        return book;
    }

    //returning the book
    public Book returnBook(Book book, Patron patron) {
        return this.returnBook(book, patron, 1);
    }

    @Override
    public Book returnBook(Book book, Patron patron, int qty) {
        //adding the book to the library
        for (int i = 0; i < qty; i++) {
            //removing the book from the patron
            if (patron.getBorrowedBooks().contains(book.getTitle())){
                patron.removeBook(book);
                book.setNumberOfCopies(book.getNumberOfCopies() + 1);
            } else {
                System.out.printf("Error: %s only has %d copy/copies of %s%n", patron.getName(),(qty - i), book.getTitle());
                break;
            }
        }

        return book;
    }
}