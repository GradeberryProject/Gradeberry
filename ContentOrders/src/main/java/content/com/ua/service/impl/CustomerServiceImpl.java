package content.com.ua.service.impl;

import content.com.ua.entities.Customer;
import content.com.ua.entities.DBUser;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.CustomerRepository;
import content.com.ua.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl<Customer> implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findByUser(DBUser user){
        return customerRepository.findByUser(user);
    }

}
