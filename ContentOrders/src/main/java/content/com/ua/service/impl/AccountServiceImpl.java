package content.com.ua.service.impl;

import content.com.ua.entities.Account;
import content.com.ua.entities.DBUser;
import content.com.ua.repository.AccountRepository;
import content.com.ua.repository.DBUserRepository;
import content.com.ua.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl extends AbstractServiceImpl<Account> implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void withdrawFromAccount(DBUser user, double sum){
        Account account= user.getAccount();
        if (account.getSum()>=sum) {
            account.setSum(account.getSum() - sum);
            update(account);
        }
    }
}
