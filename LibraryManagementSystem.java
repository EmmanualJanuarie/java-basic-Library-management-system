//Emmanual Januarie [student number -> 20230432]

import java.util.*;//importing all the util librarys
import java.util.concurrent.TimeUnit;//importing TimeUnit
import java.util.regex.Pattern;//importing pattern [using for email-validation]
import java.time.LocalDateTime;//importing java localDateTime library

//creating class for Books
class Book {
    LocalDateTime dateTime = LocalDateTime.now();
    //creating attributes of Books class
    String title;//created title field of class Books
    String author;//created author field of class Books
    String isbn;//created isbn field of class Books
    boolean isAvailable;//created isAvailable field of class Books
    int loanDate;

    //creating constructor of class Book
    Book(String title, String author, String isbn, boolean isAvailable) {//adding parameters
        //Assigning parameters of constructor to attributes of class Books
        this.title = title;//Assigned parameter title to attribute title
        this.author = author;//Assigned parameter author to attribute title
        this.isbn = isbn;//Assigned parameter isbn to attribute title
        this.isAvailable = isAvailable;//Assigned parameter isAvailable to attribute title
        this.loanDate = dateTime.getDayOfMonth();
    }

    //creating a method for availibility of book
    void toggleAvailability() {
        isAvailable = !isAvailable;
    }
    
}

//creating class for Members
class Member {
    //creating attributes of members class
    String name;//created name field of class Members
    String email;//created email field of class Members
    List<Book> borrowedBooks;//created borrowedBooks field of class Members [Declared as a List]

    //creating constructor of class Members
    Member(String name, String email) {
        this.name = name;//Assigned parameter name to attribute name
        setEmail(email);//Assigned parameter email to attribute email
        this.borrowedBooks = new ArrayList<>();//set attribute to a new arrayList
    }

    //creating a method to handle errors of email [Validation of email address]
    void setEmail(String email) {//triggered when email matches requirements
        assert !email.isEmpty() : "Assertion Error: email can't be null";//assertion for debugging
        if (Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)) {
            this.email = email;
        } else {//triggered when email does'nt matches requirements
            throw new IllegalArgumentException("Invalid email format");
        }
    }

      

    //creating a method to handle errors regarding borrowing of books [Validation of Book Borrowing]
    void borrowBook(Book book) {
        assert book == null : "book can't be null";//assertion for debugging
        if (book.isAvailable) {//triggered when book is available
            book.toggleAvailability();
            borrowedBooks.add(book);
        } else {//triggered when book is not available
            throw new IllegalArgumentException("Book is not available for checkout");
        }
    }

    //creting method to display books borrowed
    void displayBorrowedBooks(){
        System.out.println("Books checked out by" + " " + name);
        //loop for borrowed book
        for(Book book: borrowedBooks){
            System.out.println(" - " + book.title + " by " + book.author);
        }
    }

    //creting method to display books borrowed
    void BookReturnDate(int currDate, int currMonth, int currYear){
        System.out.println("Books returned by" + " " + name);
        int loanDate = currDate + 3;
        //loop for borrowed book
        for(Book book: borrowedBooks){
            System.out.println(" - " + book.title + " by " + book.author + " " + "returned before" + " " + "[" +
            + loanDate + "/" + currMonth + "/" + currYear + "]");
        }
    }

    //creting method to return books borrowed
    void BookReturnRenalty(int currDate){
        System.out.println("Books returned by" + " " + name);
        int loanDate = currDate + 3;//creating sum of loan
        //loop for borrowed book
        for(Book book: borrowedBooks){
            System.out.println(" - " + book.title + " by " + book.author);
            System.out.println(IErrors.IsReturnValid(currDate, loanDate));
            break;
        }
    }
}

class Library {
    Map<String, Book> books;//creating Book class as an object HashMap books
    Map<String, Member> members;//creating Member class as an object HashMap members

    Library() {//creating a constructor of class Library
        this.books = new HashMap<>();//Assigned attribute books to new HashMap
        this.members = new HashMap<>();//Assigned attribute members to new HashMap
    }

    //creating a method, to add books
    void addBook(Book book) {
        assert book != null : "book can't be null";//assertion for debugging
        books.put(book.isbn, book);//adding book to a HashMap called books
    }

    //creating a method, to add Member
    void addMember(Member member) {
        assert member != null : "Member can't be null";//assertion for debugging
        members.put(member.email, member);//adding member to a HashMap called members
    }

    // Creating method to display the members borrowed books
    void displayMemberBorrowedBooks(String memberName) {
        for (Member member : members.values()) {
            if (member.name.equalsIgnoreCase(memberName)) {
                member.displayBorrowedBooks();
                return;
            }
        }
        System.out.println("Member not found");
    }


    //creating a method to search for book by it's title
    Book searchBookByTitle(String title) {
        for (Book book : books.values()) {//for loop [Index book, is created to access each book in books]
            if (book.title.equalsIgnoreCase(title)) {//triggered, then book is returned
                return book;
            }
        }
        return null;
    }

    //creating a method to search for book by Author
    Book searchBookByAuthor(String author) {
        for (Book book : books.values()) {//for loop [Index book, is created to access each book in books]
            if (book.author.equalsIgnoreCase(author)) {//triggered, then book is returned
                return book;
            }
        }
        return null;
    }

    //creating a method to search member by email
    Member searchMemberByEmail(String email) {
        return members.get(email);
    }
}

public class LibraryManagementSystem  implements LoginMenu, IErrors{
    static Scanner scanner = new Scanner(System.in);//creating an object of library Scanner

