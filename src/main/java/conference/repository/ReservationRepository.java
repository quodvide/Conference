package conference.repository;

import conference.domain.ConferenceRoom;
import conference.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByDay(LocalDate day);
    List<Reservation> findByDayAndRoom(LocalDate localDate, ConferenceRoom conferenceRoom);
}
