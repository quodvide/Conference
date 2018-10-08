package conference.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    @Column
    private LocalDate day;
    @Column
    private LocalTime startTime;
    @Column
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reservation_conference_room"))
    private ConferenceRoom room;

    public Reservation(String username, LocalDate day, LocalTime startTime, LocalTime endTime, ConferenceRoom room) {
        this.username = username;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public boolean isNotValidReservation(List<Reservation> list) {
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
}
