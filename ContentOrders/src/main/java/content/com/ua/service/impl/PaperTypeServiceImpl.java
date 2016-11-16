package content.com.ua.service.impl;

import content.com.ua.entities.PaperType;
import content.com.ua.repository.AbstractRepository;
import content.com.ua.repository.PaperTypeRepository;
import content.com.ua.service.PaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaperTypeServiceImpl extends AbstractServiceImpl<PaperType> implements PaperTypeService{

    @Autowired
    private PaperTypeRepository paperTypeRepository;

    @Autowired
    public PaperTypeServiceImpl(PaperTypeRepository repository) {
        super(repository);
    }

    @Override
    public PaperType findByPaperType(String paperType){
        return paperTypeRepository.findByPaperType(paperType);
    }
}
