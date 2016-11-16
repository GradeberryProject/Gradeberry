package content.com.ua.repository;

import content.com.ua.entities.*;
import content.com.ua.enums.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends AbstractRepository <UserOrder, Long>{

    public List<UserOrder> findByStatus(Status status);
    public List<UserOrder> findBySubject(Subject subject);
    public List<UserOrder> findByFormat(Format format);
    public List<UserOrder> findByPaperType(PaperType paperType);
    public List<UserOrder> findBySubjectAndPaperType(Subject subject, PaperType paperType);
    public List<UserOrder> findBySubjectAndFormat(Subject subject, Format format);
    public List<UserOrder> findByPaperTypeAndFormat(PaperType paperType, Format format);
    public List<UserOrder> findBySubjectAndPaperTypeAndFormat(Subject subject, PaperType paperType,Format format);
    public List<UserOrder> findByWriterAndStatus(Writer writer, Status status);
    public List<UserOrder> findByCustomerAndStatus(Customer customer, Status status);
    public UserOrder findByUserOrderId(String userOrderId);
}
