package content.com.ua.service.impl;

import content.com.ua.entities.DBUser;
import content.com.ua.service.AuthorizeService;
import org.apache.catalina.Session;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;

@Service
public class AuthorizeServiceImpl implements AuthorizeService{

    @Override
    public String createRandomPassword() {
        char[] pattern = {'A', 'L', 'E', 'X', 'C', 'F', 'E', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        int length = 6;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int number = new Random().nextInt(pattern.length);
            char ch = pattern[number];
            builder.append(ch);
        }
        return builder.toString();
    }

    @Override
    public String createLogin(long userListSize, String userType){
        String customLogin = userType+"-";
        long number = new Random().nextInt(10000)+ userListSize;
        return customLogin+number;
    }

    @Override
    public boolean sendPassToEmail(DBUser user, String pass){


        return true;
    }
}
