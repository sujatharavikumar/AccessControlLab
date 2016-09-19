package Ravikumar.Sujatha.AccessControlLab;

import java.util.Random;
import java.util.Scanner;
import java.util.Date;

/**
 * Created by sujatharavikumar on 9/14/16.
 */
public class ATM {

    int menuOption;
    static int id = 1;
    MainMenu mainMenu = new MainMenu();
    UserInput input = new UserInput();
    Random random = new Random();
    Customer customer  = new Customer();
    String accountHolderName;
    Scanner in = new Scanner(System.in);
    BankAccount checkingAccount, savingsAccount, investmentAccount;

    public void runATM() {
        boolean flag = true;

        while (flag) {

            mainMenu.atmMainMenuDisplay();
            menuOption = input.getMenuOption();

            switch (menuOption) {
                case 1:

                    openBankAccount();
                    break;
                case 2:
                    getAccountBalance();
                    break;
                case 3:
                    creditToAccount();
                    break;
                case 4:
                    debitFromAccount();
                    break;
                case 5:
                    transferFunds();
                    break;
                case 6:
                    closeBankAccount();
                    break;
                case 7:
                    checkingAccount.printTransaction();
                    break;
                case 8:
                case 9:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }


    public void openBankAccount(){
        System.out.println("Enter your name:");
        String accountHolderName = in.nextLine();

        System.out.println("Would you like to open a \n"+
                "1. Checking Account\n"+
                "2. Savings Account\n" +
                "3. Investment Account\n");
        int accountType = input.getInt();
        int accountNumber = random.nextInt(9000000) + 1000000;

        switch(accountType){
            case 1:
                checkingAccount = new BankAccount(accountNumber, BankAccount.BankAccountType.CHECKING);
                openAccount(checkingAccount, accountHolderName);
                checkingAccount.addTransactions("Open  Checking Account", 0.0, new Date());
                displayAccountDetails(checkingAccount,accountHolderName);
                break;
            case 2:
                savingsAccount = new BankAccount(accountNumber, BankAccount.BankAccountType.SAVINGS);
                openAccount(savingsAccount, accountHolderName);
                savingsAccount.addTransactions("Open savings Account", 0.0, new Date());
                displayAccountDetails(savingsAccount,accountHolderName);
                break;
            case 3:
                investmentAccount = new BankAccount(accountNumber, BankAccount.BankAccountType.INVESTMENT);
                openAccount(investmentAccount, accountHolderName);
                displayAccountDetails(investmentAccount,accountHolderName);
                break;
            default:
                System.out.println("Invalid Option");
        }

    }


    public void openAccount(BankAccount account, String accountHolder){
        account.setAccountHolder(accountHolder);
        account.setAccountStatus(BankAccount.BankAccountStatus.OPEN);
        account.setPreventOverdraft(BankAccount.OverdraftPrevention.ENABLED);
    }

    public void displayAccountDetails(BankAccount account, String accountHolder){
        System.out.println("Account Holder      : "+account.getAccountHolder());
        System.out.println("Account Number      : "+account.getAccountNumber());
        System.out.println("Account Type        : "+account.getAccountType());
        System.out.println("Account Status      : "+account.getAccountStatus());
        System.out.println("Overdraft Prevention: "+account.getPreventOverdraft());
        System.out.println("Current Balance     : "+account.getBalance());
    }

    public void getAccountBalance(){
        System.out.println("Enter Account type:");
        String accountType = in.nextLine();
        if (accountType.equalsIgnoreCase("Checking"))
            hasCheckingAccount(accountType);
        else if (accountType.equalsIgnoreCase("Savings"))
            hasSavingsAccount(accountType);
    }



    public void hasCheckingAccount(String accountType){
        if (accountType.equalsIgnoreCase(BankAccount.BankAccountType.CHECKING.toString())){
            if(checkingAccount != null)
                balanceInquiry(checkingAccount);
            else
                System.out.println("You do not have a Checking account");
        }
    }



    public void hasSavingsAccount(String accountType){
        if (accountType.equalsIgnoreCase(BankAccount.BankAccountType.SAVINGS.toString())){
            if(savingsAccount != null)
                balanceInquiry(savingsAccount);
            else
                System.out.println("You do not have a Savings account");
        }
    }



    public void balanceInquiry(BankAccount account){
        System.out.println("Enter your name:");
        String name = in.nextLine();
        if (account.getAccountHolder().equalsIgnoreCase(name)){
             if ((account.getAccountType().equals(BankAccount.BankAccountType.CHECKING)) ) {
                isAccountOpen(account);
             }
             else if (account.getAccountType().equals(BankAccount.BankAccountType.SAVINGS)){
                 isAccountOpen(account);
             }
        }
        else{
            System.out.println("You do not have an account with our bank.");
        }
    }



    public void isAccountOpen(BankAccount account){
        if(account.getAccountStatus().equals(BankAccount.BankAccountStatus.OPEN)) {
            System.out.println(" Balance: " + account.getBalance());
        }
        else
            System.out.println("You account is closed");
    }



    public void creditToAccount(){

        System.out.println("To which account do you want to deposit? Checking/Savings");
        String accountType = in.nextLine();
        if(accountType.equalsIgnoreCase("Checking")) {
            if (checkingAccount != null) {
                credit(checkingAccount);
            }
            else
                System.out.println("You do not have a checking account");
        }
        else if (accountType.equalsIgnoreCase("Savings")) {
            if(savingsAccount != null) {
                credit(savingsAccount);
            }
            else
                System.out.println("You do not have a savings account");
        }

    }



    public void credit(BankAccount account){
        if (account.getAccountStatus() == BankAccount.BankAccountStatus.OPEN){
            System.out.println("How much do you want to deposit? ");
            double amtToBeCredited = in.nextDouble();
            double updatedBalance = account.getBalance() + amtToBeCredited;
            account.setBalance(updatedBalance);
            account.addTransactions("Credit", amtToBeCredited, new Date());
            System.out.println("Amount credited");

        }
        else
            System.out.println("You cannot deposit money into a closed account");
    }




    public void debitFromAccount(){

        System.out.println("From which account do you want to withdraw the money from? Checking/Savings");
        String accountType = in.nextLine();

        if (accountType.equalsIgnoreCase("Checking")){
            if (checkingAccount != null) {
                //summary.setAccountType("CHECKING");
                debit(checkingAccount);
            }
            else
                System.out.println("You do not have a savings account");
        }

        else if (accountType.equalsIgnoreCase("Savings")) {
            if(savingsAccount != null) {
                //summary.setAccountType("SAVINGS");
                debit(savingsAccount);
            }
            else
                System.out.println("You do not have a savings account");
        }

    }

    public void debit(BankAccount account) {
        if (account.getAccountStatus() == BankAccount.BankAccountStatus.OPEN) {
            System.out.println("How much do you want to withdraw?");
            double amtToBeWithdrawn = in.nextDouble();
            if (amtToBeWithdrawn <= account.getBalance()){
                System.out.println("Withdrawal approved");
                double updatedBalance = account.getBalance() - amtToBeWithdrawn;
                account.setBalance(updatedBalance);
                account.addTransactions("Debit", amtToBeWithdrawn, new Date());
                //summary.setTransactionTimeStamp(new Date());
                //summary.setTransaction("Debit Transaction");
            }
            else{
                System.out.println("You do not have enough balance. Withdrawal declined");
            }

        }
        else
            System.out.println("You cannot withdraw from a closed account");

    }



    public void transferFunds(){
        System.out.println("Do you want to transfer funds from Checking to Savings? ");
        String yesOrNoString = in.nextLine();

        if (yesOrNoString.equalsIgnoreCase("yes")) {
            System.out.println("How much do you want to transfer?");
            double amtToTransfer = in.nextDouble();
            if((checkingAccount!= null) && (savingsAccount!= null) &&
                    (checkingAccount.getAccountStatus().equals(BankAccount.BankAccountStatus.OPEN))&&
                    (savingsAccount.getAccountStatus().equals(BankAccount.BankAccountStatus.OPEN)))
                transfer(checkingAccount, savingsAccount, amtToTransfer);
            else
                System.out.println("You don't have either a checking / Savings account");
        }
        else if (yesOrNoString.equalsIgnoreCase("no")) {
            System.out.println("How much do you want to transfer?");
            double amtToTransfer = in.nextDouble();
            if((checkingAccount!= null) && (savingsAccount!= null) &&
                    (checkingAccount.getAccountStatus().equals(BankAccount.BankAccountStatus.OPEN))&&
                    (savingsAccount.getAccountStatus().equals(BankAccount.BankAccountStatus.OPEN)))
                transfer(checkingAccount, savingsAccount, amtToTransfer);
            else
                System.out.println("You don't have either a checking / Savings account");
        }

    }



    public void transfer(BankAccount account1, BankAccount account2, double amount){
        if (account1.getBalance() >= amount){
            double updatedAccount1Balance = account1.getBalance() - amount;
            account1.setBalance(updatedAccount1Balance);
            double updatedAccount2Balance = account2.getBalance() + amount;
            account2.setBalance(updatedAccount2Balance);
            System.out.println("Transfer successful");
        }
        else{
            System.out.println("You do not have sufficient balance");
        }
    }


    public void closeBankAccount(){
        System.out.println("Are you sure you want to close your account?");
        String yesOrNo = in.nextLine();

        if(yesOrNo.equalsIgnoreCase("yes")){
            System.out.println("Which account do u want to close?");
            String accountType = in.nextLine();
            if (accountType.equalsIgnoreCase("Checking") && (checkingAccount != null))
                checkingAccount.setAccountStatus(BankAccount.BankAccountStatus.CLOSED);
            else if (accountType.equalsIgnoreCase("Savings") && (savingsAccount != null))
                savingsAccount.setAccountStatus(BankAccount.BankAccountStatus.CLOSED);
        }
        System.out.println("Account Closed");
    }




}




