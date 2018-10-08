package conference.repository;

import conference.domain.ConferenceRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Long> {
    Optional<ConferenceRoom> findByName(String name);
}
