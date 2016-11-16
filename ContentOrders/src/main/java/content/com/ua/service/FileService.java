package content.com.ua.service;

import content.com.ua.entities.FileDB;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by uzer on 26.10.2016.
 */
public interface FileService extends AbstractService <FileDB> {
    public void uploadFileToAmazon(MultipartFile mpf, String path, String userOrderId);
}