    //creating Main Method
    public static void main(String[] args) {
        Library library = new Library();
        // Member member = new Member(null, null);
        LoginMenu.LoginUI(library);//calling Login UI from LoginMenu interface
       
        }

    static void addBook(Library library) {//Method to add book(s)
        //Input for title of book
        try{
        System.out.print("Please enter title of the book: ");
        String title = scanner.nextLine();
        IErrors.IsTitleNull(title);//calling IErrors method to check if isbn entry is null

        System.out.println();//empty print line [For readibility]

        //Input for author of book
        System.out.print("Please enter author of the book: ");
        String author = scanner.nextLine();
        IErrors.IsAuthorNull(author);//calling IErrors method to check if author is null
        System.out.println();//empty print line [For readibility]

        //Input for ISBN of book
        System.out.print("Please enter ISBN number of the book: ");
        String isbn = scanner.nextLine();

        IErrors.IsISBN_ValidSize(isbn);//checking if isbn number is valid size
        IErrors.IsIsbnNull(isbn);//Calling IErrors method to check if isbn is null
        IErrors.IsNumber(isbn);//Calling method [checking if isbn is a number]

        System.out.println();//empty print line [For readibility]

        //Input for availability of book
        System.out.print("Is the book available? (true/false):");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine());
        IErrors.IsIsAvailableNull(isAvailable);//calling method from IErrors [check if value is null]
        System.out.println();//empty print line [For readibility]
        
        Book book = new Book(title, author, isbn, isAvailable);
        library.addBook(book);

        System.out.println("[Book added successfully]");//mesasge to terminal
        System.out.println();//empty print line [For readibility]
        }catch(NumberFormatException $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(Exception $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
    }

    static void addMember(Library library) {//Method to add member(s)
        try{

        //Input for Member name
        System.out.print("Please enter the member's name: ");
        String name = scanner.nextLine();
        IErrors.IsNameEmpty(name);//calling IErrors method [Check if name is null]
        System.out.println();//empty print line [For readibility]

        //Input for Member Email
        System.out.print("Please enter the member's email: ");
        String email = scanner.nextLine();
        System.out.println();//empty print line [For readibility]

        Member member = new Member(name, email);//creating object of Member class
        member.setEmail(email);//calling method to check if email is valid
        library.addMember(member);

        System.out.println("[Member added successfully]");//mesasge to terminal
        System.out.println();//empty print line [For readibility]
        }catch(Exception $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
    }
        

    //
    static void borrowBook(Library library) {
        //Input for Member email
        System.out.print("Please enter your email: ");
        try{
            String email = scanner.nextLine();
            System.out.println();//empty print line [For readibility]
    
            Member member = library.searchMemberByEmail(email);//creating an object of member class library 
    
            IErrors.IsValidEmail_BorrowBooks(library, email, member);//calling IError method [checking if email when borrowing book is valid]
        }catch(IllegalArgumentException $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
       
      
    }

    //fabricating method to search book by title
    static void searchBookByTitle(Library library) {
        //Input for book title
        System.out.print("Please enter book's title: ");
        try{
            String title = scanner.nextLine();
            System.out.println();//empty print line [For readibility]
    
            Book book = library.searchBookByTitle(title);
            assert !title.isEmpty() : "Assertion Error: title is null! [@LibrayManagementSystem, Line: 273]";//assertion for debugging
            //Conditional statement to if book exists
            if (book == null) {//triggered when book does not exist based on title
                System.out.println(String.format("Book not found. Title '%s' does not exist!", title));
            } else {//triggered when the required condition is meet
                System.out.println("Book found: " + book.title + ", " + book.author);
            }
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
      
    }

    //Creating method to search book by author
    static void searchBookByAuthor(Library library) {
        //Input for Book Author
        System.out.println("Please enter the author of the book");
        System.out.print("Enter: ");
        try{
            String author = scanner.nextLine();
        System.out.println();//empty print line [For readibility]
        
        
        Book book = library.searchBookByAuthor(author);
        assert !author.isEmpty() : "Assertion Error: author is null! [@LibrayManagementSystem, Line: 296]";//assertion for debugging
        //Conditional statement to if book exists
        if (book == null) {//triggered when book does not exist based on author
            System.out.println(String.format("Book not found. Author '%s' does not exist!", author));
        } else {//triggered when the required condition is meet
            System.out.println("Book found: " + book.title + ", " + book.author);
        }   
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
     
    }

    //Creating method to search Member by email
    static void searchMemberByEmail(Library library) {
        //Input for Member by email
        System.out.println("Please enter the email");
        System.out.print("Enter: ");
        try{
            String email = scanner.nextLine();
            System.out.println();//empty print line [For readibility]
            
            Member member = library.searchMemberByEmail(email);

            IErrors.IsValidEmail_MemberSearchByEmail(library, email, member);
        }catch(IllegalArgumentException $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }       
    }
    //Creating method to search Member by email
    static void membersBorrowedBooks(Library library) {
        //Input for Member by email
        System.out.println("Please enter the email");
        System.out.print("Enter: ");
        try{
            String email = scanner.nextLine();
            System.out.println();//empty print line [For readibility]
            Member member = library.searchMemberByEmail(email);

            IErrors.IsValidEmail_MemberLoanedBooks(library, email, member);//calling IErrors method [checking if entered email is valid]
        }catch(IllegalArgumentException $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
    }

      //creating wait method
      public static void Wait(int millisecs){
        try
        {
            TimeUnit.SECONDS.sleep(millisecs);
        }
        catch(InterruptedException $e)
        {
            System.out.println("Exception Occured: " + $e.getMessage());
        }
    }
}
