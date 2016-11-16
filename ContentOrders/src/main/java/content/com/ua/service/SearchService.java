package content.com.ua.service;


import content.com.ua.entities.Format;
import content.com.ua.entities.PaperType;
import content.com.ua.entities.Subject;
import content.com.ua.entities.UserOrder;

import java.util.List;

public interface SearchService {

    public List<UserOrder> findBySearchParameters(String subject, String paperType, String format);

}
