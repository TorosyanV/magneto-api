package service;

import com.magneto.data.entity.UserEntity;
import com.magneto.data.repository.UserRepository;
import com.magneto.service.user.UserAlreadyExistsException;
import com.magneto.service.user.UserService;
import com.magneto.service.user.UserServiceImpl;
import com.magneto.web.model.RegistrationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vazgent on 3/14/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private UserService userService = new UserServiceImpl();


    @Test
    public void createNewAccount() throws UserAlreadyExistsException {

        // Expected objects
        String name = "Test Account";
        RegistrationRequest accountToSave = new RegistrationRequest();
        int userId = 12345;
        UserEntity persistedAccount = new UserEntity();
        persistedAccount.setId(userId);

        // Mockito expectations
        when(userRepository.createIfNotExist(any(UserEntity.class))).thenReturn(persistedAccount.getId());

        // Execute the method being tested
        int newUserId = userService.createUserIfNotExist(accountToSave);

        // Validation
        assertEquals(userId, newUserId);

        verify(userRepository).createIfNotExist(any(UserEntity.class));
    }
}
