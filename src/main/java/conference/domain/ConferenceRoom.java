package conference.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ConferenceRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
