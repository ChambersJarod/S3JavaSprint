package classes;

import java.util.ArrayList;

public class Library implements Borrowable{
    private ArrayList<Book> booksList; //   Use array list as we don't know size
    private ArrayList<Author> authorList;
    private ArrayList<Patron> patronList;

    //creating an empty constructor
    public Library() {
        // Initialize empty ArrayLists to prevent null values
        this.booksList = new ArrayList<>();
        this.authorList = new ArrayList<>();
        this.patronList = new ArrayList<>();
    }

    //setting and getting the books
    public ArrayList<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(ArrayList<Book> booksList) {
        this.booksList = booksList;
    }

    //setting and getting the authors
    public ArrayList<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(ArrayList<Author> authorList) {
        this.authorList = authorList;
    }

    //setting and getting the patrons
    public ArrayList<Patron> getPatronList() {
        return patronList;
    }

    public void setPatronList(ArrayList<Patron> patronList) {
        this.patronList = patronList;
    }

    //adding books
    public ArrayList<Book> addBook(Book book) {
        if(!this.booksList.contains(book)){
            this.booksList.add(book);
        } else {
            System.out.println("Error: " + book.getTitle() + " is already in the system");
        }
        return this.booksList;
    }

    //removing books
    public ArrayList<Book> removeBook(Book book) {
        this.booksList.remove(book);
        return this.booksList;
    }

    //adding authors
    public ArrayList<Author> addAuthor(Author author) {
        if(!this.authorList.contains(author)){
            this.authorList.add(author);
            for (Book bookByAuthor : author.getBooksWrittenList()){
                this.addBook(bookByAuthor);
            }
        } else {
            System.out.println("Error: " + author.getName() + " is already in the system");
        }
        return this.authorList;
    }

    //removing authors
    public ArrayList<Author> removeAuthor(Author author) {
        this.authorList.remove(author);
        for (Book bookByAuthor : author.getBooksWrittenList()){
            this.removeBook(bookByAuthor);
        }
        return this.authorList;
    }

    //adding patrons
    public ArrayList<Patron> addPatron(Patron patron) {
        if(!this.patronList.contains(patron)){ // Only add Patron if they don't already exist
            this.patronList.add(patron);
        } else {
            System.out.println("Error: " + patron.getName() + " is already in the system");
        }
        return this.patronList;
    }

    //removing patrons
    public ArrayList<Patron> removePatron(Patron patron) {
        this.patronList.remove(patron);
        return this.patronList;
    }

    //overides interface borrowable
    public Book borrowBook(Book book, Patron patron) {
        return this.borrowBook(book, patron, 1);
    }

    @Override
    public Book borrowBook(Book book, Patron patron, int qty) {
        //checking if there are enough books
        if (book.getNumberOfCopies() < qty){
            System.out.println("Error: Improper amount of books to loan out");
            book.setStatus(Status.CHECKED_OUT);
        } else {
            //taking books away from the library if they are aviable
            book.setNumberOfCopies(book.getNumberOfCopies() - qty);
            System.out.println(book.getTitle() + " x" + qty + " has been checked out by " + patron.getName());
            if (book.getNumberOfCopies() == 0){
                book.setStatus(Status.CHECKED_OUT);
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

    public Book returnBook(Book book, Patron patron) {
        return this.returnBook(book, patron, 1);
    }

    @Override
    public Book returnBook(Book book, Patron patron, int qty) {
        //adding the book to the library
        for (int i = 0; i < qty; i++) {
            if (patron.getBorrowedBooks().contains(book.getTitle())){
                patron.removeBook(book);
                book.setNumberOfCopies(book.getNumberOfCopies() + 1);
                //taking the book away from the library if the patron does not have enough aviable
            } else {
                System.out.printf("Error: %s only has %d copy/copies of %s%n", patron.getName(),(qty - i), book.getTitle());
                break;
            }
        }

        return book;
    }
    
    //searching the library for books
    public ArrayList<Book> searchBooks(String searchParameter){
        ArrayList<Book> matchedBooks = new ArrayList<>();
        for (Book book : booksList) {
            if (book.getTitle().equals(searchParameter) ||      //  Multiline if statement to check
                    book.getAuthor().getName().equals(searchParameter) ||   // if book matches search parameters
                    book.getISBN().equals(searchParameter)
            ){
                matchedBooks.add(book);     //  Add book to arrayList if it matches, so we can return it
            }
        }
        return matchedBooks;
    }
}