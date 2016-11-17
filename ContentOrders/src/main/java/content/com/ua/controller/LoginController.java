package content.com.ua.controller;

import content.com.ua.entities.Account;
import content.com.ua.entities.Customer;
import content.com.ua.entities.DBUser;
import content.com.ua.entities.Writer;
import content.com.ua.enums.UserRole;
import content.com.ua.enums.WriterLevel;
import content.com.ua.service.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private DBUserService dbUserService;

    @Autowired
    private AuthorizeService authorizeService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MailService mailService;

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/api/customer/login/checkEmail", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> checkEmail(@RequestBody String email) {
        Map<String, String> data = new HashMap<String, String>();
        DBUser dbUser = dbUserService.findByLogin(email);
        if (dbUser != null) {
            data.put("login", dbUser.getLogin());
            data.put("pass", "required");
        } else {
            long size = dbUserService.getAll().size();
            String login_name = authorizeService.createLogin(size, "user");
            String pass = authorizeService.createRandomPassword();
            String encodePass = passwordEncoder.encodePassword(pass, null);
            DBUser newUser;
            Account account;
            newUser = new DBUser(email, encodePass, login_name, UserRole.CUSTOMER, "GMT+3");
            account = new Account(10000200, "USD", 100.0);
            accountService.add(account);
            newUser.setAccount(account);
            dbUserService.add(newUser);
            mailService.sendMail(newUser, pass);
            customerService.add(new Customer(newUser));
            data.put("login", newUser.getLogin());
            byte[] bytePass = pass.getBytes();
            String passwordBase64 = new String(Base64.encodeBase64(bytePass));
            data.put("pass", passwordBase64);
        }
        return data;
    }

    @RequestMapping("/api/customer/login/loginPage")
    public ResponseEntity<Void> authorization() {
        System.out.println("Login:  pass: ");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @RequestMapping(value="/api/customer/forgotPassword",method = RequestMethod.POST)
    public @ResponseBody Map<String, String> forgotPassword(@RequestBody String email) {
        DBUser dbUser = dbUserService.findByLogin(email);
        Map<String, String> response= new HashMap();
        if (dbUser != null) {
            String pass = authorizeService.createRandomPassword();
            String encodePass = passwordEncoder.encodePassword(pass, null);
            dbUser.setPassword(encodePass);
            dbUserService.update(dbUser);
            mailService.sendForgotPass(dbUser, pass);
            response.put("response", "Password send to your email "+email);
        }else
            response.put("response","User with email "+email+" are not registered");
        return response ;
    }
//
//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public String loginPage(HttpSession session){
//        session.setAttribute("createNewUser","false");
//        return "login";}
//
//    @RequestMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
//
//    @RequestMapping("/unauthorize")
//    public String unauthorized(Model model) {
//        User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("login", user.getUsername());
//        return "unauthorize";
//    }

//    @RequestMapping(value = "/login/checkUser", method = RequestMethod.POST)
//    public String newUserloginPage(HttpSession session,@RequestParam String page, @RequestParam String email, @RequestParam String paperType, Model model) {
//            DBUser dbUser = dbUserService.findByLogin(email);
//            if (dbUser != null) {
//                model.addAttribute("paperType", paperType);
//                model.addAttribute("login", dbUser.getLogin());
//            }
//            else{
//                long size = dbUserService.getAll().size();
//                String login = authorizeService.createLogin(size,page);
//                String pass = authorizeService.createRandomPassword();
//                String encodePass = passwordEncoder.encodePassword(pass, null);
//                DBUser newUser; Account account;
//                if(page.equals("user")) {
//                newUser = new DBUser(email, login, encodePass, UserRole.CUSTOMER, "GMT+3");
//                account = new Account(10000200, "USD", 100.0);}
//                else{
//                    newUser = new DBUser(email, login, encodePass, UserRole.WRITER, "GMT+3");
//                    account = new Account(10000300, "USD", 0.0);
//                }
//                accountService.add(account);
//                newUser.setAccount(account);
//                dbUserService.add(newUser);
//                mailService.sendMail(newUser, pass);
//                model.addAttribute("paperType", paperType);
//                model.addAttribute("login", login);
//                model.addAttribute("email", email);
//                model.addAttribute("password", pass);
//                model.addAttribute("newUser", true);
//                if(page.equals("user")) customerService.add(new Customer(newUser));
//                else writerService.add(new Writer(newUser));
//            }
//            session.setAttribute("createNewUser", "true");
//        return "login";
//    }

}
