package Ravikumar.Sujatha.AccessControlLab;
import java.util.Date;

/**
 * Created by sujatharavikumar on 9/14/16.
 */
public class Transaction {

    private String transactionType;
    private double amount;
    private Date transactionTimeStamp;

    public Transaction(String transactionType, double amount, Date transactionTimeStamp) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionTimeStamp = transactionTimeStamp;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionTimeStamp=" + transactionTimeStamp +
                '}';
    }
}
