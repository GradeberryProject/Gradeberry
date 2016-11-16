package content.com.ua.entities;

import content.com.ua.enums.UserRole;

import javax.persistence.*;

@Entity
public class DBUser extends AbstractEntity {

    private String login;

    private String loginName;

    private String password;

    private String timeZone;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Writer writer;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    private Account account;

    public DBUser() {
        super();
    }

    public DBUser(String login, String password, String loginName, UserRole role, String timeZone) {
        this.loginName = loginName;
        this.login = login;
        this.password = password;
        this.role = role;
        this.timeZone = timeZone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
