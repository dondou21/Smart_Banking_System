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
    private double overdraftLimit = 2000; // Only for current accounts

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
        //Check the account type
        if (this.accountType.equals("Savings")) {
            System.out.println("Withdrawals are NOT allowed for Savings accounts.");
            return;
        }
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
        if (accountType.equals("Savings"))
        {
            this.balance += this.balance * interest;
            String formattedBalance = String.format("%.2f", balance);
            System.out.println("Interest applied. New balance: $" + formattedBalance);
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
        System.out.println("\n\n\nAccount Number: " + accountNum);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
        System.out.println("Account Type: " + accountType + "\n\n");
    }

     //Get the account number
    public long getAccountNum()
    {
        return accountNum;
    }

    // Get account type
    public String getAccountType() {
    return accountType;
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
            System.out.println("5. Apply Interest");
            System.out.println("6. View Account Details");
            System.out.println("0.Exit");
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
                    applyInterest();
                    break;
                case 6:
                    viewAccountDetails();
                    break;
                case 0:
                    System.out.println("Exiting... Thank You!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    //Account creation
        private static void createAccount() {
        System.out.print("Enter Account Number: ");
        long accNum = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Account Holder Name: ");
        String accHolder = scanner.nextLine();

        System.out.println("Choose Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String accType;
        if (typeChoice == 1) 
        {
            accType = "Savings";
        } else if (typeChoice == 2) 
        {
            accType = "Current";
        } else 
        {
            System.out.println("Invalid choice. Account not created.");
            return;
        }

        System.out.println("Do you want to add an initial deposit?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int response = scanner.nextInt();

        if (response == 1) 
        {
            System.out.print("Enter Initial Balance: ");
            double balance = scanner.nextDouble();
            accounts.add(new Account(accNum, accHolder, balance, accType));
        } else if (response == 2) 
        {
            accounts.add(new Account(accNum, accHolder, accType));
        } else 
        {
            System.out.println("Invalid choice. Account not created.");
            return;
        }

        System.out.println(accType + " Account Created Successfully!");
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

    //Applying Interest
   private static void applyInterest()
{
    System.out.println("Enter Account Number: ");
    long accNum = scanner.nextLong();

    Account account = findAccount(accNum);
    if (account == null)
    {
        System.out.println("Account not found!");
        return;
    } 

    //  Properly check if it's a Savings account
    if (!account.getAccountType().equalsIgnoreCase("Savings")) {
        System.out.println("Interest is only applicable for Savings accounts.");
        return;
    }

    //Check if the account has more than a year
    System.out.println("Has the account been active for more than a year?");
    System.out.println("1. Yes");
    System.out.println("2. No");
    int yearChoice = scanner.nextInt();

    if (yearChoice != 1) 
    {
        System.out.println("Interest is only applicable after one year.");
        return;
    }

    //Check if the account have already received the interest
    System.out.println("Has the interest already been received?");
    System.out.println("1. No");
    System.out.println("2. Yes");
    int interestReceivedChoice = scanner.nextInt();

    if (interestReceivedChoice == 1)
    {
        System.out.print("Enter interest rate (e.g., 0.05 for 5%): ");
        float rate = scanner.nextFloat();
        account.interest(rate);
    }
    else if (interestReceivedChoice == 2)
    {
        System.out.println("Interest not applied.");
    }
    else
    {
        System.out.println("Invalid input. Please enter 1 for 'yes' or 2 for 'no'.");
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