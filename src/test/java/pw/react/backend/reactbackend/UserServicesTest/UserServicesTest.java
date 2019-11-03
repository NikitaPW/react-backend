package pw.react.backend.reactbackend.UserServicesTest;;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.backend.lab.backlab.appException.ResourceNotFoundException;
import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.services.UserServices;
import pw.react.backend.reactbackend.repository.UserRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServicesTest {

    private UserServices service;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        service = spy(new UserServices(repository));
    }

    @Test
    public void givenNotExistingUser_whenUpdateUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            service.updateUser(user);
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with id [1] not found.")));
        }
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenExecuteSaveMethod() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        service.updateUser(user);

        verify(repository, times(1)).save(eq(user));
    }
}