package content.com.ua.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account extends AbstractEntity {

    private long number;

    private String currency;

    private Double sum;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "account")
    private DBUser user;

    public Account() {
        super();
    }

    public Account(long number, String currency, Double sum) {
        this.number = number;
        this.currency = currency;
        this.sum = sum;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public DBUser getDBUser() {
        return user;
    }

    public void setDBUser(DBUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", currency=" + currency +
                ", sum=" + sum +
                //   ", DBUser=" + DBUser.getLogin() +
                '}';
    }
}
