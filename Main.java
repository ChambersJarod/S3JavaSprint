import classes.Author;
import classes.Book;
import classes.Library;
import classes.Patron;

public class Main {
    public static void main(String[] args) {
        //testing the code

        //creating the author herman melville and his books moby dick and bill budd sailor
        Author hermanMelville = new Author();
        hermanMelville.setName("Herman Melville");
        Book mobyDick = new Book("Moby Dick ", hermanMelville, "isbn1", "Harper & Brothers", 9);
        Book billyBuddSailor = new Book("Billy Budd, Sailor", hermanMelville, "isbn2", "Pocket Books", 8);
        hermanMelville.addBook(mobyDick);
        hermanMelville.addBook(billyBuddSailor);

        //creating the author eric carle and his book the very hungry caterpillar
        Author ericCarle = new Author();
        ericCarle.setName("Eric Carle");
        Book theVeryHungryCaterpillar = new Book("The Very Hungry Caterpillar", ericCarle, "isbn3", "World Publishing Company", 7);
        ericCarle.addBook(theVeryHungryCaterpillar);

        //creating jim as a patron
        Patron jim = new Patron();
        jim.setName("jim");
        jim.setDateOfBirth(2000, 1, 1);
        jim.setAddress("52 main street");

        //creating john as a patron
        Patron john = new Patron();
        john.setName("john");
        john.setDateOfBirth(1999, 9, 9);
        john.setAddress("97 main street");

        //creating a library and adding authors books and patrons
        Library library = new Library();
        library.addBook(theVeryHungryCaterpillar);
        library.addAuthor(hermanMelville); // Should add Tom Sawyer and say Huck Finn is already in the system
        library.addAuthor(ericCarle);
        library.addPatron(jim);
        library.addPatron(john);
        library.borrowBook(theVeryHungryCaterpillar, jim);
        library.borrowBook(theVeryHungryCaterpillar, john, 2);
        System.out.println("jim's Borrowed Books:\n" + jim.getBorrowedBooks() + "\n");
        System.out.println("johns's Borrowed Books:\n" + john.getBorrowedBooks() + "\n");
        System.out.println("Patron List:\n" + library.getPatronList() + "\n");
        System.out.println("List of Books in Library:\n" + library.getBooksList() + "\n");
        //making john return 2 copys of the very hungry caterpillar
        System.out.println(library.returnBook(theVeryHungryCaterpillar, john, 2));

        // Test searching books through various methods
        System.out.println(library.searchBooks("Herman Melville")); //prints moby dick and billy budd sailor
        System.out.println(library.searchBooks("isbn3")); //prints The Very Hungry Caterpillar
        System.out.println(library.searchBooks("The Very Hungry Caterpillar")); //prints The Very Hungry Caterpillar
        System.out.println(library.searchBooks("afaujhjga")); //prints an empty array
    }
}