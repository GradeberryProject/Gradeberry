package content.com.ua;

import content.com.ua.entities.*;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.UserRole;
import content.com.ua.enums.WriterLevel;
import content.com.ua.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final WriterService writerService,final AccountService accountService,final PriceService priceService, final CustomerService customerService,final DBUserService userService, final FormatService formatServise, final PaperTypeService paperTypeService, final SubjectService subjectService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.add(new DBUser("admin@gmail.com","5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8","admin", UserRole.ADMIN,"GMT+3"));
                DBUser userCustomer = new DBUser("customer@gmail.com","5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", "customer", UserRole.CUSTOMER,"GMT+3");
                Account account = new Account(1234432133,"USD",100.0);
                accountService.add(account);
                userCustomer.setAccount(account);
                userService.add(userCustomer);
                customerService.add(new Customer(userCustomer));
                DBUser userWriter = new DBUser("writer@gmail.com","5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8","writer",  UserRole.WRITER,"GMT+3");
                Account account2 = new Account(1237432563,"USD",0.0);
                accountService.add(account2);
                userWriter.setAccount(account2);
                userService.add(userWriter);
                writerService.add(new Writer(userWriter));
                formatServise.add(new Format("Other"));
                paperTypeService.add(new PaperType("Any type"));
                subjectService.add(new Subject("Other"));
                formatServise.add(new Format("ATL"));
                paperTypeService.add(new PaperType("essay"));
                subjectService.add(new Subject("nature"));
                formatServise.add(new Format("UTF"));
                paperTypeService.add(new PaperType("cursova"));
                subjectService.add(new Subject("business"));
                priceService.add(new Price(UserRole.CUSTOMER,ServiceType.editing,WriterLevel.PHD,24,90,10));
                priceService.add(new Price(UserRole.WRITER,ServiceType.editing,WriterLevel.PHD,24,90,5));

            }
        };
    }
}