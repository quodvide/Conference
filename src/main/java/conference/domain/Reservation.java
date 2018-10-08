package conference.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
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
}
