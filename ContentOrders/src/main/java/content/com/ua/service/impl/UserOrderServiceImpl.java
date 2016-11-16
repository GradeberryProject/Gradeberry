package content.com.ua.service.impl;

//import content.com.ua.dao.IUserOrderDAO;
import content.com.ua.entities.*;
import content.com.ua.enums.Status;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.UserOrderRepository;
import content.com.ua.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class UserOrderServiceImpl extends AbstractServiceImpl<UserOrder> implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    public UserOrderServiceImpl(UserOrderRepository repository) {
        super(repository);
    }

    @Override
    public List<UserOrder> findByStatus(Status status){
        return userOrderRepository.findByStatus(status);
    }

    @Override
    public boolean checkDeadline(UserOrder userOrder,Date date){
        Date currentDate=userOrder.dateToNewFormat(date,userOrder.getCustomer().getUser().getTimeZone());
        Date deadline = userOrder.stringToDate(userOrder.getDeadline(),userOrder.getCustomer().getUser().getTimeZone());
        if (date.before(deadline))
            return true;
        else return false;
    }

    @Override
    public List<UserOrder> findByWriterAndStatus(Writer writer, Status status){
        return userOrderRepository.findByWriterAndStatus(writer, status);
    }

    @Override
    public List<UserOrder> findByCustomerAndStatus(Customer customer, Status status){
        return  userOrderRepository.findByCustomerAndStatus(customer,status);
    }

    @Override
    public String generateUserOrderId(){
        String abc = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String numbers = "0123456789";
        Calendar calendar = new GregorianCalendar();
        Long ms = calendar.getTimeInMillis();
        String result = abc.charAt((int) (ms % abc.length())) + "";
        for (int i = 0; i < 6; i++) {
            result += numbers.charAt((int) (ms % numbers.length()));
            ms = (ms - ms % numbers.length()) / numbers.length();
        }
        result += abc.charAt((int) (ms % abc.length())) + "";
        return result;
    }

}
