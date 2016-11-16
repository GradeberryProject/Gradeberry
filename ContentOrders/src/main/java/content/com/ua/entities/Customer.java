package content.com.ua.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {

    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private DBUser user;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserOrder> customerOrders;

    public Customer() {
        super();
    }

    public Customer(DBUser user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DBUser getUser() {
        return user;
    }

    public void setUser(DBUser user) {
        this.user = user;
    }

    public List<UserOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<UserOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}
