package classes;

public interface Borrowable {

    //method for letting patrons borrow books
    Book borrowBook(Book book, Patron patron, int qty);

    //method for letting patrons return books
    Book returnBook(Book book, Patron patron, int qty);

}