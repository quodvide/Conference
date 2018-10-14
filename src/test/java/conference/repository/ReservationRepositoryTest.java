package conference.repository;

import conference.domain.ConferenceRoom;
import conference.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Test
    public void 정상_저장() {
        ConferenceRoom room = conferenceRoomRepository.findByName("A").get();
        Reservation reservation = new Reservation("MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("03:00:00"), LocalTime.parse("05:00:00"), room);
        Reservation createdReservation = reservationRepository.save(reservation);
        assertEquals(createdReservation, reservation);
    }
}