package conference.dto;

import conference.domain.ConferenceRoom;
import conference.domain.Reservation;
import conference.repository.ConferenceRoomRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReservationDtoTest {

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Test
    public void 비정상_반복예약() {
        ConferenceRoom room = new ConferenceRoom("A");
        ReservationDto dto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("00:00:00"), LocalTime.parse("01:00:00"), 0);
        List<Reservation> list = dto.toReservation(room);

        assertEquals(0, list.size());
    }

    @Test
    public void 정상_반복예약() {
        ConferenceRoom room = new ConferenceRoom("A");
        ReservationDto dto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("00:00:00"), LocalTime.parse("01:00:00"), 2);
        List<Reservation> list = dto.toReservation(room);

        assertEquals(2, list.size());
    }

    @Test
    public void 비정상_시간정보() {
        ReservationDto dto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("00:20:00"), LocalTime.parse("01:00:00"), 1);

        assertEquals(true, dto.isNotValidTime());
    }

    @Test
    public void 정상_시간정보() {
        ReservationDto dto = new ReservationDto("A", "MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("00:00:00"), LocalTime.parse("01:00:00"), 2);

        assertEquals(false, dto.isNotValidTime());
    }

}