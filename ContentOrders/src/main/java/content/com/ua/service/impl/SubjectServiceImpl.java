package content.com.ua.service.impl;

import content.com.ua.entities.Subject;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.SubjectRepository;
import content.com.ua.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectServiceImpl extends AbstractServiceImpl<Subject> implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository repository) {
        super(repository);
    }

    @Override
    public Subject findBySubject(String subject){
        return subjectRepository.findBySubject(subject);
    }
}
