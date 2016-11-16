package content.com.ua.service;

import content.com.ua.entities.DBUser;

/**
 * Created by uzer on 11.10.2016.
 */
public interface AuthorizeService {

    public String createRandomPassword();
    public String createLogin(long userListSize, String userType);
    public boolean sendPassToEmail(DBUser user, String pass);
}
