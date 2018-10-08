package conference.web.api;

import conference.domain.Reservation;
import conference.dto.ReservationDto;
import conference.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservations")
public class ApiReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getReservationList(LocalDate localDate) {
        return reservationService.getAllList(localDate);
    }

    @PostMapping
    public ResponseEntity<Void> reserve(@RequestBody ReservationDto reservationDto) {
        log.info("Reservation Tried");
        log.info("Dto : {}", reservationDto.toString());
        if(reservationService.reserve(reservationDto)) {
            log.info("We returned OK");
            return ResponseEntity.ok().build();
        }
        log.info("We returned UNAUTHORIZED");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
