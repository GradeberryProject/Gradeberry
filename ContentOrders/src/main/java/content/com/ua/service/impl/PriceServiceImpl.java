package content.com.ua.service.impl;

import content.com.ua.entities.DBUser;
import content.com.ua.entities.Price;
import content.com.ua.entities.UserOrder;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.UserRole;
import content.com.ua.enums.WriterLevel;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.PriceRepository;
import content.com.ua.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl extends AbstractServiceImpl<Price> implements PriceService{

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository repository) {
        super(repository);
    }

    @Override
    public Price findCustomerPrice(UserOrder userOrder){
        List<Price> prices =  priceRepository.findByUserTypeAndServiceTypeAndWriterLevel(UserRole.CUSTOMER, userOrder.getServiceType(), userOrder.getWriterLevel());
        int hours = userOrder.hours1();
        for (Price price:prices){
            if (hours<= price.getHoursTo() && hours> price.getHoursFrom())
                return price;
        }
        return null;
    }

    @Override
    public Price findWriterPrice(UserOrder userOrder){
        List<Price> prices =  priceRepository.findByUserTypeAndServiceTypeAndWriterLevel(UserRole.WRITER, userOrder.getServiceType(), userOrder.getWriterLevel());
        int hours = userOrder.hours1();
        for (Price price:prices){
            if (hours<= price.getHoursTo() && hours> price.getHoursFrom())
                return price;
        }
        return null;
    }
}
