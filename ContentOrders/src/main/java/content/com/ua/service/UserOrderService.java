package content.com.ua.service;

import content.com.ua.entities.*;
import content.com.ua.enums.Status;

import java.util.Date;
import java.util.List;


public interface UserOrderService extends AbstractService <UserOrder>{

    public List<UserOrder> findByStatus(Status status);
    public List<UserOrder> findByWriterAndStatus(Writer writer, Status status);
    public List<UserOrder> findByCustomerAndStatus(Customer customer, Status status);
    public boolean checkDeadline(UserOrder userOrder,Date date);
    public String generateUserOrderId();
}
