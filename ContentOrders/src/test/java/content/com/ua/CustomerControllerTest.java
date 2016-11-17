package content.com.ua;

import content.com.ua.entities.Format;
import content.com.ua.entities.PaperType;
import content.com.ua.entities.Subject;
import content.com.ua.entities.UserOrder;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.Status;
import content.com.ua.enums.WriterLevel;
import content.com.ua.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Yurii on 16.11.2016.
 */
@Transactional
public class CustomerControllerTest extends AbstractControllerTest{


    //tetwtqet
    @Autowired
    private PaperTypeService paperTypeService;

    @Autowired
    private FormatService formatService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void testGetPaperTypes() throws Exception{
        String uri = "/api/customer/getPaperTypes";
        List<PaperType> expectedList = paperTypeService.getAll();
        String expectedJson = super.mapToJson(expectedList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
        //Assert.assertTrue("failure - expected HTTP body to have a value", content.trim().length() > 0);
        Assert.assertEquals("failure - expected list",expectedJson, content);
    }

    @Test
    public void testGetFormats() throws Exception{
        String uri = "/api/customer/getFormats";
        List<Format> expectedList = formatService.getAll();
        String expectedJson = super.mapToJson(expectedList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
        Assert.assertEquals("failure - expected list",expectedJson, content);
    }

    @Test
    public void testGetSubjects() throws Exception{
        String uri = "/api/customer/getSubjects";
        List<Subject> expectedList = subjectService.getAll();
        String expectedJson = super.mapToJson(expectedList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
        Assert.assertEquals("failure - expected list",expectedJson, content);
    }

    @Test
    public void testGetWriterLevels() throws Exception{
        String uri = "/api/customer/getWriterLevels";
        WriterLevel[] expectedList = WriterLevel.values();

        String expectedJson = super.mapToJson(expectedList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
        Assert.assertEquals("failure - expected list",expectedJson, content);
    }

    @Test
    public void testGetServiceTypes() throws Exception{
        String uri = "/api/customer/getServiceTypes";
        ServiceType[] expectedList = ServiceType.values();
        String expectedJson = super.mapToJson(expectedList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
        Assert.assertEquals("failure - expected list",expectedJson, content);
    }

    @Test
    @WithUserDetails("customer@gmail.com")
    public void testAddOrder() throws Exception{
        String uri = "/api/customer/add_order";
        UserOrder userOrder = new UserOrder();
        userOrder.setTopic("myTopic");
        userOrder.setPaperType(paperTypeService.getAll().get(0));
        userOrder.setSubject(subjectService.getAll().get(0));
        userOrder.setPageCount(2);
        userOrder.setDeadline("2016-11-18T23:46");
        userOrder.setFormat(formatService.getAll().get(0));
        userOrder.setInstructions("myInstr");
        userOrder.setServiceType(ServiceType.editing);
        userOrder.setWriterLevel(WriterLevel.PHD);
//        userOrder.setDate();
        userOrder.setStatus(Status.NEW);
        userOrder.setUserOrderId(userOrderService.generateUserOrderId());
//        userOrder.generateCustomerPrice(priceService.findCustomerPrice(userOrder));
//        userOrder.generateWriterPrice(priceService.findWriterPrice(userOrder));
        String requestJson = super.mapToJson(userOrder);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(requestJson))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
        Assert.assertTrue("failure - expected HTTP body to have a value", content.trim().length() > 0);
    }


    @Ignore
    @Test
    @WithUserDetails("customer@gmail.com")
    public void testUploadFile() throws Exception {
        String uri = "/api/customer/uploadFile/{userOrderId}";
        String userOrderId = "V837618V";
        MockMultipartFile file = new MockMultipartFile("test_file.txt", new FileInputStream("D:\\timezones.txt"));
        MockMultipartHttpServletRequestBuilder mockMultipartHttpServletRequestBuilder = (MockMultipartHttpServletRequestBuilder) fileUpload("/vocabularys/import").accept(MediaType.ALL).session(httpSession);
        mockMultipartHttpServletRequestBuilder.file(file);
        mockMultipartHttpServletRequestBuilder.content("whatever");

        mockMvc.perform(fileUpload(uri, userOrderId)
                .file(file))
                .andExpect(status().isUnauthorized());
        //int status = resultActions..getResponse().getStatus();
        //resultActions.andExpect(status())
        //Assert.assertEquals("failure - expected HTTP status 200", 200 , status);
    }
}
