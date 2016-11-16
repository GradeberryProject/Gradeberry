package content.com.ua.service.impl;

import content.com.ua.entities.DBUser;
import content.com.ua.entities.Writer;
import content.com.ua.repository.WriterRepository;
import content.com.ua.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl extends AbstractServiceImpl<Writer> implements WriterService{

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    public WriterServiceImpl(WriterRepository writerRepository){
        super(writerRepository);
    }

    @Override
    public Writer findByUser(DBUser user){
        return writerRepository.findByUser(user);
    }
}
