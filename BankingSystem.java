import java.util.Scanner;

class BankAccount
{
    // Declaration of instance variables
    private long accountNum;
    private String accountHolderName;
    private double balance;
    private String accountType;
    private float interest;
    private double overdraftLimit = 0; // Only for current accounts

    //Parameterized constructor
    BankAccount(long accountNum, String accountHolderName, double balance, String accountType)
    {
        this.accountNum = accountNum;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Constructor overloading (No initial deposit)
    BankAccount(long accountNum, String accountHolderName, String accountType)
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
        if(accountType == "savings")
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

}


// Main class
public class BankingSystem {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("");
    }

}