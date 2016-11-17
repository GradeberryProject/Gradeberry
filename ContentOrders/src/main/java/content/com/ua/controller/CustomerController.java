package content.com.ua.controller;

import content.com.ua.entities.*;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.Status;
import content.com.ua.enums.WriterLevel;
import content.com.ua.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@RestController
public class CustomerController {

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private DBUserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FormatService formatService;

    @Autowired
    private PaperTypeService paperTypeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private WriterService writerService;

    @Autowired
    private FileService fileService;


    //TESTED
    @RequestMapping("/api/customer/getPaperTypes")
    public @ResponseBody List<PaperType> getPaperTypes() {
        List<PaperType> paperList = paperTypeService.getAll();
        return paperList;
    }

    //TESTED
    @RequestMapping("/api/customer/getFormats")
    public @ResponseBody List<Format> getFormats() {
        List<Format> formatList = formatService.getAll();
        return formatList;
    }

    @RequestMapping("/api/customer/getSubjects")
    public @ResponseBody List<Subject> getSubjects() {
        List<Subject> subjectList = subjectService.getAll();
        return subjectList;
    }

    @RequestMapping("/api/customer/getWriterLevels")
    public @ResponseBody WriterLevel[] getWriterLevels() {
        WriterLevel[] writerLevelsList = WriterLevel.values();
        return writerLevelsList;
    }

    @RequestMapping("/api/customer/getServiceTypes")
    public @ResponseBody ServiceType[] getServiceTypes() {
        ServiceType[] ServiceTypeList = ServiceType.values();
        return ServiceTypeList;
    }

    @RequestMapping(value = "/api/customer/add_order", method = RequestMethod.POST, consumes = {"application/json"})
    public
    Map<String, String> createUserOrder(@RequestBody UserOrder userOrder) {
        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUser(userService.findByLogin(securityUser.getUsername()));
        userOrder.setCustomer(customer);
        userOrder.setDate();
        userOrder.generateCustomerPrice(priceService.findCustomerPrice(userOrder));
        userOrder.generateWriterPrice(priceService.findWriterPrice(userOrder));
        userOrder.setUserOrderId(userOrderService.generateUserOrderId());
        userOrder.setStatus(Status.NEW);
        userOrderService.add(userOrder);
        Map<String, String> response = new HashMap<>();
        response.put("userOrderId", userOrder.getUserOrderId());
        return response;
    }


