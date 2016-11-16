package content.com.ua.service;


import content.com.ua.entities.AbstractEntity;
import content.com.ua.entities.Customer;
import content.com.ua.entities.DBUser;

public interface CustomerService extends AbstractService <Customer> {
    public Customer findByUser(DBUser user);
}
