package conference.dto;

import conference.domain.ConferenceRoom;
import conference.domain.Reservation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ReservationDto {

    final static int WEEK = 7;

    private String roomName;
    private String username;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int count;

    public ReservationDto(String roomName, String username, LocalDate day, LocalTime startTime, LocalTime endTime, int count) {
        this.roomName = roomName;
        this.username = username;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.count = count;
    }

    public List<Reservation> toReservation(ConferenceRoom room) {
        List<Reservation> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Reservation(this.username, this.day.plusDays(i * WEEK), this.startTime, this.endTime, room));
        }
        return list;
    }

    public boolean isNotValidTime() {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) return true;
        if (startTime.getMinute() % 30 != 0 || endTime.getMinute() % 30 != 0) return true;
        return false;
    }
}
