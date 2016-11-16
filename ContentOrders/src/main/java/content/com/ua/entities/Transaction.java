package content.com.ua.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction extends AbstractEntity {

    @Column(name = "date", nullable = false)
    private String date;

    private Double sum;
    private String paymentPurpose;
    private Double commission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transactionsFrom")
    private Account accountFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transactionsTo")
    private Account accountTo;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = dateToString(new Date(), accountFrom.getDBUser().getTimeZone(), "yyyy-MM-dd'T'HH:mm");
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }
}
