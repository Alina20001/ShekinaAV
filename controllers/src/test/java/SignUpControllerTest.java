import com.alina.shekina.repository.ResidenceRepository;
import com.alina.shekina.userDetails.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;
    private static Robot testRobot;

    public UserControllerTest() {
    }

    @BeforeClass
    public static void prepareTestData() {
        testRobot = Robot
                .email("ms.sidorov@mail.ru")
                .password("1234")
                .country("Россия")
                .name("Иван")
                .phone("+375299599904")
                .surname("Сидоров")
                .build();
    }

    @Before
    public void init() {
        PasswordEncoder = new passwordEncoder(userService);
    }
    @Test
    public void updateTest() {
        when(userService.signUp(any(Long.class))).thenReturn(testRobot);
        when(userService.saveUser(any(Robot.class))).then(returnsFirstArg());
        Robot robotForUpdate = Robot
                .builder()
                .email("ms.sidorov@mail.ru")
                .password("1234")
                .country("Россия")
                .name("Иван")
                .phone("+375299599904")
                .surname("Сидоров")
                .build();

        Robot resultRobot = userService.saveUser("ms.sidorov@mail.ru", "1234", "Россия","Иван","+375299599904","Сидоров");

        assertNotNull(saveUser);
    }
