package conference.service;

import conference.domain.ConferenceRoom;
import conference.domain.Reservation;
import conference.dto.ReservationDto;
import conference.repository.ConferenceRoomRepository;
import conference.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ReservationService {

    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public boolean reserve(ReservationDto reservationDto) {
        log.info("Reservation Tried!");
        if (reservationDto.isNotValidTime() || reservationDto.getCount() < 1 || isNotValidName(reservationDto.getRoomName())) {
            log.info("In First");
            return false;
        }
        ConferenceRoom reservationRoom = conferenceRoomRepository.findByName(reservationDto.getRoomName()).orElseThrow(() -> new IllegalArgumentException("해당 회의실이 존재하지않습니다."));
        log.info("Room Name : {}", reservationRoom.getName());
        List<Reservation> list = reservationDto.toReservation(reservationRoom);
        List<Reservation> compareList;
        // 만들어진 예약 정보들
        for (int i = 0; i < list.size(); i++) {
            compareList = getList(list.get(i).getDay(), reservationRoom);
            log.info("List Size : {}", compareList.size());
            if (compareList.size() != 0 && list.get(i).isNotValidReservation(compareList)) {
                log.info("In Second");
                return false;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            reservationRepository.save(list.get(i));
            log.info("Reservation : {}", list.get(i).toString());
        }
        return true;
    }

    private boolean isNotValidName(String roomName) {
        return conferenceRoomRepository.findByName(roomName).isPresent() ? false : true;
    }

    public List<Reservation> getList(LocalDate localDate, ConferenceRoom conferenceRoom) {
        return reservationRepository.findByDayAndRoom(localDate, conferenceRoom);
    }

    public List<Reservation> getAllList(LocalDate localDate) {
        return reservationRepository.findByDay(localDate);
    }
}
