package content.com.ua.service.impl;

import content.com.ua.entities.Account;
import content.com.ua.entities.DBUser;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.DBUserRepository;
import content.com.ua.service.AccountService;
import content.com.ua.service.DBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class DBUserServiceImpl extends AbstractServiceImpl<DBUser> implements DBUserService {

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    public DBUserServiceImpl(DBUserRepository repository) {
        super(repository);
    }

    @Autowired
    public AccountService accountService;

    @Override
    @Transactional(readOnly = true)
    public DBUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public DBUser findByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }


}
