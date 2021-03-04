import com.alina.shekina.repository.ResidenceRepository;
import com.alina.shekina.repository.UserRepository;
import com.alina.shekina.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.*;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    private static Robot testRobot;

    @BeforeClass
    public static void prepareTestData() {
        testRobot = Robot
                .id(2)
                .surname("Сидоров")
                .name("Иван")
                .country("Россия")
                .phone("+375299599904")
                .build();
    }

    @Before
    public void init() {
        userService = new UserService(userDetails);
    }
    @Test
    public void updateTest() {
        when(userDetails.findById(any(Long.class))).thenReturn(testRobot);
        when(userDetails.update(any(Robot.class))).then(returnsFirstArg());
        Robot robotForUpdate = Robot
                .builder()
                .id(2)
                .surname("Сидоров")
                .name("Иван")
                .country("Россия")
                .phone("+375299599904")
                .build();

        Robot resultRobot = userService.update(2, robotForUpdate);

        assertNotNull(resultRobot);
        assertSame(resultRobot.getId(),testRobot.getId());
        assertThat(resultRobot.getSurname()).isEqualTo(robotForUpdate.getSurname());
        assertTrue(resultRobot.getName().equals(robotForUpdate.getName()));
        assertTrue(resultRobot.getCountry().equals(robotForUpdate.getCountry()));
        assertEquals(resultRobot.getPhone(),testRobot.getPhone());
    }