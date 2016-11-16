package content.com.ua.service;

import content.com.ua.entities.Subject;

public interface SubjectService  extends AbstractService <Subject>{

    public Subject findBySubject(String subject);
}
