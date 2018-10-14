package conference.repository;

import conference.domain.ConferenceRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceRoomRepositoryTest {

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Test
    public void 정상_저장() {
        ConferenceRoom room = new ConferenceRoom("Hello");
        ConferenceRoom CreatedRoom = conferenceRoomRepository.save(room);
        assertEquals(CreatedRoom, room);
    }
}