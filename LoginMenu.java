import java.util.Scanner;//importing scanner
import java.time.LocalDateTime;//importing localDateTime
public interface LoginMenu {
    Scanner scanner = new Scanner(System.in);//creating object of Scanner Library
    //default method
    static void LoginUI(Library library){
        System.out.println("***********************************");
        System.out.println("            LOGIN MENU             ");
        System.out.println("***********************************");

        //Input for user's email
        System.out.print("Please enter your email: ");
        try{//checking if users email has the required domains
            String user_email = scanner.nextLine();
            IErrors.IsValidEmail(library, user_email);//calling method from IErrors [Valid email ?]
            
        }catch(IllegalArgumentException $e){//cathcing the error
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
       
    }

    //creating method to display Admin menu
    static void AdminMenu(Library library){
        while (true) {//triggered when conditionis true
            System.out.println("***********************************");
            System.out.println("Uitenhage Library Management System");
            System.out.println("***********************************");
            System.out.println("1) Add Book" + "\n" +
                               "2) Add Member" + "\n" +
                               "3) Display Borrowed Books" + "\n" +
                               "4) Search by [Book Title]" + "\n" +
                               "5) Search by [Book Author]" + "\n" +
                               "6) Book Returns" + "\n" +
                               "7) Search Member by [Email]" + "\n" +
                               "0) Log Out"
            );
         
            System.out.print("Please select an option: ");
            String option = scanner.nextLine();
            System.out.println();//empty print line [For readibility]

            switch (option) {
                case "1"://case triggered when 1 is entered
                    LibraryManagementSystem.addBook(library);//Calling method to add book
                    break;//terminating case
                case "2"://case triggered when 2 is entered
                    LibraryManagementSystem.addMember(library);//Calling method to add member
                    break;//terminating case
                case "3"://case triggered when 3 is entered
                    LibraryManagementSystem.membersBorrowedBooks(library);
                    break;//terminating case
                case "4"://case triggered when 4 is entered
                    LibraryManagementSystem.searchBookByTitle(library);//Calling method to Search book [title]
                    break;//terminating case
                case "5"://case triggered when 5 is entered
                    LibraryManagementSystem.searchBookByAuthor(library);//Calling method to Search book [author]
                    break;//terminating case
                case "6"://case triggered when 6 is entered
                    ReturnBook(library);//calling method to display return dates
                    break;//terminating case
                case "7"://case triggered when 7 is entered
                    LibraryManagementSystem.searchMemberByEmail(library);//Calling method to Search member [email]
                    break;//terminating case
                case "0"://case triggered when 0 is entered
                    System.out.println("Logging out...");
                    System.out.println();//empty printline [for readibility]
                    LibraryManagementSystem.Wait(2);//calling LibraryManagementSystem class wait method
                    LoginUI(library);//calling the main method Login Menu  
                    break;//terminating case       
                default://terminating case
                    System.out.println(String.format("'%s' is not a valid option! Please choose from the menu",option));
                    System.out.println();//empty print line [For readibility]
                    break;//terminating case
            }
    }
}


    //creating method for Regular members
    static void RegMembers(Library library){
        while (true) {//triggered when conditionis true
            System.out.println("***********************************");
            System.out.println("             GUEST MENU            ");
            System.out.println("***********************************");
            System.out.println("1) Check Out Book" + "\n" +
                               "2) Search by [Book Title]" + "\n" +
                               "3) Return Dates" + "\n" +
                               "4) Return Books" + "\n" +
                               "0) Log Out"
            );
         
            System.out.print("Please select an option: ");
            String option = scanner.nextLine();
            System.out.println();//empty print line [For readibility]

            switch (option) {
                case "1"://case triggered when 1 is entered
                    LibraryManagementSystem.borrowBook(library);//Calling method to Check Out book
                    break;//terminating case
                case "2"://case triggered when 2 is entered
                    LibraryManagementSystem.searchBookByTitle(library);//Calling method to Search book [title]
                    break;//terminating case
                case "3"://case triggered when 3 is entered
                    ReturnDates(library);//calling method to return dates
                    break;//terminating case
                case "4"://case triggered when 4 is entered
                    ReturnBook(library);//calling method to display return dates
                    break;//terminating case
                case "0"://case triggered when 0 is entered
                    System.out.println("Logging out...");
                    System.out.println();//empty printline [for readibility]
                    LibraryManagementSystem.Wait(2);//calling LibraryManagementSystem class wait method
                    LoginUI(library);//calling the main method Login Menu          
                default://terminating case
                    System.out.println(String.format("'%s' is not a valid option! Please choose from the menu",option));
                    System.out.println();//empty print line [For readibility]
                    break;//terminating case
            }
    }
}

    //Creating method for return dates for book
    static void ReturnDates(Library library){
        LocalDateTime datetime = LocalDateTime.now();
        int currDate = datetime.getDayOfMonth();
        int currMonth = datetime.getMonthValue();
        int currYear = datetime.getYear();

        System.out.print("Please enter your email: ");

        try{
            String email = scanner.nextLine();
            System.out.println();//empty print line [For readibility]
            Member member = library.searchMemberByEmail(email);

            IErrors.IsValidEmail_ReturnDates(library, email, member, currDate, currMonth, currYear);//calling method from IErrors [checking if return date email is valid]

        }catch(IllegalArgumentException $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
       
    }

     //creating a method to return book
     static void ReturnBook(Library library){
        LocalDateTime datetime = LocalDateTime.now();
        int currDate = datetime.getDayOfMonth();

        System.out.print("Please enter your email: ");
        try{
            String email = scanner.nextLine();
            System.out.println();//empty print line [For readibility]
    
            Member member = library.searchMemberByEmail(email);
    
            IErrors.IsValidEmail_BookReturns(library, email, member, currDate);//calling method from IErrors [Checks if email from Book return is valid]
        }catch(IllegalArgumentException $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }catch(AssertionError $e){
            System.out.println($e.getMessage());//Displaying message if error is present
        }
    }

    //creating a method for members [Determine if Admin / Guest]
    static void IsMember(Library library, String email){
        //Conditional statement to chack if member exists based on email
        if (email.equals("Admin_Uth120@gmail.com")) {//triggered when member does not exixts [based on email]
            System.out.println("Directing to Admin Menu...");
            System.out.println();//empty printline [for readibility]
            LibraryManagementSystem.Wait(2);
           AdminMenu(library);//calling the Admin method
        }else{
            Member member = library.searchMemberByEmail(email);//creating an object of member class library 
            if(member == null){
                System.out.println(String.format("Email '%s' not found",email));
            }else{
                System.out.println("Directing to Guest Menu...");
                System.out.println();//empty printline [for readibility]
                LibraryManagementSystem.Wait(2);
                RegMembers(library);//calling Guest method
            }
        }
    }

}