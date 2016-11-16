package content.com.ua.service.impl;

import content.com.ua.entities.WritersWork;
import content.com.ua.repository.WritersWorkRepository;
import content.com.ua.service.WritersWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WritersWorkServiceImpl extends AbstractServiceImpl<WritersWork> implements WritersWorkService {

    @Autowired
    private WritersWorkRepository writersWorkRepository;

    @Autowired
    public WritersWorkServiceImpl (WritersWorkRepository writersWorkRepository){super(writersWorkRepository);}

}
