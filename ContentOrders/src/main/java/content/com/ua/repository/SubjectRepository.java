package content.com.ua.repository;


import content.com.ua.entities.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends AbstractRepository<Subject,Long>{

    public Subject findBySubject(String subject);
}
