import java.util.Scanner;
import java.util.ArrayList;

class Account
{
    // Declaration of instance variables
    private long accountNum;
    private String accountHolderName;
    private double balance;
    private String accountType;
    private float interest;
    private double overdraftLimit = 0; // Only for current accounts

    //Parameterized constructor
    Account(long accountNum, String accountHolderName, double balance, String accountType)
    {
        this.accountNum = accountNum;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Constructor overloading (No initial deposit)
    Account(long accountNum, String accountHolderName, String accountType)
    {
        this.accountNum = accountNum;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.accountType = accountType;
    }

    //Deposit method
    void deposit(double amount)
    {
        //Amount validation
        if(amount > 0)
        {
            //Updating balance
            this.balance += amount;
            System.out.println("Deposit successful of: $" + amount + ". New balance: $" + balance);
        }
        else 
        {
            System.out.println("Invalid Deposit Amount.");
        }
        
    }

    //withdraw method
    void withdraw(double amount)
    {
        //Check if the balance is enough for the operation
        if((this.balance + overdraftLimit )>= amount && amount > 0)
        {
            //Deducting amount for the balance
            this.balance -= amount;
            System.out.println("Withdraw successful of $" + amount + ". Remaining balance: $" + balance);
        }
        else if(amount <= 0)
        {
            System.out.println("Sorry! You have enter invalid amount");
        }
        else
        {
            System.out.println("Soory! You have INSUFFICIENT BALANCE");
        }
    }

    //Interest method
    void interest(float interest)
    {
        if (accountType.equals("savings"))
        {
            this.balance += this.balance * interest;
            System.out.println("Interest applied. New balance: $" + balance);
        }
        else 
        {
            System.out.println("Interest is only applicable for Savings accounts.");
        }
    }

    //Balance checking
    double balance(long accountNum)
    {
        return this.balance;
    }

    //Displaying account details
    void displayDetails() 
    {
        System.out.println("Account Number: " + accountNum);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Account Type: " + accountType);
    }

     //Get the account number
    public long getAccountNum()
    {
        return accountNum;
    }


}


// Main class
public class BankingSystem {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        //Menu implematation
        while(true)
        {
            System.out.println("\nSmart Banking System:");
            System.out.println("======================\n");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View Account Details");
            System.out.println("6.Exit");
            System.out.println("\n=================\n");
            System.out.println("Enter Your Choice: ");

            //Reading the choice
            int choice = scanner.nextInt();

            //Switch case 
            switch (choice) 
            {
                case 1:
                    createAccount();
                    break;
                case 2:
                    transaction("deposit");
                    break;
                case 3:
                    transaction("withdraw");
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewAccountDetails();
                    break;
                case 6:
                    System.out.println("Exiting... Thank You!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    //Account creation
    private static void createAccount()
    {
        System.out.println("Enter Account Number: ");
        long accNum = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter Account Holder Name: ");
        String accHolder  = scanner.nextLine();

        System.out.println("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        System.out.println("Enter Account Type (Savings/Current): ");
        String accType = scanner.next();

        accounts.add(new Account(accNum, accHolder, balance, accType));
        System.out.println("Account Created Successfully!");
    }

    //Transaction method
    private static void transaction(String type)
    {
        System.out.println("Enter Account Number: ");
        long accNum = scanner.nextLong();

        Account account = findAccount(accNum);
        if (account == null) 
        {
            System.out.println("Account not found!");
            return;
        }

        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();

        if(type.equals("deposit"))
        {
            account.deposit(amount);
        }
        else
        {
            account.withdraw(amount);
        }
    }

    //Checking balance
    private static void checkBalance()
    {
        System.out.println("Enter Account Number: ");
        long accNum = scanner.nextLong();

        Account account = findAccount(accNum);
        if(account != null)
        {
            account.displayDetails();
        }
        else
        {
            System.out.println("Account no found");
        }
    }

    //Display account details
    private static void viewAccountDetails()
    {
        System.out.println("Enter Account Number: ");
        long accNum = scanner.nextLong();

        Account account = findAccount(accNum);
        if(account != null)
        {
            account.displayDetails();
        }
        else
        {
            System.out.println("Account no found");
        }
    }

    //Searching an account
    private static Account findAccount(long accountNum)
    {
        for(Account acc : accounts)
        {
            if(acc.getAccountNum() == accountNum)
            {
                return acc;
            }
        }
        return null;
    }

}