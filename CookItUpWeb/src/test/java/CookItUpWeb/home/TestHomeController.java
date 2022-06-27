package CookItUpWeb.home;

import CookItUpWeb.auxiliary.CopyFolder;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestHomeController {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CopyFolder copyFolder;

    @InjectMocks
    private HomeController homeController;

    private JacksonTester<User> jsonUser;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(homeController)
                .build();
    }

    @Test
    public void Given_TheLogInIsCorrect_The_logIn_ShouldReturn_AnEmptyStringAndPutTheUserInTheSession() {
        MockHttpSession session = new MockHttpSession();

        List<User> users = new LinkedList<>();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        users.add(user);

        given(userRepository.findAll())
                .willReturn(users);

        String message = homeController.logIn(session, name, password);

        assertEquals("", message);
        assertEquals(user, session.getAttribute("user"));

    }

    @Test
    public void Given_NameIsEmpty_Then_logIn_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        String name = "";
        String password = "1234";

        String message = homeController.logIn(session, name, password);

        assertEquals("The submitted form is incomplete", message);
    }

    @Test
    public void Given_PasswordIsEmpty_Then_logIn_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        String name = "name";
        String password = "";

        String message = homeController.logIn(session, name, password);

        assertEquals("The submitted form is incomplete", message);
    }

    @Test
    public void Given_PasswordIncorrect_Then_logIn_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        String name = "name";
        String password = "123";

        User user = new User();
        user.setName(name);
        user.setPassword("1234");

        List<User> users = new ArrayList<>();

        users.add(user);

        given(userRepository.findAll())
                .willReturn(users);

        String message = homeController.logIn(session, name, password);

        assertEquals("The password is wrong", message);
    }

    @Test
    public void Given_UserNotExist_Then_logIn_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        String name = "name";
        String password = "123";

        List<User> users = new ArrayList<>();

        given(userRepository.findAll())
                .willReturn(users);

        String message = homeController.logIn(session, name, password);

        assertEquals("User not found", message);
    }

    @Test
    public void Given_NameIsEmpty_Then_signUp_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        String name = "";
        String password = "1234";

        String message = homeController.signUp(session, name, password);

        assertEquals("The submitted form is incomplete", message);
    }

    @Test
    public void Given_PasswordIsEmpty_Then_signUp_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        String name = "name";
        String password = "";

        String message = homeController.signUp(session, name, password);

        assertEquals("The submitted form is incomplete", message);
    }

    @Test
    public void Given_AnAssignedUserName_Then_signUp_ShouldReturn_AStringWithTheErrorMessage() {
        MockHttpSession session = new MockHttpSession();

        List<User> users = new LinkedList<>();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        users.add(user);

        given(userRepository.findAll())
                .willReturn(users);

        String message = homeController.signUp(session, name, password);

        assertEquals("The user name you are trying to get is already assigned", message);
    }

    @Test
    public void Given_TheSignUpIsCorrect_Then_signUp_ShouldReturn_AnEmptyString() {
        MockHttpSession session = new MockHttpSession();

        List<User> users = new LinkedList<>();

        String name = "pepe";
        String password = "password";

        given(userRepository.findAll())
                .willReturn(users);

        String message = homeController.signUp(session, name, password);

        assertEquals("", message);
    }

}