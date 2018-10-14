package conference.web.api;

import conference.service.ReservationService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import support.test.AcceptanceTest;

import static org.junit.Assert.assertEquals;

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
}