package Ravikumar.Sujatha.AccessControlLab;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sujatharavikumar on 9/14/16.
 */
public class BankAccount {
    enum BankAccountType {CHECKING, SAVINGS, INVESTMENT}
    enum BankAccountStatus {OPEN, CLOSED, OFAC}
    enum OverdraftPrevention {ENABLED, DISABLED}

    private int accountNumber;
    private double balance;
    private String accountHolder;
    public ArrayList<Transaction> transactionSummary = new ArrayList<Transaction>();
    private BankAccountType accountType;
    private BankAccountStatus accountStatus;
    private OverdraftPrevention preventOverdraft;


    public BankAccount(int accountNumber, BankAccountType accountType){
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }


    public BankAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(BankAccountType accountType) {
        this.accountType = accountType;
    }

    public BankAccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(BankAccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public OverdraftPrevention getPreventOverdraft() {
        return preventOverdraft;
    }

    public void setPreventOverdraft(OverdraftPrevention preventOverdraft) {
        this.preventOverdraft = preventOverdraft;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }


    public void addTransactions(String transactionType, double amount, Date date){
        transactionSummary.add(new Transaction(transactionType,amount,date));
    }


    public void printTransaction(){
        for (int i=0; i< transactionSummary.size(); i++)
            System.out.println(transactionSummary.get(i).toString());

    }
}
