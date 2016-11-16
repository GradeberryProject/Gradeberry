package content.com.ua.controller;

import content.com.ua.entities.PaperType;
import content.com.ua.service.PaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class IndexController {

    @Autowired
    public PaperTypeService paperTypeService;

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

//    @RequestMapping(value ="/paperType", method = RequestMethod.POST)
//    public @ResponseBody PaperType indexPage(@RequestBody PaperType paperType, @RequestParam("email") String email) {
//        System.out.println(email);
//        return paperType;
//    }

//    @RequestMapping(name = "/postlogin", method = RequestMethod.POST)
//    public @ResponseBody String user(@RequestBody String login) {
//        System.out.println("Login: "+login+" pass: ");
//        return login;
//    }
}

