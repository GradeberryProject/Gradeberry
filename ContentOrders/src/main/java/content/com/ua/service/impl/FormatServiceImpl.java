package content.com.ua.service.impl;

import content.com.ua.entities.Format;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.FormatRepository;
import content.com.ua.service.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormatServiceImpl extends AbstractServiceImpl<Format> implements FormatService{

    @Autowired
    private FormatRepository formatRepository;

    @Autowired
    public FormatServiceImpl(FormatRepository repository) {
        super(repository);
    }

    @Override
    public Format findByFormat(String format){
        return formatRepository.findByFormat(format);
    }
}
