package content.com.ua.service;


import content.com.ua.entities.Price;
import content.com.ua.entities.UserOrder;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.UserRole;
import content.com.ua.enums.WriterLevel;

import java.util.List;

public interface PriceService extends AbstractService <Price>{

    public Price findCustomerPrice(UserOrder userOrder);
    public Price findWriterPrice(UserOrder userOrder);
}
