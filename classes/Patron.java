package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patron {
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private ArrayList<Book> borrowedBooks;

    //blank constructor
    public Patron() {
        this.borrowedBooks = new ArrayList<Book>(); // Initialize empty ArrayList to prevent null
    }

    //getting and returning the name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //getting and returning the date of birth
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int year, int month, int day) {
        this.dateOfBirth = LocalDate.of(year, month, day);
    }

    //getting and returning the address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //getting and returning the borrowed books
    public ArrayList<String> getBorrowedBooks() {
        ArrayList<String> bookArr = new ArrayList<>();
        borrowedBooks.forEach((x) -> bookArr.add(x.getTitle()));
        return bookArr;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    //converting it to a string
    @Override
    public String toString() {
        return String.format(
                "Patron Name: %s%n" +
                        "DoB: %s%n" +
                        "Address: %s%n",
                this.getName(), this.getDateOfBirth(), this.getAddress());
    }

    //adding and removing borrowed books
    public ArrayList<Book> addBook(Book book){
        this.borrowedBooks.add(book);
        return this.borrowedBooks;
    }

    public ArrayList<Book> removeBook(Book book){
        this.borrowedBooks.remove(book);
        return this.borrowedBooks;
    }
}