    @RequestMapping(value = "/api/customer/uploadFile/{userOrderId}", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadFile(MultipartHttpServletRequest request, @PathVariable("userOrderId") String userOrderId)
            throws ServletException, IOException {
        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUser(userService.findByLogin(securityUser.getUsername()));
        String path = "user-" + customer.getId() + "/" + userOrderId+"/";
        fileService.uploadFileToAmazon(request.getFile(request.getFileNames().next()), path, userOrderId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    @RequestMapping("/")
//    public String index(HttpSession session, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        switch (user.getRole()) {
//            case CUSTOMER:
//                if (session.getAttribute("createNewUser").equals("true")) {
//                    model.addAttribute("userName", user.getLogin());
//                    model.addAttribute("balance", user.getAccount().getSum() + "$");
//                    model.addAttribute("service", ServiceType.values());
//                    model.addAttribute("levels", WriterLevel.values());
//                    model.addAttribute("subjects", subjectService.getAll());
//                    model.addAttribute("formats", formatService.getAll());
//                    model.addAttribute("paperTypes", paperTypeService.getAll());
//                    return "order";
//                } else {
//                    model.addAttribute("userName", user.getLogin());
//                    model.addAttribute("balance", user.getAccount().getSum() + "$");
//                    Customer customer = customerService.findByUser(user);
//                    model.addAttribute("orders", customer.getCustomerOrders());
//                    return "userOrdersPage";
//                }
//            case WRITER:
//                if (session.getAttribute("createNewUser").equals("true")) {
//                    model.addAttribute("user", user);
//                    model.addAttribute("headerTitle", "REGISTRATION");
//                    model.addAttribute("levels", WriterLevel.values());
//                    return "writerRegistration";
//                } else {
//                    model.addAttribute("userName", user.getLogin());
//                    model.addAttribute("balance", user.getAccount().getSum() + "$");
//                    Writer writer = writerService.findByUser(user);
//                    model.addAttribute("orders", writer.getWriterOrders());
//                    model.addAttribute("formats", formatService.getAll());
//                    model.addAttribute("subjects", subjectService.getAll());
//                    model.addAttribute("paperTypes", paperTypeService.getAll());
//                    return "writersOrders";
//                }
//        }
//        return "admin";
//    }
//
//    @RequestMapping("/order")
//    public String orderPage(Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        model.addAttribute("userName", user.getLogin());
//        model.addAttribute("balance", user.getAccount().getSum() + "$");
//        model.addAttribute("service", ServiceType.values());
//        model.addAttribute("levels", WriterLevel.values());
//        model.addAttribute("subjects", subjectService.getAll());
//        model.addAttribute("formats", formatService.getAll());
//        model.addAttribute("paperTypes", paperTypeService.getAll());
//        return "order";
//    }
//
//    @RequestMapping("/ordersPage")
//    public String ordersPage(Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        DBUser user = userService.findByLogin(securityUser.getUsername());
//        model.addAttribute("userName", user.getLogin());
//        model.addAttribute("balance", user.getAccount().getSum() + "$");
//        Customer customer = customerService.findByUser(user);
//        model.addAttribute("userOrders", customer.getCustomerOrders());
//        return "userOrdersPage";
//    }
//
//
//
//
//
//
//    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    UserOrder update(@RequestParam String topic, @RequestParam Integer pageCount,
//                     @RequestParam String deadline, @RequestParam String instructions,
//                     @RequestParam String subject, @RequestParam String paperType,
//                     @RequestParam String format, @RequestParam String serviceType,
//                     @RequestParam String writerLevel, @RequestParam Integer resourcesCount,
//                     @RequestParam MultipartFile simpleFile, Model model) {
//        User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Customer customer = customerService.findByUser(userService.findByLogin(securityUser.getUsername()));
//        UserOrder userOrder = new UserOrder(customer);
//        FileDB fileDB = new FileDB();
//        if (simpleFile.isEmpty())
//            throw new PhotoErrorException();
//        fileDB = new FileDB(simpleFile.getOriginalFilename(), userOrder, simpleFile.getOriginalFilename());
//        userOrder.addToFiles(fileDB);
//        userOrder.addToFiles(fileDB);
//        userOrder.setInstructions(instructions);
//        userOrder.setPageCount(pageCount);
//        userOrder.setTopic(topic);
//        userOrder.setDeadline(deadline);
//        userOrder.setResourcesCount(resourcesCount);
//        userOrder.setFormat(formatService.findByFormat(format));
//        userOrder.setSubject(subjectService.findBySubject(subject));
//        userOrder.setPaperType(paperTypeService.findByPaperType(paperType));
//        userOrder.setStatus(Status.NEW);
//        userOrder.setServiceType(ServiceType.valueOf(serviceType));
//        userOrder.setWriterLevel(WriterLevel.valueOf(writerLevel));
//        userOrder.setCustomerPrice1(priceService.findCustomerPrice(userOrder));
//        userOrder.setWriterPrice1(priceService.findWriterPrice(userOrder));
//        ordersMap.put(userOrder.getId(), userOrder);
//        userOrderService.add(userOrder);
//        model.addAttribute("userName", customer.getUser().getLogin());
//        model.addAttribute("balance", customer.getUser().getAccount().getSum() + "$");
//        model.addAttribute("order", userOrder);
//        return userOrder;
//    }
//
//
//    @RequestMapping(value = "/pay", method = RequestMethod.POST)
//    public String confirmOrder(@RequestParam Long orderId, @RequestParam String confirm, Model model) {
//        String result = "";
//        UserOrder currentOrder = ordersMap.get(orderId);
//        if (confirm.equals("accept")) {
//            userOrderService.add(currentOrder);
//            accountService.withdrawFromAccount(currentOrder.getCustomer().getUser(), currentOrder.getCustomerPrice());
//            result = "Your order is accepted. " + currentOrder.getCustomerPrice() + " $ debited from your account.";
//        } else {
//            result = "Your order is rejected. Try again.";
//        }
//        model.addAttribute("result", result);
//        return ordersPage(model);
//    }
//
//    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
//    public String acceptOrder(@RequestParam Long orderId, @RequestParam String confirm, Model model) {
//        UserOrder userOrder = userOrderService.get(orderId);
//        userOrder.setStatus(Status.FINISHED);
//        // accountService.withdrawFromAccount();
//        String result = "You confirm order " + orderId + ". This order is closed.";
//        return ordersPage(model);
//    }


}
