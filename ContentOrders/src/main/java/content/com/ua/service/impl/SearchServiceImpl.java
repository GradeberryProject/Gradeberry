package content.com.ua.service.impl;


import content.com.ua.entities.Format;
import content.com.ua.entities.PaperType;
import content.com.ua.entities.Subject;
import content.com.ua.entities.UserOrder;
import content.com.ua.repository.FormatRepository;
import content.com.ua.repository.PaperTypeRepository;
import content.com.ua.repository.SubjectRepository;
import content.com.ua.repository.UserOrderRepository;
import content.com.ua.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    PaperTypeRepository paperTypeRepository;

    @Autowired
    FormatRepository formatRepository;

    @Override
    public List<UserOrder> findBySearchParameters(String subject, String paperType, String format){
        if(subject.equals("Other")&&paperType.equals("Any type")&&format.equals("Other")){
            return userOrderRepository.findAll();
        }else
        if(paperType.equals("Any type")&&format.equals("Other")){
            return userOrderRepository.findBySubject(subjectRepository.findBySubject(subject));
        }else
        if (subject.equals("Other")&&format.equals("Other")){
            return userOrderRepository.findByPaperType(paperTypeRepository.findByPaperType(paperType));
        }else
        if (subject.equals("Other")&& paperType.equals("Any type")){
            return userOrderRepository.findByFormat(formatRepository.findByFormat(format));
        }else
        if(format.equals("Other")){
            return userOrderRepository.findBySubjectAndPaperType(subjectRepository.findBySubject(subject),paperTypeRepository.findByPaperType(paperType));
        }else
        if (paperType.equals("Any type")){
            return userOrderRepository.findBySubjectAndFormat(subjectRepository.findBySubject(subject), formatRepository.findByFormat(format));
        }else
        if (subject.equals("Other")){
            return userOrderRepository.findByPaperTypeAndFormat(paperTypeRepository.findByPaperType(paperType),formatRepository.findByFormat(format));
        }else
            return userOrderRepository.findBySubjectAndPaperTypeAndFormat(subjectRepository.findBySubject(subject),paperTypeRepository.findByPaperType(paperType),formatRepository.findByFormat(format));
    }
}
