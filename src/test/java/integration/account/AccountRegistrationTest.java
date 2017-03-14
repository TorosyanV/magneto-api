package integration.account;

import com.magneto.MagnetoApplication;
import com.magneto.config.ApiError;
import com.magneto.web.model.RegistrationRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by vazgent on 3/14/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagnetoApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class AccountRegistrationTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void registerWithValidUserNameAndPasswordWillSuccess() {

        RegistrationRequest requestData = new RegistrationRequest();
        requestData.setEmail("vazg@mail.com");
        requestData.setName("Vazgen");
        requestData.setPassword("123456");
        ResponseEntity responseEntity = restTemplate.postForEntity("/register", requestData, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void registerWithEmptyEmailWillReturnError() {

        RegistrationRequest requestData = new RegistrationRequest();
        requestData.setName("Vazgen");
        requestData.setPassword("123456");
        ResponseEntity<ApiError> responseEntity = restTemplate.postForEntity("/register", requestData, ApiError.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertTrue("no error messages when passing empty email ", responseEntity.getBody().getErrors().get(0).contains("Email field is required."));
    }

    @Test
    public void registerWithInvalidEmailWillReturnError() {

        RegistrationRequest requestData = new RegistrationRequest();
        requestData.setName("Vazgen");
        requestData.setPassword("123456");
        requestData.setEmail("invalidemail");
        ResponseEntity<ApiError> responseEntity = restTemplate.postForEntity("/register", requestData, ApiError.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertTrue("no error messages when passing invalid email", responseEntity.getBody().getErrors().get(0).contains("Invalid mail format."));
    }
}
