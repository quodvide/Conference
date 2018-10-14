package conference.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReservationTest {

    ConferenceRoom roomA;
    ConferenceRoom roomB;
    Reservation origin;
    List<Reservation> targetList;

    @Before
    public void setUp() throws Exception {
        roomA = new ConferenceRoom("A");
        origin = new Reservation("MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("01:00:00"), LocalTime.parse("02:00:00"), roomA);
    }

    @Test
    public void 유효한_예약() {
        targetList = new ArrayList<>();
        Reservation targetFirst = new Reservation("MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("00:30:00"), LocalTime.parse("01:00:00"), roomA);
        Reservation targetSecond = new Reservation("MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("02:00:00"), LocalTime.parse("03:00:00"), roomA);
        targetList.add(targetFirst);
        targetList.add(targetSecond);

        assertEquals(false, origin.isNotValidReservation(targetList));
    }

    @Test
    public void 유효하지않은_예약() {
        targetList = new ArrayList<>();
        Reservation targetFirst = new Reservation("MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("01:00:00"), LocalTime.parse("01:30:00"), roomA);
        Reservation targetSecond = new Reservation("MUZI", LocalDate.parse("2018-10-10"), LocalTime.parse("00:00:00"), LocalTime.parse("00:30:00"), roomA);
        targetList.add(targetFirst);
        targetList.add(targetSecond);

        assertEquals(true, origin.isNotValidReservation(targetList));
    }
}