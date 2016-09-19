package Ravikumar.Sujatha.AccessControlLab;

import sun.jvm.hotspot.utilities.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Created by sujatharavikumar on 9/15/16.
 */
public class ATMTest {

    BankAccount checkingAccount = new BankAccount(9972581, BankAccount.BankAccountType.CHECKING);
    BankAccount savingsAccount = new BankAccount(9934223, BankAccount.BankAccountType.SAVINGS);
    ATM atmObject = new ATM();


    @Test
    public void openCheckingAccountTest(){
        atmObject.openAccount(checkingAccount, "Sujatha");
        assertEquals("Account holder name should be Sujatha", "Sujatha", checkingAccount.getAccountHolder());
        assertEquals("Account number must be 9972581", 9972581, checkingAccount.getAccountNumber());
        assertTrue(checkingAccount.getBalance() == 0);
        assertTrue("Account type should be Checking", (checkingAccount.getAccountType() == BankAccount.BankAccountType.CHECKING));
        assertEquals("Account status should be OPEN", BankAccount.BankAccountStatus.OPEN, checkingAccount.getAccountStatus());
        assertEquals("Overdraft prevention is Enabled for new accounts", BankAccount.OverdraftPrevention.ENABLED, checkingAccount.getPreventOverdraft());
    }


    @Test
    public void openSavingsAccountTest(){
        atmObject.openAccount(savingsAccount, "Sujatha");
        assertEquals("Account holder name should be Sujatha", "Sujatha", savingsAccount.getAccountHolder());
        assertEquals("Account number must be 9934223", 9934223, savingsAccount.getAccountNumber());
        assertTrue(savingsAccount.getBalance() == 0);
        assertTrue("Account type should be Checking", (savingsAccount.getAccountType() == BankAccount.BankAccountType.SAVINGS));
        assertEquals("Account status should be OPEN", BankAccount.BankAccountStatus.OPEN, savingsAccount.getAccountStatus());
        assertEquals("Overdraft prevention is Enabled for new accounts", BankAccount.OverdraftPrevention.ENABLED, savingsAccount.getPreventOverdraft());
    }


    @Test
    public void balanceInquiryTest(){
        checkingAccount.setBalance(200.0);
        savingsAccount.setBalance(500.0);
        assertTrue("Checking account balance should be 200", checkingAccount.getBalance() == 200);
        assertTrue("Savings account balance should be 500", savingsAccount.getBalance() == 500);
    }


    @Test
    public void isAccountOpenTest(){
        assertTrue(BankAccount.BankAccountStatus.OPEN == checkingAccount.getAccountStatus());
    }

    @Test
    public void creditTest(){
        atmObject.openAccount(checkingAccount, "Sujatha");
        atmObject.credit(checkingAccount);
    }



}
