package content.com.ua.service;

import content.com.ua.entities.DBUser;

/**
 * Created by uzer on 11.10.2016.
 */
public interface MailService {
    public void sendMail(DBUser user, String pass);
    public void sendCustomerMail(DBUser user, Long orderId);
    public void sendForgotPass(DBUser dbUser, String pass);
}
