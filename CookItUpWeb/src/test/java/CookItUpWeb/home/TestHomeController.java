package CookItUpWeb.home;

import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestHomeController {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

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
    public void t() {
        List<User> users = new LinkedList<>();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        users.add(user);

        given(userRepository.findAll())
                .willReturn(users);
/*
        MockHttpServletResponse response = mvc.perform(

        );

 */
    }

}