package content.com.ua.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import content.com.ua.entities.FileDB;
import content.com.ua.repository.FileRepository;
import content.com.ua.repository.UserOrderRepository;
import content.com.ua.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl extends AbstractServiceImpl<FileDB> implements FileService {

    private static final String BUCKET_NAME = "gradeberry";
    private static final String ACCESS_KEY_ID = "AKIAJLKXAHCZLHJJRFCQ";
    private static final String SECRET_ACCESS_KEY = "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";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository){super(fileRepository);}


    @Override
    public void uploadFileToAmazon(MultipartFile mpf, String path, String userOrderId){
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        TransferManager tm = new TransferManager(credentials);
        try {
            // Or you can block and wait for the upload to finish
            Upload upload = tm.upload(BUCKET_NAME, path+mpf.getOriginalFilename(), convert(mpf));
            upload.waitForCompletion();
            this.add(new FileDB(mpf.getOriginalFilename(), userOrderRepository.findByUserOrderId(userOrderId),
                    path+mpf.getOriginalFilename()));
        } catch (AmazonClientException amazonClientException) {
            amazonClientException.printStackTrace();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
