package Ravikumar.Sujatha.AccessControlLab;

/**
 * Created by sujatharavikumar on 9/14/16.
 */
public class Customer {

    public static int customerId;
    private String customerName;
    private BankAccount[] bankAccounts;



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BankAccount[] getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccount[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
