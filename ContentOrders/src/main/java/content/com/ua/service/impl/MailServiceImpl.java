package content.com.ua.service.impl;

import content.com.ua.entities.DBUser;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import content.com.ua.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(final DBUser user, final String pass){
      //  JavaMailSender mailSender = new JavaMailSenderImpl();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getLogin()));
                mimeMessage.setSubject("Welcome to gradeberry.com!");
                mimeMessage.setFrom(new InternetAddress("content.com.ua@gmail.com"));
                mimeMessage.addHeaderLine("Welcome to gradeberry.com!");
                mimeMessage.setText( "Hello user "+user.getLoginName()+"!"+
                        "\n\nYour login is " + user.getLogin() + "\n\nYour password: "
                                + pass + "\n\nThanks for choosing gradeberry.com!");
            }
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendForgotPass(final DBUser dbUser, final String pass){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(dbUser.getLogin()));
                mimeMessage.setSubject("Password recovery in gradeberry.com!");
                mimeMessage.setFrom(new InternetAddress("content.com.ua@gmail.com"));
                mimeMessage.addHeaderLine("Password recovery in gradeberry.com!");
                mimeMessage.setText( "Hello user "+dbUser.getLoginName()+"!"+
                        "\n\nYour new password is "
                        + pass + "\n\nThanks for choosing gradeberry.com!");
            }
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendCustomerMail(final DBUser user, final Long orderId){
        //  JavaMailSender mailSender = new JavaMailSenderImpl();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getLogin()));
                mimeMessage.setSubject("Order completed");
                mimeMessage.setFrom(new InternetAddress("content.com.ua@gmail.com"));
                mimeMessage.addHeaderLine("gradeberry.com");
                mimeMessage.setText(
                        "Dear, "+user.getLogin()+"! Your order (ID "+orderId+" )" +"is completed, wait for verification. <br/> Best regards! <br/> gradeberry.com!");
            }
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

//    public void sendCustomerMail(final DBUser user, final Long orderId){
//        //  JavaMailSender mailSender = new JavaMailSenderImpl();
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//
//                mimeMessage.setRecipient(Message.RecipientType.TO,
//                        new InternetAddress(user.getLogin()));
//                mimeMessage.setSubject("Order completed");
//                mimeMessage.setFrom(new InternetAddress("content.com.ua@gmail.com"));
//                mimeMessage.addHeaderLine("gradeberry.com");
//                mimeMessage.setText(
//                        "Dear, "+user.getLogin()+"! Your order (ID "+orderId+" )" +"is completed, wait for verification. <br/> Best regards! <br/> gradeberry.com!");
//            }
//        };
//
//        try {
//            mailSender.send(preparator);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }
}
