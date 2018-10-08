package conference.service;

import conference.domain.Reservation;
import conference.dto.ReservationDto;
import conference.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public boolean reserve(ReservationDto reservationDto) {
        if(isValidateReservation(reservationDto)) {
            reservationRepository.save(reservationDto.toReservation());
            return true;
        }
        return false;
    }

    private boolean isValidateReservation(ReservationDto reservationDto) {
        return reservationDto.isValid(getList(reservationDto.getDay()));
    }

    public List<Reservation> getList(LocalDate localDate) {
        return reservationRepository.findByDay(localDate);
    }
}
