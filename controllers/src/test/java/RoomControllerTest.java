import com.alina.shekina.repository.ResidenceRepository;
import com.alina.shekina.repository.TypeRoomRepository;
import com.alina.shekina.repository.UserRepository;
import com.alina.shekina.services.RoomService;
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
    private RoomService roomService;
    private ResidenceRepository residenceRepository;
    private static Robot testRobot;

    public UserControllerTest() {
    }

    @BeforeClass
    public static void prepareTestData() {
        testRobot = Robot
                .user(3)
                .roomId(7)
                .checkInDate("2020-12-16")
                .checkOutDate("2020-12-18")
                .build();
    }

    @Before
    public void init() {
        residenceRepository = new ResidenceRepository(roomService);
    }
    @Test
    public void updateTest() {
        when(roomService.findByUser(any(Long.class))).thenReturn(testRobot);
        when(roomService.bookRoom(any(Robot.class))).then(returnsFirstArg());
        Robot robotForUpdate = Robot
                .builder()
                .user(3)
                .roomId(7)
                .checkInDate("2020-12-16")
                .checkOutDate("2020-12-18")
                .build();

        Robot resultRobot = roomService.bookRoom(3, robotForBook);

        assertNotNull(resultRobot);
        assertSame(resultRobot.getUser(),testRobot.getRoomId());
        assertThat(resultRobot.getRoomId()).isEqualTo(robotForBook.getRoomId());
        assertTrue(resultRobot.getcheckInDate().equals(robotForBook.getcheckInDate()));
        assertTrue(resultRobot.getcheckOutDate().equals(robotForBook.getcheckOutDate()));
    }