package content.com.ua.controller;

import content.com.ua.entities.*;
import content.com.ua.enums.Status;
import content.com.ua.enums.WriterLevel;
import content.com.ua.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
public class WriterController {

    @Autowired
    DBUserService userService;

    @Autowired
    UserOrderService userOrderService;

    @Autowired
    WriterService writerService;

    @Autowired
    SearchService searchService;

    @Autowired
    FileService fileService;

    @Autowired
    private FormatService formatService;

    @Autowired
    private PaperTypeService paperTypeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    WritersWorkService writersWorkService;

    @Autowired
    MailService mailService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

//    @RequestMapping("/writer")
//    public String writerPage() {
//        return "writer";
//    }
//
//    @RequestMapping(value = "/writer", method = RequestMethod.POST)
//    public String writerIndexPage() {
//        return "writer";
//    }
//
//    @RequestMapping(value = "/writerRegistration", method = RequestMethod.POST)
//    public String writerRegistPage(String firstName, String lastName, String city, String writerLevel, String phone, String skype,
//                                   String login, String passwordOld, String passwordNew, String passwordNew2, String submit, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        Writer writer = writerService.findByUser(user);
//        if (submit.equals("Save")) {
//            writer.setAcademicDegree(WriterLevel.valueOf(writerLevel));
//            writer.setCity(city);
//            writer.setFirstName(firstName);
//            writer.setLastName(lastName);
//            writer.setPhone(phone);
//            writer.setSkypeName(skype);
//            writerService.update(writer);
//            model.addAttribute("newCount",userOrderService.findByWriterAndStatus(writer,Status.NEW).size());
//            model.addAttribute("inProgCount",userOrderService.findByWriterAndStatus(writer,Status.IN_PROGRESS).size());
//            model.addAttribute("auditCount",userOrderService.findByWriterAndStatus(writer,Status.AUDIT).size());
//            model.addAttribute("paidCount",userOrderService.findByWriterAndStatus(writer,Status.PAID).size());
//            model.addAttribute("finishedCount",userOrderService.findByWriterAndStatus(writer,Status.FINISHED).size());
//            model.addAttribute("writer", writer);
//        } else {
//            if (!login.equals(user.getLogin())) {
//                user.setLogin(login);
//            }
//            if (!passwordEncoder.encodePassword(passwordOld, null).equals(user.getPassword())) {
//                user.setPassword(passwordEncoder.encodePassword(passwordNew, null));
//                model.addAttribute("result", "You must enter with new login and password!");
//                return "login";
//            } else {
//                model.addAttribute("result", "Wrong password!");
//                return "writerRegistration";
//            }
//        }
//        return "writerProfile";
//    }
//
//    @RequestMapping("/editPassword")
//    public String editPassword(Model model){
//        model.addAttribute("editPassword",model);
//        return editWriterProfile("EDIT PERSONAL DATA",model);
//    }
//
//    @RequestMapping("/writerProfile")
//    public String writerProfile(Model model){
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        Writer writer = writerService.findByUser(user);
//        model.addAttribute("newCount",userOrderService.findByWriterAndStatus(writer,Status.NEW).size());
//        model.addAttribute("inProgCount",userOrderService.findByWriterAndStatus(writer,Status.IN_PROGRESS).size());
//        model.addAttribute("auditCount",userOrderService.findByWriterAndStatus(writer,Status.AUDIT).size());
//        model.addAttribute("paidCount",userOrderService.findByWriterAndStatus(writer,Status.PAID).size());
//        model.addAttribute("finishedCount",userOrderService.findByWriterAndStatus(writer,Status.FINISHED).size());
//        model.addAttribute("writer", writer);
//        return "writerProfile";
//    }
//
//    @RequestMapping("/editWriterProfile")
//    public String editWriterProfile(String submit,Model model){
//        model.addAttribute("headerTitle",submit);
//        return writerRegistrationPage(model);
//    }
//
//    @RequestMapping("/writerRegistrationPage")
//    public String writerRegistrationPage(Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        model.addAttribute("user", user);
//        model.addAttribute("levels", WriterLevel.values());;
//        return "writerRegistration";
//    }
//
//    @RequestMapping(value = "/newOrders", method = RequestMethod.POST)
//    public String newOrdersPage() {
//        return "newOrders";
//    }
//
//    @RequestMapping("/writersOrders")
//    public String writersOrders(Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        Writer writer = writerService.findByUser(user);
//        model.addAttribute("userName", user.getLogin());
//        model.addAttribute("balance", user.getAccount().getSum() + "$");
//        model.addAttribute("orders", writer.getWriterOrders());
//        model.addAttribute("formats", formatService.getAll());
//        model.addAttribute("subjects", subjectService.getAll());
//        model.addAttribute("paperTypes", paperTypeService.getAll());
//        return "writersOrders";
//    }
//
//    @RequestMapping("/newOrders")
//    public String newOrdersPage(Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        model.addAttribute("userName", user.getLogin());
//        model.addAttribute("balance", user.getAccount().getSum() + "$");
//        model.addAttribute("subjects", subjectService.getAll());
//        model.addAttribute("formats", formatService.getAll());
//        model.addAttribute("paperTypes", paperTypeService.getAll());
//        model.addAttribute("orders", userOrderService.findByStatus(Status.NEW));
//        return "newOrders";
//    }
//
//
//    @RequestMapping(value = "/orderGetting", method = RequestMethod.POST)
//    public String orderGetting(@RequestParam Long orderId, @RequestParam String confirm, @RequestParam String pageFrom, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Writer writer = writerService.findByUser(userService.findByLogin(securityUser.getUsername()));
//        UserOrder userOrder = userOrderService.get(orderId);
//        if (confirm.equals("getOrder")) {
//            userOrder.setWriter(writer);
//            userOrder.setStatus(Status.IN_PROGRESS);
//            userOrderService.update(userOrder);
//            model.addAttribute("result", "New order was added to your works! Deadline: " + userOrder.printDeadline());
//            return writersOrders(model);
//        } else {
//            return (getOrdersDetails(orderId, pageFrom, model));
//        }
//    }
//
//    @RequestMapping(value = "/searchNewOrders", method = RequestMethod.POST)
//    public String searchNewOrders(@RequestParam String subject, @RequestParam String paperType, @RequestParam String format,@RequestParam String pageFrom, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        model.addAttribute("userName", user.getLogin());
//        model.addAttribute("balance", user.getAccount().getSum() + "$");
//        model.addAttribute("subjects", subjectService.getAll());
//        model.addAttribute("formats", formatService.getAll());
//        model.addAttribute("paperTypes", paperTypeService.getAll());
//        model.addAttribute("orders", searchService.findBySearchParameters(subject, paperType, format));
//        model.addAttribute("result", "Search results by Subject = " + "<b>"+subject +"</b>"+ "   Paper type = " + "<b>"+paperType+"</b>" + "   Format = " + "<b>"+format+"</b>");
//        if (pageFrom.equals("newOrders"))
//        return "newOrders";
//        else return "writersOrders";
//    }
//
//    @RequestMapping(value = "/orderProcessing", method = RequestMethod.POST)
//    public String orderProcessing(@RequestParam Long orderId, @RequestParam MultipartFile resultFile, @RequestParam String confirm, @RequestParam String pageFrom, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Writer writer = writerService.findByUser(userService.findByLogin(securityUser.getUsername()));
//        UserOrder userOrder = userOrderService.get(orderId);
//        if (confirm.equals("sendWork")) {
//            if (resultFile.isEmpty())
//                throw new PhotoErrorException();
//            Date doneDate = new Date();
//            if (userOrderService.checkDeadline(userOrder, doneDate)) {
//                FileDB fileDB;
//                fileDB = new FileDB(resultFile.getOriginalFilename(), userOrder, resultFile.getOriginalFilename());
//                fileService.add(fileDB);
//                WritersWork work = new WritersWork();
//                work.setResultFileDB(fileDB);
//                work.setStatus(Status.AUDIT);
//                work.setWriter(writer);
//                work.setDoneDate(doneDate);
//                work.setOrder(userOrder);
//                writersWorkService.add(work);
//                userOrder.setDoneWork(work);
//                userOrder.setStatus(Status.AUDIT);
//                userOrderService.update(userOrder);
//                mailService.sendCustomerMail(userOrder.getCustomer().getUser(),userOrder.getId());
//                model.addAttribute("result", "Your work was added! It will be audit by redactor.");
//            } else {
//                writer.setRating(0.);
//                model.addAttribute("result", "Deadline was ! Money was returned to customer! You can't add done work!");
//            }
//            return writersOrders(model);
//        } else {
//            return (getOrdersDetails(orderId, pageFrom, model));
//        }
//    }
//
//    @RequestMapping(value = "/redirectBack", method = RequestMethod.POST)
//    public String redirectBack(@RequestParam String pageBack, @RequestParam Long orderId, Model model) {
//        if (pageBack.equals("newOrders")) {
//            return newOrdersPage(model);
//        }else if (pageBack.equals("writersOrders")){
//            return writersOrders(model);
//        } else{
//            DBUser user= userOrderService.get(orderId).getCustomer().getUser();
//            model.addAttribute("userName",user.getLogin());
//            model.addAttribute("balance", user.getAccount().getSum()+"$");
//            model.addAttribute("userOrders",userOrderService.get(orderId).getCustomer().getCustomerOrders());
//            return "userOrdersPage";
//        }
//    }
//
//    @RequestMapping(value = "/orderDetails", method = RequestMethod.POST)
//    public String getOrdersDetails(@RequestParam Long orderId, @RequestParam String pageFrom, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        UserOrder selectedOrder = userOrderService.get(orderId);
//        model.addAttribute("pageBack", pageFrom);
//        model.addAttribute("userOrder", selectedOrder);
//        return "ordersDetails";
//    }

}
