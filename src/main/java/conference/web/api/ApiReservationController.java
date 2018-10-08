package conference.web.api;

import conference.domain.Reservation;
import conference.dto.ReservationDto;
import conference.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ApiReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    public List<Reservation> getReservationList(LocalDate localDate) {
        return reservationService.getList(localDate);
    }

    @PostMapping
    public ResponseEntity<Void> reserve(@RequestBody ReservationDto reservationDto) {
        if(reservationService.reserve(reservationDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
