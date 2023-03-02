package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Author {
    private ArrayList<Book> booksWrittenList; // Use array list as we don't know size
    private String name;
    private LocalDate dateOfBirth;

    //creating an empty constructor
    public Author() {
        this.booksWrittenList = new ArrayList<>();  //  Initialize empty ArrayList to prevent null
    }

    //setting and getting the authors books written
    public ArrayList<Book> getBooksWrittenList() {
        return booksWrittenList;
    }

    public void setBooksWrittenList(ArrayList<Book> booksWrittenList) {
        this.booksWrittenList = booksWrittenList;
    }

    //setting and getting the authors name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //setting and getting the authors date of birth
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int year, int month, int day) {
        this.dateOfBirth = LocalDate.of(year, month, day);
    }

    //adding books to the list that the author has written
    public ArrayList<Book> addBook(Book book){
        this.booksWrittenList.add(book);
        return this.booksWrittenList;
    }
}