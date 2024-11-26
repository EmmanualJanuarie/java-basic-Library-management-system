import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public interface IErrors {
    static Scanner scanner = new Scanner(System.in);//creating an object of library Scanner
      //creating method to handle return of book warning
      static String IsReturnValid(int currDay, int returnDate){
        //creating variables for LocalDateTime
        LocalDateTime datetime = LocalDateTime.now();
        int date = datetime.getDayOfMonth();
        
        int penalty = returnDate - currDay;//creating variable for penalty
        int toPay = penalty * 50;
        if(date > returnDate){
            return "Late return. Penalty of R " + toPay;
        }else{
            return "Book returned on time.";
        }
    }

        //Condition for valid email for main email menu
        static void IsValidEmail(Library library, String email){
            //creating a list of email domains
                assert !email.isEmpty() : "Assertion Error: email is null";//assertion for debugging
                //condtion to check if email contains the required domain
                if(!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)){
                    throw new IllegalArgumentException(String.format("'%s' is not a valid email. Please enter a valid email!", email));
                }else{
                    LoginMenu.IsMember(library, email);// calling method IsMember [Checks if ADMIN / Guest]
                }
            
           
        }

         //Condition for valid email for Book return Dates
         static void IsValidEmail_ReturnDates(Library library, String email, Member member,
          int currDate, int currMonth, int currYear){
            //creating a list of email domains
                assert !email.isEmpty() : "Assertion Error: email is null";//assertion for debugging
                //condtion to check if email contains the required domain
                if(!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)){
                    throw new IllegalArgumentException(String.format("'%s' is not a valid email. Please enter a valid email!", email));
                }else{
                    //Conditional statement to if member exists
                    if (member == null) {//triggered when member does not exist based on author
                        System.out.println(String.format("Member not found. email '%s' does not exist!", email));
                    } else {//triggered when the required condition is meet
                        member.BookReturnDate(currDate, currMonth, currYear);
                    }
                }
        }

        //Condition for valid email for Book returns
        static void IsValidEmail_BookReturns(Library library, String email, Member member,int currDate){
          //creating a list of email domains
              assert !email.isEmpty() : "Assertion Error: email is null";//assertion for debugging
              //condtion to check if email contains the required domain
              if(!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)){
                  throw new IllegalArgumentException(String.format("'%s' is not a valid email. Please enter a valid email!", email));
              }else{
                 //Conditional statement to if member exists
                if (member == null) {//triggered when member does not exist based on author
                    System.out.println(String.format("Member not found. email '%s' does not exist!", email));
                } else {//triggered when the required condition is meet
                    member.BookReturnRenalty(currDate);//calling method method Class
                }
              }
      }

      //Condition for valid email for Book borrowing
      static void IsValidEmail_BorrowBooks(Library library, String email, Member member){
        //creating a list of email domains
            assert !email.isEmpty() : "Assertion Error: email is null";//assertion for debugging
            //condtion to check if email contains the required domain
            if(!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)){
                throw new IllegalArgumentException(String.format("'%s' is not a valid email. Please enter a valid email!", email));
            }else{
                //Conditional statement to chack if member exists based on email
                if (member == null) {//triggered when member does not exixts [based on email]
                    System.out.println(String.format("Member '%s' not found",email));
                    return;
                }

                //Input for Book's ISBN number
                System.out.print("Please enter the books ISBN number: ");
                String isbn = scanner.nextLine();
                System.out.println();//empty print line [For readibility]

                Book book = library.books.get(isbn);

                //conditional statement to check is book exists based on isbn number
                if (book == null) {//triggered when book does not exists
                    System.out.println(String.format("Invalid Entry!. '%s' ISBN number don't exist", isbn));
                    return;
                }

                member.borrowBook(book);//calling method from Member class
                System.out.println("[Book borrowed successfully]");//mesasge to terminal
                System.out.println();//empty print line [For readibility]
            }
    }

    //Condition for valid email for member search by email
    static void IsValidEmail_MemberSearchByEmail(Library library, String email, Member member){
        //creating a list of email domains
        assert !email.isEmpty() : "Assertion Error: email is null";//assertion for debugging
        //condtion to check if email contains the required domain
        if(!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)){
            throw new IllegalArgumentException(String.format("'%s' is not a valid email. Please enter a valid email!", email));
        }else{
            //Conditional statement to if member exists
            if (member == null) {//triggered when member does not exist based on author
                System.out.println(String.format("Member not found. email '%s' does not exist!", email));
            }else{//triggered when the required condition is meet
                //Conditional statement to if member exists
                System.out.println("Member found: " + member.name + ", " + member.email);
            }   
        }
    }

    //Condition for valid email for member search by email
    static void IsValidEmail_MemberLoanedBooks(Library library, String email, Member member){
        //creating a list of email domains
        assert !email.isEmpty() : "Assertion Error: email is null";//assertion for debugging
        //condtion to check if email contains the required domain
        if(!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", email)){
            throw new IllegalArgumentException(String.format("'%s' is not a valid email. Please enter a valid email!", email));
        }else{
            //Conditional statement to if member exists
            if (member == null) {//triggered when member does not exist based on author
                System.out.println(String.format("Member not found. email '%s' does not exist!", email));
            } else {//triggered when the required condition is meet
                member.displayBorrowedBooks();
            } 
        }
    }

    //creating a method to check if users isbn number is a interger
    static void IsNumber(String isbn){
       Pattern pattern = Pattern.compile("^[a-zA-Z&_\\.-]+$");//creating instance of pattern library
       Matcher m = pattern.matcher(isbn);//creating instance of Macher class [will check if isbn matches pattern]

       assert !isbn.isEmpty() : "Assertion Error: isbn is null";
       //Conditional statement to check if input is a Letter or special char
       if(m.matches()){
            throw new NumberFormatException(String.format("'%s' is not a valid ISBN Number. Please enter a ISBN Number!", isbn));
       }
    }

    //creating method to check if title entry  is null
    static void IsTitleNull(String title)throws Exception{
        assert !title.isEmpty() : "Assertion Error: title is null";//assertion for debugging
        if(title.isEmpty()){
            throw new Exception("title entry can't be null" + "\n");
        }
    }

    //creating method to check if author entry  is null
    static void IsAuthorNull(String author)throws Exception{
        assert !author.isEmpty() : "Assertion Error: author is null";//assertion for debugging
        if(author.isEmpty()){
            throw new Exception("author entry can't be null" + "\n");
        }
    }

     //creating method to check if isbn entry  is null
     static void IsIsbnNull(String isbn)throws Exception{
        assert !isbn.isEmpty() : "Assertion Error: isbn is null";//assertion for debugging
        if(isbn.isEmpty()){
            throw new Exception("isbn entry can't be null" + "\n");
        }
    }

    //creating method to check if isbn entry  is null
    static void IsIsAvailableNull(Boolean isAvailable)throws Exception{
        assert isAvailable != null : "Assertion Error: isAvailable is null";//assertion for debugging
        if(isAvailable == null){
            throw new Exception("isAvailable entry can't be null" + "\n");
        }
    }

     //creating method to check if isbn entry  is null
     static void IsNameEmpty(String name)throws Exception{
        assert !name.isEmpty() : "Assertion Error: name is null";//assertion for debugging
        if(name.isEmpty()){
            throw new Exception("name entry can't be null" + "\n");
        }
    }

         //creating method to check if isbn is greater than 10
         static void IsISBN_ValidSize(String isbn)throws Exception{
            //creating a char list
            char[] isbnTot = isbn.toCharArray();
            if(isbnTot.length > 10){
                throw new StringIndexOutOfBoundsException("isbn is greater then 10!" + "\n");
            }
        }

}
