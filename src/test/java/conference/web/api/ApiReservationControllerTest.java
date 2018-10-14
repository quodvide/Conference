package conference.web.api;

import static org.junit.Assert.*;

import conference.domain.ConferenceRoom;
import conference.dto.ReservationDto;
import conference.repository.ConferenceRoomRepository;
import conference.repository.ReservationRepository;
import conference.service.ReservationService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ApiReservationControllerTest extends AcceptanceTest {

    final String RESERVATION_URL = "/api/reservations";

    @InjectMocks
    ApiReservationController apiReservationController;

    @Mock
    ReservationService mockReservationService;

    @Test
    public void 예약리스트_가져오기() {
        assertEquals(HttpStatus.OK, apiReservationController.getReservationList("2018-10-10").getStatusCode());
    }

    @Test
    public void 정상_예약() {
        ReservationDto reservationDto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-11-10"), LocalTime.parse("05:00:00"), LocalTime.parse("07:00:00"), 2);
        assertEquals(HttpStatus.OK, apiReservationController.reserve(reservationDto).getStatusCode());
    }

    @Test
    public void 회의실_이름_비정상_예약() {
        ReservationDto reservationDto = new ReservationDto("Z", "MUZI", LocalDate.parse("2018-11-10"), LocalTime.parse("05:00:00"), LocalTime.parse("07:00:00"), 2);
        assertEquals(HttpStatus.UNAUTHORIZED, apiReservationController.reserve(reservationDto).getStatusCode());
    }

    @Test
    public void 시간_비정상_예약() {
        ReservationDto reservationDto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-11-10"), LocalTime.parse("05:00:00"), LocalTime.parse("07:10:00"), 2);
        assertEquals(HttpStatus.UNAUTHORIZED, apiReservationController.reserve(reservationDto).getStatusCode());

    }
}