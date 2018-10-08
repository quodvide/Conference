package conference.dto;

import conference.domain.Reservation;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReservationDto {

    private String username;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomName;
    private int count;

    public ReservationDto(String username, LocalDate day, LocalTime startTime, LocalTime endTime, String roomName, int count) {
        this.username = username;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.count = count;
    }

    public Reservation toReservation() {
        return new Reservation();
    }

    public boolean isRedundant(List<Reservation> list) {
        LocalTime tempStartTime;
        LocalTime tempEndTime;
        boolean redundantFlag = false;
        for (int i = 0; i < list.size(); i++) {
            redundantFlag = true;
            tempStartTime = list.get(i).getStartTime();
            tempEndTime = list.get(i).getEndTime();
            if(startTime.isBefore(tempStartTime) && (endTime.isBefore(tempStartTime) || endTime.equals(tempStartTime))) {
                redundantFlag = false;
            }
            if (endTime.isAfter(tempEndTime) && (startTime.isAfter(tempEndTime) || startTime.equals(tempEndTime))) {
                redundantFlag = false;
            }
            if(redundantFlag) return redundantFlag;
        }
        return redundantFlag;
    }

    public boolean isValid(List<Reservation> list) {
        if(isRedundant(list) || isNotValidTime()) return false;
        return true;
    }

    private boolean isNotValidTime() {
        if(startTime.getMinute()%30 != 0 || endTime.getMinute()%30 != 0) return true;
        return false;
    }
}
