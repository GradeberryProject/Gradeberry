package content.com.ua.service;


import content.com.ua.entities.PaperType;

public interface PaperTypeService extends AbstractService <PaperType>{

    public PaperType findByPaperType(String paperType);
}
