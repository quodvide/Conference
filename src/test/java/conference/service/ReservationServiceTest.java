package conference.service;

import conference.dto.ReservationDto;
import conference.repository.ConferenceRoomRepository;
import conference.repository.ReservationRepository;
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
public class ReservationServiceTest {

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @Test
    public void 정상_예약성공() {
        ReservationDto reservationDto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("05:00:00"), LocalTime.parse("07:00:00"), 2);
        assertEquals(reservationService.reserve(reservationDto), true);
    }

    @Test
    public void 방이름_비정상_예약실패() {
        ReservationDto reservationDto = new ReservationDto("K", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("05:00:00"), LocalTime.parse("07:00:00"), 2);
        assertEquals(reservationService.reserve(reservationDto), false);
    }

    @Test
    public void 시간_비정상_예약실패() {
        ReservationDto reservationDto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("05:10:00"), LocalTime.parse("07:00:00"), 2);
        assertEquals(reservationService.reserve(reservationDto), false);
    }
}