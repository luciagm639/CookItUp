package CookItUpWeb.data.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestUserController {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jsonUser;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test

    public void Given_UserIsTheCurrentUser_Then_currentUser_ShouldReturn_TheCurrentUser() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        assertEquals(userController.currentUser(session), user);
    }

    @Test
    public void Given_UserIsNull_Then_currentUser_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();

        assertEquals(userController.currentUser(session), null);
    }

    @Test
    public void Given

    @RequestMapping(path="current")
    public @ResponseBody
    User currentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
